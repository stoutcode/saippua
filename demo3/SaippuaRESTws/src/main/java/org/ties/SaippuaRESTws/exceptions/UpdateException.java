package org.ties.SaippuaRESTws.exceptions;


public class UpdateException extends RuntimeException {
private static final long serialVersionUID = -1272553621676928681L;
	
	public UpdateException() {
		super("Could not update resource.");
	}
	
	public UpdateException(String message) {
		super(message);
	}
}
