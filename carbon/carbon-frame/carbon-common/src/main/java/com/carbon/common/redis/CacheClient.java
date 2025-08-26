package com.carbon.common.redis;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Slf4j
@Component
/**
 * author jzh
 * date 2022/06/21
 * redis序列化存储封装（未完成）
 */
public class CacheClient {
    private final StringRedisTemplate stringRedisTemplate;

    public CacheClient(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public void set(String key, Object value, Long time, TimeUnit unit){
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(value), time, unit);
    }

    public void setStringRedisTemplate(String key, Object value, Long time, TimeUnit unit){
        //设置逻辑过期
        RedisData redisData = new RedisData();
        redisData.setData(value);
        redisData.setExpireTime(LocalDateTime.now().plusSeconds(unit.toSeconds(time)));
        //写入redis
        stringRedisTemplate.opsForValue().set(key, JSONUtil.toJsonStr(redisData));
    }

    public <R, ID> R queryWithPassThrough(
            String KeyPrefix, ID id, Class<R> type, Function<ID, R> dbFallback, Long time, TimeUnit unit) {
        String key = KeyPrefix + id;
        //从redis查询缓存
        String json = stringRedisTemplate.opsForValue().get(key);
        //判断是否存在
        if (StrUtil.isNotBlank(json)){
            //存在直接返回
            return JSONUtil.toBean(json, type);
        }
        // 判断命中是否为空值
        if (json != null){
            //返回一个错误信息
            return null;
        }

        //不存在，根据id查询数据库
        R r = dbFallback.apply(id);
        if (r == null){
            //将空值写入redis
            stringRedisTemplate.opsForValue().set(key,"", 500L,TimeUnit.MINUTES);//过期500分钟
            //返回错误信息
            return null;
        }
        //存在，写入redis
        stringRedisTemplate.opsForValue().set(key,JSONUtil.toJsonStr(r),1L,TimeUnit.DAYS);//过期1天

        return r;
    }
}
