package com.carbon.chainmaker.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * @author : LiJun
 * @date : 2022-07-12 14:18
 **/
public class CommUtils {

    public static Map<String,String> beanToMap(Object obj){
        return JSON.parseObject(JSONObject.toJSONString(obj),new TypeReference<Map<String,String>>(){});
    }
}
