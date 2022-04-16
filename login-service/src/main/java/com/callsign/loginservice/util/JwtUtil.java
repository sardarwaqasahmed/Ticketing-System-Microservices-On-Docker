package com.callsign.loginservice.util;

import io.jsonwebtoken.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Calendar;
import java.util.Date;
/**
	Author: waqas ahmed
	Date  : APR 14, 2022
**/
@Component
@AllArgsConstructor
@NoArgsConstructor
@Data
@Slf4j
public class JwtUtil implements Serializable {

    private static final long serialVersionUID = -2550198745626007488L;
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
    private final long expirationMillSec = 300000;  // 5 min

    @Value("${jwt.secret}")
    private String jwtSecret;

    /** <p> This method will generate a token for web service. Server will sign the header and payload using signing key and add token expiry.</p>
     * <p> Server will add claims that belong to the user</p>
     * <p> Symmetric key is used in this POC</p>
     */
    public String generateJWTToken(String userName, Long id) {

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Claims claims = Jwts.claims().setSubject(userName).setId(String.valueOf(id));

        Calendar exp = Calendar.getInstance();
        exp.setTimeInMillis(System.currentTimeMillis() + expirationMillSec);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date()) // now
                .setExpiration(exp.getTime()) // Expiration date
                .signWith(signatureAlgorithm, signingKey)
                .compact();
    }

    /** This method will validate the jwt token using the secret key and return true or false.
     * @param token
     * @return
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature - {}", ex.getMessage());
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token - {}", ex.getMessage());
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token - {}", ex.getMessage());
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token - {}", ex.getMessage());
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty - {}", ex.getMessage());
        }
        return false;
    }

    /**
     * @param token
     * @return
     */
    public String getUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}