package org.ties.SaippuaRESTws.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String errorMessage;
	private int errorCode; 
	private String documentation; 
	
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
}
