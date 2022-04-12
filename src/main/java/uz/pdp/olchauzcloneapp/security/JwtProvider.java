package uz.pdp.olchauzcloneapp.security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.util.Date;

@Component
public class JwtProvider {

    @Value("${secret-key}")
    private String secretKey;

    @Value("${expire_access_token}")
    private long accessTokenExpire;

    @Value("${refresh_token}")
    private long refreshTokenExpire;

    public String generateToken(String email, boolean forAccess) {
        Date expire = new Date(System.currentTimeMillis() + (forAccess ? accessTokenExpire : refreshTokenExpire));
        return Jwts
                .builder()
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(expire)
                .compact();
    }

    public String getEmail(String token) {
        try {
            return Jwts
                    .parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
           e.printStackTrace();
        }
        return null;
    }

/*    public void validateToken(String token){
         Jwts
                .parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token);
    }*/
}
