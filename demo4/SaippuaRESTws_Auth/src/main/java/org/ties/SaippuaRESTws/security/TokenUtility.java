package org.ties.SaippuaRESTws.security;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

public class TokenUtility {

    public static String buildJWT(String user, String password) {
        RsaJsonWebKey rsaJsonWebKey = RSAKeyMaker.produce();
        System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtClaims claims = new JwtClaims();
        claims.setSubject(user); 
        claims.setClaim("username", user);
        claims.setClaim("password", password);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(rsaJsonWebKey.getPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String jwt = null;
        try {
            jwt = jws.getCompactSerialization();
        } catch (JoseException ex) {
        	System.out.println(ex);
        }

        System.out.println("Claim:\n" + claims);
        System.out.println("JWS:\n" + jws);
        System.out.println("JWT:\n" + jwt);

        return jwt;
    }
}
