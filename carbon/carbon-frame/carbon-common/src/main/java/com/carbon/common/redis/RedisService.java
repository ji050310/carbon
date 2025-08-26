package com.carbon.common.redis;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redis操作Service,
 * 封装redis的操作方法
 * @author magang
 */
public interface RedisService {
    Boolean expireAt(String key, Date date);

    Set<String> keys(String pattern);

    Boolean move(String key, int dbIndex);

    Boolean persist(String key);

    Long getExpire(String key, TimeUnit unit);

    Long getExpire(String key);

    String randomKey();

    void rename(String oldKey, String newKey);

    Boolean renameIfAbsent(String oldKey, String newKey);

    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    void delete(Collection<String> keys);

    byte[] dump(String key);

    Boolean hasKey(String key);

    Boolean expire(String key, long timeout, TimeUnit unit);

    /**
     * 设置超期时间
     */
    Boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);


    String getRange(String key, long start, long end);

    String getAndSet(String key, String value);

    Boolean getBit(String key, long offset);

    List<String> multiGet(Collection<String> keys);

    boolean setBit(String key, long offset, boolean value);

    void setEx(String key, String value, long timeout, TimeUnit unit);

    boolean setIfAbsent(String key, String value);

    void setRange(String key, String value, long offset);

    Long size(String key);

    void multiSet(Map<String, String> maps);

    boolean multiSetIfAbsent(Map<String, String> maps);

    Long incrBy(String key, long increment);

    Double incrByFloat(String key, double increment);

    Integer append(String key, String value);

    Object hGet(String key, String field);

    Map<Object, Object> hGetAll(String key);

    List<Object> hMultiGet(String key, Collection<Object> fields);

    void hPut(String key, String hashKey, String value);

    void hPutAll(String key, Map<Object, Object> maps);

    void hPutStrAll(String key, Map<String, String> maps);

    Boolean hPutIfAbsent(String key, String hashKey, String value);

    Long hDelete(String key, Object... fields);

    boolean hExists(String key, String field);

    Long hIncrBy(String key, Object field, long increment);

    Double hIncrByFloat(String key, Object field, double delta);

    Set<Object> hKeys(String key);

    Long hSize(String key);

    List<Object> hValues(String key);


    String lIndex(String key, long index);

    List<String> lRange(String key, long start, long end);

    List<String> allRange(String key);

    Long lLeftPush(String key, String value);

    Long lLeftPushAll(String key, String... value);

    Long lLeftPushAll(String key, Collection<String> value);

    Long lLeftPushIfPresent(String key, String value);

    Long lLeftPush(String key, String pivot, String value);

    Long lRightPush(String key, String value);

    Long lRightPushAll(String key, String... value);

    Long lRightPushAll(String key, Collection<String> value);

    Long lRightPushIfPresent(String key, String value);

    Long lRightPush(String key, String pivot, String value);

    void lSet(String key, long index, String value);

    String lLeftPop(String key);

    String leftPop(String key, long timeout, TimeUnit unit);

    String lRightPop(String key);

    String lRightPop(String key, long timeout, TimeUnit unit);

    String lRightPopAndLeftPush(String sourceKey, String destinationKey);

    String lRightPopAndLeftPush(String sourceKey, String destinationKey,
                                long timeout, TimeUnit unit);

    Long lRemove(String key, long index, String value);

    void lTrim(String key, long start, long end);

    Long lLen(String key);

    Long sAdd(String key, String... values);

    Long sRemove(String key, Object... values);

    String sPop(String key);

    Boolean sMove(String key, String value, String destKey);

    Long sSize(String key);

    Boolean sIsMember(String key, Object value);

    Set<String> sIntersect(String key, String otherKey);

    Set<String> sIntersect(String key, Collection<String> otherKeys);

    Long sIntersectAndStore(String key, String otherKey, String destKey);

    Long sIntersectAndStore(String key, Collection<String> otherKeys,
                            String destKey);

    Set<String> sUnion(String key, String otherKeys);

    Set<String> sUnion(String key, Collection<String> otherKeys);

    Long sUnionAndStore(String key, String otherKey, String destKey);

    Long sUnionAndStore(String key, Collection<String> otherKeys,
                        String destKey);

    Set<String> sDifference(String key, String otherKey);

    Set<String> sDifference(String key, Collection<String> otherKeys);

    Long sDifference(String key, String otherKey, String destKey);

    Long sDifference(String key, Collection<String> otherKeys,
                     String destKey);

    Set<String> setMembers(String key);

    String sRandomMember(String key);

    List<String> sRandomMembers(String key, long count);

    Set<String> sDistinctRandomMembers(String key, long count);

    Boolean zAdd(String key, String value, double score);

    Long zRemove(String key, Object... values);

    Double zIncrementScore(String key, String value, double delta);

    Long zRank(String key, Object value);

    Long zReverseRank(String key, Object value);

    Set<String> zRange(String key, long start, long end);

    Set<String> zRangeByScore(String key, double min, double max);

    Set<String> zReverseRange(String key, long start, long end);

    Set<String> zReverseRangeByScore(String key, double min,
                                     double max);

    Set<String> zReverseRangeByScore(String key, double min,
                                     double max, long start, long end);

    Long zCount(String key, double min, double max);

    Long zSize(String key);

    Long zCard(String key);

    Double zScore(String key, Object value);

    Long zRemoveRange(String key, long start, long end);

    Long zRemoveRangeByScore(String key, double min, double max);

    Long zUnionAndStore(String key, String otherKey, String destKey);

    Long zUnionAndStore(String key, Collection<String> otherKeys,
                        String destKey);

    Long zIntersectAndStore(String key, String otherKey,
                            String destKey);

    Long zIntersectAndStore(String key, Collection<String> otherKeys,
                            String destKey);


}
