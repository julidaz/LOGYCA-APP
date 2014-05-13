package com.logyca;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String user_name;
	private String user_mail;
	
	public User(String uSER) {
		// TODO Auto-generated constructor stub
		this.user_mail = uSER;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_mail() {
		return user_mail;
	}
	public void setUser_mail(String user_mail) {
		this.user_mail = user_mail;
	}
}
