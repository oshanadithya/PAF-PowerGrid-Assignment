package com;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Complaint {
	
	private String name;
	private String email;
	private String contact;
	private String complaint;
	
	public String getName() {
		return name;
	}
	public String getEmail() {
		return email;
	}
	public String getContact() {
		return contact;
	}
	public String getComplaint() {
		return complaint;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}
	
	
}
