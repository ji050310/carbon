
package com.carbon.common.utils;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.time.Duration;
import java.util.Date;

/**
 * <p>
 * jwt工具类
 * </p>
 *
 * @author Li Jun
 * @since 2021-06-17
 **/
@Slf4j
public class JwtUtil {

    /**token 携带的信息*/
    public static final String JWT_ACCOUNT_ID = "account_id";
    public static final String JWT_TENANT_ID = "tenant_id";
    /**签发人*/
    private static final String ISSUER = "carbon";
    /**主题*/
    private static final String SUBJECT = "";
    /**签发的目标*/
    private static final String AUDIENCE = "web";
    /**token失效时间,默认3天,60*60*24*3=259200*/
    public static final Long EXPIRE_SECOND = 259200L;
    /**盐值校验*/
    private static final String SALT = "carbon";


    /**
     * 生成JWT Token
     * @param accountId 账户id
     * @param tenantId 租户id
     * @param expireDuration 过期时间和单位
     * @return token
     */
    public static String generateToken(Long accountId,Long tenantId, Duration expireDuration) {
        try {
            if (accountId == null) {
                return null;
            }
            // 过期时间，单位：秒
            long expireSecond = EXPIRE_SECOND;
            if (expireDuration != null) {
                expireSecond = expireDuration.getSeconds();
            }
            Date expireDate = DateUtils.addSeconds(new Date(), (int) expireSecond);
            // 生成token
            Algorithm algorithm = Algorithm.HMAC256(SALT);
            return JWT.create()
                    .withClaim(JWT_ACCOUNT_ID,accountId)
                    .withClaim(JWT_TENANT_ID,tenantId)
                    .withJWTId(UUID.randomUUID().toString())
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    .withIssuedAt(new Date())
                    .withExpiresAt(expireDate)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("generateToken exception", e);
        }
        return null;
    }

    public static String generateToken(Long accountId,Long tenantId) {
        return generateToken(accountId,tenantId,null);
    }

    /**
     * 校验token
     * @param token token
     * @return boolean
     */
    public static boolean isValidToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SALT);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .withAudience(AUDIENCE)
                    .build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            log.error("token校验失败:{}",e.getMessage());
        }
        return false;
    }

    /**
     * 获取token 中的信息
     * @param name key 的名称
     * @return String
     */
    public static Long getTokenInfo(String token,String name){
        if (StrUtil.isEmpty(token)){
            return null;
        }
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(name).asLong();
    }

    public static String get(String token,String name){
        DecodedJWT decode = JWT.decode(token);
        return decode.getClaim(name).asString();
    }

}
