package com.carbon.common.config.jackson.serializer;


import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.carbon.common.exception.CommonBizException;
import com.carbon.common.redis.RedisService;
import com.carbon.common.utils.SpringContextHolder;
import com.carbon.domain.common.annotation.Dict;
import com.carbon.domain.common.annotation.OperatorName;
import com.carbon.domain.common.constant.RedisKeyName;
import com.carbon.domain.system.vo.SysDictModelVo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;



/**
 * 字典序列化
 * @author Li Jun
 * @since 2021-06-11
 */
@Slf4j
public class DictSerializerModifier extends BeanSerializerModifier {

    private RedisService redisService;

    @Override
    public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
        initDictNameCache();
        for (BeanPropertyWriter beanProperty : beanProperties) {
            Dict dict = beanProperty.getAnnotation(Dict.class);
            if (dict != null){
                beanProperty.assignNullSerializer(new DictSerializer(dict,redisService));
                beanProperty.assignSerializer(new DictSerializer(dict,redisService));
            }

            OperatorName operatorName = beanProperty.getAnnotation(OperatorName.class);
            if (operatorName != null){
                beanProperty.assignNullSerializer(new OperatorNameSerializer(operatorName,redisService));
                beanProperty.assignSerializer(new OperatorNameSerializer(operatorName,redisService));
            }
        }
        return beanProperties;
    }

    /**
     * 初始化redis
     */
    private void initDictNameCache(){
        if (redisService == null){
            redisService = SpringContextHolder.getBean(RedisService.class);
        }
    }


    /**
     * 字典序列化
     */
    static class DictSerializer extends JsonSerializer<Object> {

        /**生成序列化后的字典后缀*/
        private final String DICT_SUFFIX = "Name";

        /**字典注解信息*/
        private Dict dict;

        /** 获取枚举Key的方法 */
        private static final String[] ENUM_KEY_METHOD = {"getValue","getCode","getStatus","getState"};

        /** 获取枚举描述名称的方法 */
        private static final String[] ENUM_NAME_METHOD = {"getName"};


        private final RedisService redisService;

        public DictSerializer(Dict dict,RedisService redisService) {
            this.dict = dict;
            this.redisService = redisService;
        }

        /**
         * 序列化
         */
        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            String fieldName = gen.getOutputContext().getCurrentName().concat(DICT_SUFFIX);
            String dictName = this.getDictName(dict, value);

            //注入后缀为name 的字段
            serializerProvider.defaultSerializeValue(value,gen);
            gen.writeStringField(fieldName,dictName);
        }

        /**
         * 获取字典名称
         * @param dict 字典注解
         * @param value 字段值
         * @return 字典名称
         */
        private String getDictName(Dict dict, Object value) {
            String defaultName = " ";

            //如果指定了枚举，则使用枚举转换
            String enumName = getEnumName(dict, value);
            if (enumName != null){
                return enumName;
            }

            //使用字典表转换
            String dictCode = dict.dictCode();
            if (StrUtil.isEmpty(dictCode)){
                return defaultName;
            }

            List<SysDictModelVo> modelVos = JSONUtil.toList(redisService.get(RedisKeyName.SYS_DICT_KEY + dictCode), SysDictModelVo.class);
            for (SysDictModelVo dictModel : modelVos) {
                if (dictModel.getValue().equals(String.valueOf(value))) {
                    return dictModel.getName();
                }
            }
            return defaultName;
        }

        /**
         * 获取枚举名称
         * @param dict 字典注解
         * @param value 字段值
         * @return 枚举名称
         */
        private String getEnumName(Dict dict,Object value) {
            if (dict == null || value == null){
                return null;
            }
            Class<? extends Enum<?>> enumType = dict.enumType();
            if (Dict.Empty.class.equals(enumType)){
                return null;
            }

            //查找枚举Key方法
            Method keyMethod = this.getMethod(enumType,ENUM_KEY_METHOD);
            //查找枚举Name方法
            Method nameMethod = this.getMethod(enumType,ENUM_NAME_METHOD);

            //匹配字典 key ，返回对应的 name
            Enum<?>[] enums = enumType.getEnumConstants();
            try {
                for (Enum<?> anEnum : enums) {
                    String valueStr = String.valueOf(value);
                    String enumsKey = String.valueOf(keyMethod.invoke(anEnum));
                    if (valueStr.equals(enumsKey)){
                        return nameMethod.invoke(anEnum).toString();
                    }
                }
                throw new CommonBizException(StrUtil.format("枚举转换失败：枚举{}，找不到对应的枚举key:{}",enumType.getName(),value));
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                throw new CommonBizException(StrUtil.format("枚举转换失败：枚举{}，异常:{}",enumType.getName(),e.getMessage()));
            }
        }

        private Method getMethod(Class<? extends Enum<?>> enumType, String[] methodNames) {
            for (String methodName : methodNames) {
                try {
                    return enumType.getMethod(methodName);
                }catch (NoSuchMethodException ignored){}
            }
            throw new CommonBizException(StrUtil.format("枚举转换失败：枚举{}，没有{}方法",enumType.getName(),Arrays.toString(methodNames)));
        }

    }


    /**
     * 用户名序列化
     */
    static class OperatorNameSerializer extends JsonSerializer<Object> {

        private final String DICT_SUFFIX = "Name";

        /**字典注解信息*/
        private OperatorName operatorName;

        private final RedisService redisService;

        public OperatorNameSerializer(OperatorName operatorName,RedisService redisService) {
            this.operatorName = operatorName;
            this.redisService = redisService;
        }


        @Override
        public void serialize(Object value, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
            String fieldName = gen.getOutputContext().getCurrentName().replace("Id",DICT_SUFFIX);
            String accountName = redisService.get(RedisKeyName.ACCOUNT_NAME_CACHE + value);

            //注入后缀为name 的字段
            serializerProvider.defaultSerializeValue(value,gen);
            gen.writeStringField(fieldName,accountName);
        }
    }
}
