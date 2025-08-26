package com.carbon.common.utils;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 全局 ApplicationContext
 */
@Component
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    public static void clearHolder() {
        applicationContext = null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext属性未注入");
        }
    }
}
