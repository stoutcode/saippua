package org.ties.SaippuaRESTws.exceptions;

public class AuthorizationException extends RuntimeException {
	private static final long serialVersionUID = -1272552621676928689L;
	
	public AuthorizationException() {
		super("Authorization unsuccessful.");
	}
	
	public AuthorizationException(String message) {
		super(message);
	}
}
