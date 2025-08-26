package com.carbon.common.mybatis;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.carbon.domain.auth.api.LoginCheckApi;
import com.carbon.domain.auth.vo.SecurityData;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import java.util.Date;

/**
 * 自动注入属性
 * @author : Li Jun
 * @since : 2021-06-17 21:05
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Autowired
    private LoginCheckApi loginCheckApi;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ..........................");
        DateTime currentTime = DateUtil.date();
        this.strictInsertFill(metaObject, "creatorId", Long.class, getAccountId());
        this.strictInsertFill(metaObject, "createdTime", Date.class, currentTime);
        this.strictInsertFill(metaObject, "updatedTime", Date.class, currentTime);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill .........................");
        this.strictUpdateFill(metaObject, "updatedId", Long.class, getAccountId());
        this.strictUpdateFill(metaObject, "updatedTime", Date.class, DateUtil.date());
    }

    private Long getAccountId(){
        SecurityData data = loginCheckApi.getSecurityData().getData();
        if (data == null){
            return null;
        }
        return data.getAccountId();
    }


}
