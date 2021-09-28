package org.ties.SaippuaRESTws.exceptions;


public class CreateException extends RuntimeException {
	private static final long serialVersionUID = -1272553621676928691L;
		
		public CreateException() {
			super("Could not create new resource.");
		}
		
		public CreateException(String message) {
			super(message);
		}
}
