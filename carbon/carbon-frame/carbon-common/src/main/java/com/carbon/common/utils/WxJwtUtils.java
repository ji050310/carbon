package com.carbon.common.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/*
token工具类
 */
public class WxJwtUtils {

    private static final String jwtToken = "v0x1bx@tok0x1ben" ;

    /**
     * 生成token
     * @param openid
     * @return
     */
    public static String createToken(String openid) {
        Map<String , Object> claims = new HashMap<>() ;
        claims.put("openId" , openid) ;
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256 , jwtToken)      //签发算法
                .setClaims(claims)      //body数据
                .setIssuedAt(new Date())    //设置签发时间
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) ;    //设置一天的有效时间
        String token = jwtBuilder.compact() ;
        return token ;
    }

    /**
     * 破解token
     * @param token
     * @return
     */
    public static Map<String , Object> checkToken(String token) {
        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token) ;
            return (Map<String, Object>) parse.getBody();
        } catch(Exception e) {
            e.printStackTrace() ;
        }
        return null ;
    }

    public static void main(String[] args) {
        String token = WxJwtUtils.createToken("100") ;
        System.out.println(token);
        Map<String , Object> map = WxJwtUtils.checkToken(token) ;
        System.out.println(map.get("openId"));
    }

}
