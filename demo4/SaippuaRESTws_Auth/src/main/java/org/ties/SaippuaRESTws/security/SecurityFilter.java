package org.ties.SaippuaRESTws.security;

import java.io.IOException;
import java.util.Arrays;

import javax.ws.rs.container.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.consumer.InvalidJwtException;
import org.jose4j.jwt.consumer.JwtConsumer;
import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.ties.SaippuaRESTws.models.ErrorMessage;

@Provider
public class SecurityFilter implements ContainerRequestFilter {
	SecurityFunctions secFuncs = new SecurityFunctions();
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		
		
		String authHeaderVal = requestContext.getHeaderString("Authorization");	
		
		if (authHeaderVal  == null) {
			authHeaderVal = "noAuth";
		}
		System.out.println("request filter invoked...");
		
		//Checks for jwt token
		if (authHeaderVal.startsWith("Bearer")) {
            try {
            	final String[] creds = validate(authHeaderVal.split(" ")[1]); //[0] = username & [1] = password
            	System.out.println("Authenticating with JWT..");
                System.out.println("JWT being tested:\n" + authHeaderVal.split(" ")[1]);
                
            	if ( requestContext.getUriInfo().getPath().contains("snippets") ) {
        			secFuncs.handleAuth(requestContext, creds[0], creds[1], Arrays.asList("worker", "manager", "admin"));
        		}
        		
        		if ( (requestContext.getUriInfo().getPath().contains("languages") || requestContext.getUriInfo().getPath().contains("tasks"))
        				&& ( requestContext.getMethod().equals("DELETE") || requestContext.getMethod().equals("POST") || requestContext.getMethod().equals("PUT") ) ) {
        			
        			secFuncs.handleAuth(requestContext, creds[0], creds[1], Arrays.asList("manager", "admin"));
        		}
        		
        		if ( requestContext.getUriInfo().getPath().contains("users") ) {
        			secFuncs.handleAuth(requestContext, creds[0], creds[1], Arrays.asList("admin"));
        		}
            	
            }catch (InvalidJwtException ex) {
                System.out.println("JWT validation failed");

                requestContext.setProperty("auth-failed", true);                
                ErrorMessage errorMessage = new ErrorMessage("User cannot access the resource.", 401,
                		"http://myDocs.org");

                Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                		.entity(errorMessage)
                		.build();

                requestContext.abortWith(unauthorizedStatus); 		
            }
		
		}
		
		
		if ( requestContext.getUriInfo().getPath().contains("authenticate") ) {
		
			System.out.println("Checking credentials...");
			secFuncs.login(requestContext);
		}
		
		// Let all GET requests not directed to users past the filter.
		if (requestContext.getMethod().equals("GET")) {
			if ( requestContext.getUriInfo().getPath().contains("users") ) {
				ErrorMessage errorMessage = new ErrorMessage("User cannot access the resource.", 401,
                		"http://myDocs.org");

                Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
                		.entity(errorMessage)
                		.build();

                requestContext.abortWith(unauthorizedStatus); 	
			}
			System.out.println("GET request bypassed security filter...");
			return;
		}					
		
	}
	
	private String[] validate(String jwt) throws InvalidJwtException {
		String[] ret = new String[2];
        RsaJsonWebKey rsaJsonWebKey = RSAKeyMaker.produce();

        System.out.println("RSA hash code... " + rsaJsonWebKey.hashCode());

        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
                .setRequireSubject() // the JWT must have a subject claim
                .setVerificationKey(rsaJsonWebKey.getKey()) // verify the signature with the public key
                .build(); // create the JwtConsumer instance

        try {
            //  Validate the JWT and process it to the Claims
            JwtClaims jwtClaims = jwtConsumer.processToClaims(jwt);
            ret[0] = (String) jwtClaims.getClaimValue("username");
            ret[1] = (String) jwtClaims.getClaimValue("password");
           
            System.out.println("JWT validation succeeded! " + jwtClaims);
        } catch (InvalidJwtException e) {
            e.printStackTrace(); //on purpose
            throw e;
        }

        return ret;
    }
}
