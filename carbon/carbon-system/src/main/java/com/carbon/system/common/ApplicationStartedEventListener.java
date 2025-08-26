package com.carbon.system.common;

import cn.hutool.json.JSONUtil;
import com.carbon.common.redis.RedisService;
import com.carbon.domain.common.constant.RedisKeyName;
import com.carbon.domain.system.vo.SysDictModelVo;
import com.carbon.system.entity.SysAccount;
import com.carbon.system.mapper.SysDictMapper;
import com.carbon.system.service.SysAccountService;
import com.carbon.system.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 应用启动完成 监听器
 */
@Slf4j
@Component
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {

    @Resource
    private SysDictMapper sysDictMapper;
    @Autowired
    private SysDictService sysDictService;
    @Autowired
    private SysAccountService sysAccountService;
    @Autowired
    private RedisService redisService;


    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        log.info("......ApplicationStartedEvent......");
        initDictCache();
    }


    /**
     * 初始化字典缓存
     */
    public void initDictCache(){
        //缓存字典
        Map<String, List<SysDictModelVo>> dictMap = sysDictService.getAllDict().stream().collect(Collectors.groupingBy(SysDictModelVo::getDictCode));
        dictMap.forEach((dictCode,dictList) ->{
            redisService.set(RedisKeyName.SYS_DICT_KEY + dictCode,JSONUtil.toJsonStr(dictList));
        });

        //缓存用户名
        List<SysAccount> accounts = sysAccountService.list();
        for (SysAccount account : accounts) {
            redisService.set(RedisKeyName.ACCOUNT_NAME_CACHE + account.getId(),account.getAccountName());
        }

    }
}
