package com.carbon.common.service;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.carbon.common.redis.CommonRedisKey;
import com.carbon.common.redis.RedisService;
import com.carbon.common.utils.HttpContextUtils;
import com.carbon.common.utils.SqlFilterUtils;
import com.carbon.domain.auth.vo.LoginInfoVo;
import com.carbon.domain.common.QueryParam;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author Li Jun
 * @since 2021-06-11
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired
    private RedisService redisService;

    protected Page<?> getPage(QueryParam queryParam) {
        //分页对象
        Page<?> page = new Page<>(queryParam.getCurrent(), queryParam.getSize());

        //没有排序字段，则不排序
        if(StrUtil.isBlank(queryParam.getSortField())){
            return page;
        }

        //排序字段、防止SQL注入（因为sidx、order是通过拼接SQL实现排序的，会有SQL注入风险）
        String orderField = SqlFilterUtils.sqlInject(queryParam.getSortField());
        //前端字段排序
        if(queryParam.getAsc()) {
            return  page.addOrder(OrderItem.asc(orderField));
        }else {
            return page.addOrder(OrderItem.desc(orderField));
        }
    }

    /**
     * 获取当前登录人的租户ID
     * @return TenantId
     */
    protected Long getCurrentTenantId(){
        return HttpContextUtils.getTenantId();
    }

    /**
     * 获取当前登录人的账户ID
     * @return AccountId
     */
    protected Long getCurrentAccountId(){
        return HttpContextUtils.getAccountId();
    }

    /**
     * 获取当前登录信息
     * @return LoginInfoVo
     */
    protected LoginInfoVo getLoginInfoVo(){
        String json = redisService.get(String.format(CommonRedisKey.LOGIN_USER,getCurrentAccountId()));
        if (StrUtil.isEmpty(json)) {
            return null;
        }
        return new Gson().fromJson(json, LoginInfoVo.class);
    }
}
