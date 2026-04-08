package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@SpringBootTest
class SpringSecurityExampleApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 创建 token
     */
    @Test
    public void testCreateJWT() {
        String secret = "luoyu1234567890123456789012345678"; // 32 字节
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        SecretKey key2 = Keys.secretKeyFor(SignatureAlgorithm.HS256); // 自动生成 256 位密钥

        JwtBuilder jwtBuilder = Jwts.builder()
                .setId("9527") // 设置唯一 id 用户 id
                .setSubject("hejiayun_community") // 主体内容
                .setIssuedAt(new Date()) // 签约时间
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .claim("role", "admin")
                .signWith(key, SignatureAlgorithm.HS256);// 设置签名 使用 HS256 算法  并且设置 secretKey

        // 压缩成 string 格式
        String jws = jwtBuilder.compact();
        System.out.println(jws);
    }

    /**
     * 解析 token
     */
    @Test
    public void testCreateAndParse() {
        // 固定密钥（≥32字节）
        String secret = "luoyu1234567890123456789012345678";
        SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

        // 生成 token
        String jws = Jwts.builder()
                .setId("9527") // 设置唯一 id 用户 id
                .setSubject("hejiayun_community") // 主体内容
                .setIssuedAt(new Date()) // 签约时间
                .setExpiration(new Date(System.currentTimeMillis() + 3600 * 1000)) // 设置过期时间
                .claim("role", "admin")
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        System.out.println("Token: " + jws);

        // 解析 token
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jws)
                .getBody();
        System.out.println("Claims: " + claims);
    }

    /**
     * 自定义 claims
     */
    public void testJwt(){
        String secret = "luoyu1234567890123456789012345678";
    }
}
