package com.platform.parent.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tqyao.
 */
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID = -3301605591108950415L;

    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";
    private static final String CLAIM_KEY_PASSWORD = "pwd";
    private static final String CLAIM_KEY_ID = "id";


//    @Value("${jwt.secret}")
    private static String secret;
//
//    @Value("${jwt.expiration}")
    private static Long expiration;
    private static String tokenHeader;
    private static String tokenHead;


    public String getPhoneFromToken(String token) {
        String phone;
        try {
            final Claims claims = getClaimsFromToken(token);
            phone = claims.getSubject();
        } catch (Exception e) {
            phone = null;
        }
        return phone;
    }

    public String getClaimKeyPassword(String token) {
        String password;
        final Claims claims = getClaimsFromToken(token);
        password = (String) claims.get(CLAIM_KEY_PASSWORD);
        return password;
    }

    public long getClaimKeyId(String token) {
//        long id;
        final Claims claims = getClaimsFromToken(token);
        int i =  (int)claims.get(CLAIM_KEY_ID);
//        int i = Integer.parseInt(s);
//        id =  (int) i;
        return i;
    }

    public Date getCreatedDateFromToken(String token) {
        Date created;
        try {
            final Claims claims = getClaimsFromToken(token);
            created = new Date((Long) claims.get(CLAIM_KEY_CREATED));
        } catch (Exception e) {
            created = null;
        }
        return created;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    /*private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }*/

    public String generateToken(String phone, String password, long id) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, phone);
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_PASSWORD, password);
        claims.put(CLAIM_KEY_ID, id);
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

   /* public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getCreatedDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && !isTokenExpired(token);
    }*/

    public String refreshToken(String token) {
        String refreshedToken;
        try {
            final Claims claims = getClaimsFromToken(token);
            claims.put(CLAIM_KEY_CREATED, new Date());
            refreshedToken = generateToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    public Boolean validateToken(String token, String phone) {
        final String phoneNumber = getPhoneFromToken(token);
        final Date created = getCreatedDateFromToken(token);
        //final Date expiration = getExpirationDateFromToken(token);
        return (phoneNumber.equals(phone) && !isTokenExpired(token));
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Long getExpiration() {
        return expiration;
    }

    public void setExpiration(Long expiration) {
        this.expiration = expiration;
    }

    public static String getTokenHeader() {
        return tokenHeader;
    }

    public static void setTokenHeader(String tokenHeader) {
        JwtTokenUtil.tokenHeader = tokenHeader;
    }

    public static String getTokenHead() {
        return tokenHead;
    }

    public static void setTokenHead(String tokenHead) {
        JwtTokenUtil.tokenHead = tokenHead;
    }
}
