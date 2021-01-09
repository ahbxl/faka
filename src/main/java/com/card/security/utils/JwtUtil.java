package com.card.security.utils;

import com.card.entity.vo.CheckResult;
import com.card.security.constant.SystemConstant;
import io.jsonwebtoken.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Data
@Component
@Slf4j
public class JwtUtil {
    // 秘钥
    private String secret = "aHR0cHM6Ly9teS5vc2NoaW5hLm5ldC91LzM2ODE4Njg=";

    // 有效时间
    private Long expire = 5184000L;

    // 用户凭证
    private String header = "Authorization";

    // 签发者
    private String issuer = "faka";

    /**
     * 生成token签名
     *
     * @param subject
     * @return
     */
    public String generateToken(String subject) {
        final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        Date now = new Date();
        // 过期时间
        Date expireDate = new Date(now.getTime() + expire * 1000);
        //Create the Signature SecretKey
        final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(getSecret().getBytes()));
        final Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
        final Map<String, Object> headerMap = new HashMap<>();
        headerMap.put("alg", "HS256");
        headerMap.put("typ", "JWT");
        //add JWT Parameters
        final JwtBuilder builder = Jwts.builder()
                .setHeaderParams(headerMap)
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .setIssuer(getIssuer())
                .signWith(signatureAlgorithm, signingKey);
        return builder.compact();

    }


    /**
     * 验证JWT
     *
     * @param jwtStr
     * @return
     */
    public CheckResult validateJWT(String jwtStr) {
        CheckResult checkResult = new CheckResult();
        try {
            Claims claims = parseToken(jwtStr);
            checkResult.setSuccess(true);
            checkResult.setClaims(claims);
        } catch (ExpiredJwtException e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_EXPIRE);
            checkResult.setSuccess(false);
        } catch (Exception e) {
            checkResult.setErrCode(SystemConstant.JWT_ERRCODE_FAIL);
            checkResult.setSuccess(false);
        }
        return checkResult;
    }

    /**
     * 判断token是否过期
     *
     * @param expiration
     * @return
     */
    public boolean isExpired(Date expiration) {
        return expiration.before(new Date());
    }

    /**
     * 解析token
     *
     * @param token token
     * @return
     */
    public Claims parseToken(String token) {
        Claims claims;
        try {
            final byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(Base64.getEncoder().encodeToString(getSecret().getBytes()));
            claims = Jwts.parser().setSigningKey(apiKeySecretBytes).parseClaimsJws(token).getBody();
            log.info("Parse JWT token by: ID: {}, Subject: {}, Issuer: {}, Expiration: {}", claims.getId(), claims.getSubject(), claims.getIssuer(), claims.getExpiration());
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e) {
            log.error("Parse JWT error " + e.getMessage());
            return null;
        }
        return claims;
    }
}