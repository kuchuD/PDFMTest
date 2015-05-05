package com.macy.dto;

public class Partner {
	private String partnerName;
	private String partnerType;
	private String pricingDays;
	private String active;
	private String protocol;
	private String server;
	private String path;
	private String userName;
	private String password;
	
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public String getPricingDays() {
		return pricingDays;
	}
	public void setPricingDays(String pricingDays) {
		this.pricingDays = pricingDays;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public String getServer() {
		return server;
	}
	public void setServer(String server) {
		this.server = server;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
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
		return "Partner [partnerName=" + partnerName + ", partnerType="
				+ partnerType + ", pricingDays=" + pricingDays + ", active="
				+ active + ", protocol=" + protocol + ", server=" + server
				+ ", path=" + path + ", userName=" + userName + ", password="
				+ password + "]";
	}
}
