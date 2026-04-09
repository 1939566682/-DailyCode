package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

/**
 * JwtUtil
 * JWT工具类
 * @author Yang QingBo
 * {@code @date} 2026-04-09 15:11
 */

@Slf4j
public class JwtUtil {

    // 有效期为 1 小时（单位：毫秒）
    public static final Long JWT_TTL = 60 * 60 * 1000L;

    // 密钥明文：必须 ≥32 字节（这里使用 32 字符）
    public static final String JWT_KEY = "luoyu1234567890123456789012345678"; // 32 字节

    public static void main(String[] args) {
        String token = createJWT("userID_123123", JWT_TTL);
        System.out.println("Token: " + token);

        try {
            Claims claims = parseJWT(token);
            System.out.println("解析结果: " + claims);
        } catch (Exception e) {
            log.info("log() JwtUtil:",e);
        }
    }

    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 生成 JWT（使用默认过期时间）
     */
    public static String createJWT(String subject) {
        return createJWT(subject, JWT_TTL);
    }

    /**
     * 生成 JWT（指定过期时间）
     */
    public static String createJWT(String subject, Long ttlMillis) {
        return createJWT(getUUID(), subject, ttlMillis);
    }

    /**
     * 生成 JWT（指定 ID、主题、过期时间）
     */
    public static String createJWT(String id, String subject, Long ttlMillis) {
        SecretKey secretKey = generalKey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        long expMillis = nowMillis + (ttlMillis != null ? ttlMillis : JWT_TTL);
        Date expDate = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setSubject(subject)
                .setIssuer("sg")
                .setIssuedAt(now)
                .setExpiration(expDate)
                .signWith(secretKey, SignatureAlgorithm.HS256);
        return builder.compact();
    }

    /**
     * 生成安全的 HMAC 密钥
     */
    public static SecretKey generalKey() {
        // 直接使用明文字符串生成 HMAC-SHA 密钥（推荐）
        return Keys.hmacShaKeyFor(JWT_KEY.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * 解析 JWT
     */
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(jwt)
                .getBody();
    }
}