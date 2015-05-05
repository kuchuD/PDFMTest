package com.macy.dto;

public class FeedFile {
	
	private String feedType;
	private String feedName;
	private String fileName;
	private String date;
	private String protocol;
	private String serverName;
	private String pathName;
	private String username;
	private String password;
	
	public String getFeedType() {
		return feedType;
	}
	public void setFeedType(String feedType) {
		this.feedType = feedType;
	}
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getPathName() {
		return pathName;
	}
	public void setPathName(String pathName) {
		this.pathName = pathName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "FeedFile [feedType=" + feedType + ", feedName=" + feedName
				+ ", fileName=" + fileName + ", date=" + date + ", protocol="
				+ protocol + ", serverName=" + serverName + ", pathName="
				+ pathName + ", username=" + username + ", password="
				+ password + "]";
	}
}
