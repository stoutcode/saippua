package org.ties.SaippuaRESTws.exceptions;

public class DeleteException extends RuntimeException {
private static final long serialVersionUID = -1272553621675928689L;
	
	public DeleteException() {
		super("Could not delete resource..");
	}
	
	public DeleteException(String message) {
		super(message);
	}
}
