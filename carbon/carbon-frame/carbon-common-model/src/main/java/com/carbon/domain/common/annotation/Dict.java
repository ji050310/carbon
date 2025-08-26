package com.carbon.domain.common.annotation;


import cn.hutool.core.annotation.Alias;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Inherited
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Dict {

    /**
     * 枚举默认值
     */
    enum Empty{}

    /**
     * @return 字典枚举
     */
    Class<? extends Enum<?>> enumType() default Empty.class;

    /**
     * @return 字典code
     */
    String dictCode() default "";
}
