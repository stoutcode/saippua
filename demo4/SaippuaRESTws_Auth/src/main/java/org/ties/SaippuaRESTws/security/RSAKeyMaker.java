package org.ties.SaippuaRESTws.security;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.lang.JoseException;


public class RSAKeyMaker {

    private RSAKeyMaker() {
    }
    
    private static RsaJsonWebKey secret;
    
    public static RsaJsonWebKey produce(){
        if(secret == null){
            try {
                secret = RsaJwkGenerator.generateJwk(2048);
            } catch (JoseException ex) {
                Logger.getLogger(RSAKeyMaker.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         
        System.out.println("RSA Key... "+ secret.hashCode());
        return secret;
    }
}
