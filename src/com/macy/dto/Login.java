package com.macy.dto;



public class Login {
	
	
	private String userName;
	private String password;
	private String link;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public Login(){
		
	}
	public Login(String userName,String password,String link) {
		this.userName = userName;
		this.password = password;
		this.link = link;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "Login [userName=" + userName + ", password=" + password + " ]";
	}
	
	
}
