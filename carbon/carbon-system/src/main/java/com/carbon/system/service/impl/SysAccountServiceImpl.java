package com.carbon.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.carbon.common.api.Paging;
import com.carbon.common.enums.ExpCodeEnum;
import com.carbon.common.exception.CommonBizException;
import com.carbon.common.redis.RedisService;
import com.carbon.common.service.BaseServiceImpl;
import com.carbon.domain.common.ApiResult;
import com.carbon.domain.common.constant.RedisKeyName;
import com.carbon.domain.common.constant.RocketDelayLevelConstant;
import com.carbon.domain.common.constant.RocketMqName;
import com.carbon.domain.common.constant.SmsConstant;
import com.carbon.domain.system.param.ChangePasswordParam;
import com.carbon.domain.system.param.SysAccountParam;
import com.carbon.system.entity.SysAccount;
import com.carbon.system.param.*;
import com.carbon.system.service.SysAccountRoleService;
import com.carbon.system.service.common.MailService;
import com.carbon.system.vo.SysAccountQueryVo;
import com.carbon.system.mapper.SysAccountMapper;
import com.carbon.system.service.SysAccountService;
import com.carbon.system.service.SysRoleService;
import com.carbon.system.vo.SysAccountRoleVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * <p>
 * 帐号  服务实现类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-11
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class SysAccountServiceImpl extends BaseServiceImpl<SysAccountMapper, SysAccount> implements SysAccountService {

    @Resource
    private SysAccountMapper sysAccountMapper;
    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysAccountRoleService sysAccountRoleService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private MailService mailService;
    @Autowired
    private RocketMQTemplate mqTemplate;

    private static final String REGION_ID = "cn-hangzhou";
    private static final String SYS_DOMAIN = "dysmsapi.aliyuncs.com";
    private static final String SYS_VERSION = "2017-05-25";
    private static final String SYS_ACTION = "SendSms";

    //签名
    @Value("${sms.sign}")
    private String smsSignContent;
    @Value("${aliyun.accesskey-id}")
    private String accesskeyId;
    @Value("${aliyun.accesskey-secret}")
    private String accesskeySecret;
    @Value("${sms.template_code}")
    private String templateCode;


    @Override
    public void addAccount(SysAccountParam param) {
        SysAccount account = sysAccountMapper.selectForUpdate(param.getAccountName());
        //账户名唯一
        if (account != null) {
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_NAME_ALREADY_EXISTS);
        }
        account = sysAccountMapper.selectOne(Wrappers.<SysAccount>lambdaQuery().eq(SysAccount::getPhone, param.getPhone()));
        //手机号唯一
        if (account != null) {
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_PHONE_ALREADY_EXISTS);
        }
        //加密密码，保存账户
        param.setPassword(DigestUtils.md5Hex(param.getPassword()));
        account = new SysAccount();
        BeanUtil.copyProperties(param, account);
        if (!param.getAccountStatus().equals("0390000004")) {
            account.setCreatedTime(DateUtil.date());
        }
        this.save(account);
        //保存账户角色
        sysRoleService.saveAccountRoles(account.getId(), param.getRoleIds());
    }

    @Override
    public void updateAccount(SysAccountParam param) {
        SysAccount account = sysAccountMapper.selectOne(Wrappers.lambdaQuery(SysAccount.class).eq(SysAccount::getId, param.getId()));
        if (account == null){
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_NOT_EXISTS);
        }

        //手机号唯一
        if (!param.getPhone().equals(account.getPhone()) && this.lambdaQuery().eq(SysAccount::getPhone,param.getPhone()).count() > 0){
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_PHONE_ALREADY_EXISTS);
        }

        //检查数据合法性
        if(!ReUtil.isMatch("1[3-9]\\d{9}", param.getPhone())){
            throw new CommonBizException("请输入正确的手机号");
        }
        if(StrUtil.isNotBlank(param.getEmail())){
            if(!ReUtil.isMatch("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", param.getEmail())){
                throw new CommonBizException("请输入正确的邮箱");
            }
        }
        //更新账户
        String password = account.getPassword();
        BeanUtil.copyProperties(param,account,"accountName");
        account.setUpdatedTime(DateUtil.date());
        //密码加密
        if(StrUtil.isNotBlank(param.getPassword())){
            account.setPassword(DigestUtils.md5Hex(param.getPassword()));
        }
        else {
            account.setPassword(password);
        }
        if (!updateById(account)){
            throw new CommonBizException(ExpCodeEnum.OPERATE_FAIL_ERROR);
        }
        sysRoleService.saveAccountRoles(account.getId(),param.getRoleIds());

    }

    @Override
    public SysAccountQueryVo getSysAccountById(Long id) {
        if(id==0)
        {
            SysAccountQueryVo s=new SysAccountQueryVo();
            return s;
        }
        SysAccountQueryVo accountVo = sysAccountMapper.getSysAccountById(id);
        accountVo.setAccountRole(sysRoleService.getAccountRole(id));
        return accountVo;
    }

    @Override
    public Paging<SysAccountQueryVo> getSysAccountPageList(SysAccountQueryParam param) {
        Page<?> page = getPage(param);
        page.addOrder(OrderItem.desc("sa.updated_time"));
        IPage<SysAccountQueryVo> iPage = sysAccountMapper.getSysAccountPageList(page,param);
        List<SysAccountQueryVo> records = iPage.getRecords();
        //查对应角色
        for (SysAccountQueryVo record : records){
            Long accountId = record.getId();
            SysAccountRoleVo accountRole = sysRoleService.getAccountRole(accountId);
            record.setAccountRole(accountRole);
        }
        iPage.setRecords(records);
        return new Paging<>(iPage);
    }

    @Override
    public void updateAccountBaseInfo(AccountBaseInfoUpdateParam param) {
        SysAccount account = sysAccountMapper.selectByIdForUpdate(param.getId());
        if (account == null){
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_NOT_EXISTS);
        }
        //检查数据合法性
        if(!ReUtil.isMatch("1[3-9]\\d{9}", param.getPhone())){
            throw new CommonBizException("请输入正确的手机号");
        }
        if(StrUtil.isNotBlank(param.getEmail())){
            if(!ReUtil.isMatch("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", param.getEmail())){
                throw new CommonBizException("请输入正确的邮箱");
            }
        }
        account.setAccountName(param.getAccountName());
        account.setPhone(param.getPhone());
        account.setEmail(param.getEmail());
        if (!this.updateById(account)){
            throw new CommonBizException(ExpCodeEnum.OPERATE_FAIL_ERROR);
        }
    }

    @Override
    public void updatePassword(ChangePasswordParam param) {
        SysAccount account = this.getById(param.getId());
        if (account == null){
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_NOT_EXISTS);
        }
        String oldPasswordMd5 = StrUtil.isEmpty(param.getOldPassword()) ? param.getOldPasswordMd5() : DigestUtils.md5Hex(param.getOldPassword());
        //校验旧密码
        if (StrUtil.isEmpty(oldPasswordMd5) || !oldPasswordMd5.equals(account.getPassword())){
            throw new CommonBizException("旧密码不正确");
        }
        account.setPassword(DigestUtils.md5Hex(param.getNewPassword()));
        if (!this.updateById(account)){
            throw new CommonBizException("修改密码失败");
        }
    }

    @Override
    public void updateAccountAvatar(ChangeAvatarParam param) {
        SysAccount account = sysAccountMapper.selectById(param.getId());
        if (account == null){
            throw new CommonBizException(ExpCodeEnum.SYS_ACCOUNT_NOT_EXISTS);
        }
        //将头像url插入数据库
        account.setAvatar(param.getAvatar());
        sysAccountMapper.updateById(account);
    }

    @Override
    public void sendUpdateCode(String phone) {
        String key = RedisKeyName.SMS_UPDATE_PHONE_KEY + phone;
        String validCode = "123456";
        HashMap<String, Object> param = new HashMap<>();
        param.put("code", validCode);
        //有效时间 5分钟
        redisService.setEx(key, validCode, 300, TimeUnit.SECONDS);
        System.out.println(templateCode);

//        sendMsg(phone, templateCode, JSONUtil.toJsonStr(param));
    }

    @Override
    public void updatePhone(ChangePhoneParam param) {
        //修改手机号
        SysAccount sysAccount = sysAccountMapper.selectById(param.getId());
        sysAccount.setPhone(param.getPhone());
        sysAccountMapper.updateById(sysAccount);
    }

    @Override
    public void bindEmail(BindEmailParam param) {
        //通过验证密码来绑定邮箱
        SysAccount sysAccount = baseMapper.selectById(param.getId());
        if(!DigestUtils.md5Hex(param.getPassword()).equals(sysAccount.getPassword())){
            throw new CommonBizException("密码错误！");
        }
        //简单正则验证邮箱正确性
        if(!ReUtil.isMatch("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", param.getEmail())){
            throw new CommonBizException("请输入正确的邮箱");
        }
        sysAccount.setEmail(param.getEmail());
        baseMapper.updateById(sysAccount);
    }

    @Override
    public void sendEmail(SendEmailParam param) {
        //验证密码是否正确
        SysAccount sysAccount = baseMapper.selectById(param.getId());
        if(!DigestUtils.md5Hex(param.getPassword()).equals(sysAccount.getPassword())){
            throw new CommonBizException("请输入正确的登录密码！");
        }
        //简单正则验证邮箱正确性
        if(!ReUtil.isMatch("[a-zA-Z0-9]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+", param.getEmail())){
            throw new CommonBizException("请输入正确的邮箱");
        }
        //邮件信息
        //使用redis随机码
        String key = param.getEmail();
        String value = RandomUtil.randomString(6);
        redisService.setEx(key,value,10,TimeUnit.MINUTES);
        String text="点击以下链接完成邮箱验证\n" +
                "http://127.0.0.1:9091/system/sysAccount/renew/email/"+param.getId().toString()+"/"+param.getEmail()+"/"+value;
        mailService.simple(param.getEmail(),"【碳信使】邮箱验证",text);
    }

    @Override
    public void bindEmail2(Long id,String email,String value) {
        //验key-value
        if(value.equals(redisService.get(email))){
            SysAccount sysAccount = baseMapper.selectById(id);
            sysAccount.setEmail(email);
            baseMapper.updateById(sysAccount);
        }
        else {
            throw new CommonBizException("验证链接已失效，请重新绑定！");
        }
    }

    @Override
    public void deleteAccountById(Long id) {
//        超管账户不可删除
        if(id == 1){
            throw new CommonBizException("超管账户不可删除");
        }
        baseMapper.deleteById(id);
    }

    public void sendMsg(String phone, String templateCode, String content) {
        DefaultProfile profile = DefaultProfile.getProfile(REGION_ID, accesskeyId, accesskeySecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain(SYS_DOMAIN);
        request.setSysVersion(SYS_VERSION);
        request.setSysAction(SYS_ACTION);
        request.putQueryParameter("RegionId", REGION_ID);
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", smsSignContent);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", content);
        try {
            CommonResponse response = client.getCommonResponse(request);
            log.info("调用发送短信返回结果，{}", JSONUtil.toJsonPrettyStr(response.getData()));

            JSONObject resultJson= JSON.parseObject(response.getData());
            if (!"OK".equals(resultJson.get("Message"))) {
                throw new CommonBizException("发送失败");
            }
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CommonBizException("发送失败");
        }
    }

    public void checkValidateCode(String phone, String code, String type) {
        if (StrUtil.isEmpty(phone) || StrUtil.isEmpty(code)) {
            throw new CommonBizException("手机号或验证码为空");
        }
        String redisKey = type + phone;
        String redisMsgCode = redisService.get(redisKey);
        log.info("redisMsgCode：{}",redisMsgCode);
        if (StrUtil.isEmpty(redisMsgCode)) {
            throw new CommonBizException("验证码已过期");
        }
        if (!code.equals(redisMsgCode)) {
            throw new CommonBizException("请输入正确的验证码");
        }
        //验证码消费后失效
        redisService.delete(CollUtil.newArrayList(redisKey));
    }

}
