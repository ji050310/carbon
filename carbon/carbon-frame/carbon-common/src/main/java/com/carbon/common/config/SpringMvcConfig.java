package com.carbon.common.config;

import cn.hutool.core.date.DatePattern;
import com.carbon.common.config.jackson.deserializer.JacksonDateDeserializer;
import com.carbon.common.config.jackson.deserializer.JacksonDoubleDeserializer;
import com.carbon.common.config.jackson.serializer.DictSerializerModifier;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @author Li Jun
 * @since 2021-06-11
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        //Jackson 处理
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = converter.getObjectMapper();
        // 时间格式化
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));
        objectMapper.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        SimpleModule simpleModule = new SimpleModule();

        // Long类型序列化成字符串，避免Long精度丢失
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        //序列化监听
        simpleModule.setSerializerModifier(new DictSerializerModifier());

        //日期反序列化
        simpleModule.addDeserializer(Date.class, new JacksonDateDeserializer());
        simpleModule.addDeserializer(Double.class, new JacksonDoubleDeserializer());

        objectMapper.registerModule(simpleModule).registerModule(new ParameterNamesModule());

        // 设置格式化内容
        converter.setObjectMapper(objectMapper);
        converters.add(0, converter);
    }
}
