package com.qrsoft.util;

import com.qrsoft.common.R;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Calendar;
import java.util.Map;

/**
 * token工具类
 */
public class TokenUtil {

    private TokenUtil() {
    }

    /**
     * 密钥（至少32字节，后续可根据需求换成RSA加密）
     */
    private static final byte[] SECRET = "sarnath-sarnath-sarnath-sarnath.".getBytes();
    /**
     * token失效时间(分)
     */
    public static final int EXP_TIME = 24 * 60;

    /**
     * 生成token
     */
    public static String genToken(Map<String, Object> payloadMap) {
        // JwtBuilder的base64UrlEncoder默认Encoders.BASE64URL
        JwtBuilder jwtBuilder = Jwts.builder();
        // 设置载荷
        jwtBuilder.setClaims(payloadMap);
        // 设置失效时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, EXP_TIME);
        jwtBuilder.setExpiration(calendar.getTime());
        // 设置签名
        jwtBuilder.signWith(Keys.hmacShaKeyFor(SECRET));
        return jwtBuilder.compact();
    }

    /**
     * 验证token有效性
     */
    public static R valid(String token) {
        try {
            JwtParser jwtParser = Jwts.parser();
            // 设置时钟偏移
            jwtParser.setAllowedClockSkewSeconds(3 * 60);
            // 设置签名
            jwtParser.setSigningKey(SECRET);
            Map<String, Object> payload = jwtParser.parseClaimsJws(token).getBody();
            return R.ok(payload);
        } catch (Exception e) {
            return R.error(e.getMessage());
        }
    }
}

