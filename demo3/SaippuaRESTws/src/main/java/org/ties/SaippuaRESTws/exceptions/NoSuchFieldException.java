package org.ties.SaippuaRESTws.exceptions;

public class NoSuchFieldException extends RuntimeException{
	private static final long serialVersionUID = -7772553621676928689L;
	
	public NoSuchFieldException(String message){
		super(message);
	}
}
