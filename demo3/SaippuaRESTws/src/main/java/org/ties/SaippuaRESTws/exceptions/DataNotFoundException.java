package org.ties.SaippuaRESTws.exceptions;


public class DataNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -1272553621676928689L;
	
	public DataNotFoundException(String message){
		super(message);
	}
}
