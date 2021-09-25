package org.ties.SaippuaRESTws.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode; //own custom error code
	private String documentation; //link to documentation regarding a error and it’s resolving
	
	public ErrorMessage(){}
	public ErrorMessage(String errorMessage, int errorCode, String documentation) {
		super();
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
		this.documentation = documentation;
	}
	
	public String getMessage() {
		return this.errorMessage;
	}
	
	public int getCode() {
		return this.errorCode;
	}
	
	public String getDocumentation() {
		return this.documentation;
	}
	
	public void setMessage(String message) {
		this.errorMessage = message;
	}
	
	public void setCode(int code) {
		this.errorCode = code;
	}
	
	public void setDocumentation(String doc) {
		this.documentation = doc;
	}
	
// below generate getters and setters for all the variable of the class…
}
