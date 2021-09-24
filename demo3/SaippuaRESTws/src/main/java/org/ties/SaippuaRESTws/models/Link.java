package org.ties.SaippuaRESTws.models;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Link {
	private String href;
	private String rel;
	
	public Link(String href, String rel) {
		this.href = href;
		this.rel = rel;
	}
	
	public void setHref(String href) {
		this.href = href;
	}
	
	public void setRel(String rel) {
		this.rel = rel;;
	}
	
	
	public String getHref() {
		return this.href;
	}
	
	public String getRel() {
		return this.rel;
	}

}
