package com.carbon.common.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.carbon.common.constants.CommonConstant;
import com.carbon.common.utils.HttpContextUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * mybatis-plus配置
 *
 * @author Li Jun
 * @since 2021-06-11
 **/
@Slf4j
@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        //多租户的表
        List<String> tenantTables = new ArrayList<>();
        tenantTables.add("carbon_project");
        tenantTables.add("carbon_source_data");
        tenantTables.add("data_panel");
        tenantTables.add("exchange_account");
        tenantTables.add("tenant_user");
        tenantTables.add("carbon_project_doc");
        tenantTables.add("carbon_resource_file");
        tenantTables.add("carbon_credit_assets");
        tenantTables.add("carbon_quota_assets");


        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(
                new TenantLineHandler() {

                    // 获取租户 ID 值表达式，只支持单个 ID 值
                    @Override
                    public Expression getTenantId() {
                        Long tenantId = HttpContextUtils.getTenantId();
                        if (tenantId != null) {
                            return new StringValue(String.valueOf(tenantId));
                        }
                        return null;
                    }
                    // 这是 default 方法,默认返回 false 表示所有表都需要拼多租户条件,
                    // 这里设置 role表不需要该条件
                    @Override
                    public boolean ignoreTable(String tableName) {
                        if (getTenantId() == null){
                            return true;
                        }
                        return tenantTables.stream().noneMatch(t -> t.equalsIgnoreCase(tableName));
                    }

                    @Override
                    public String getTenantIdColumn() {
                        return CommonConstant.TENANT_ID;
                    }
                }));
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    public ConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.setUseDeprecatedExecutor(false);
    }
}
