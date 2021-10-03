package org.ties.SaippuaRESTws.exceptions;

public class ForbiddenException extends RuntimeException {
	private static final long serialVersionUID = -1222553621676928689L;

	public ForbiddenException() {
		super("This user cannot access this resource.");
	}
	
	public ForbiddenException(String message) {
		super(message);
	}
}
