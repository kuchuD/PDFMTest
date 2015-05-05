package com.macy.dto;

public class FeedData {
	private String partnerName;
	private String feedName;
	private String sourceData;
	private String header;
	private String groovy;
	
	public String getSourceData() {
		return sourceData;
	}
	public void setSourceData(String sourceData) {
		this.sourceData = sourceData;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getGroovy() {
		return groovy;
	}
	public void setGroovy(String groovy) {
		this.groovy = groovy;
	}
	
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getFeedName() {
		return feedName;
	}
	public void setFeedName(String feedName) {
		this.feedName = feedName;
	}
	@Override
	public String toString() {
		return "FeedData [partnerName=" + partnerName + ", feedName="
				+ feedName + ", sourceData=" + sourceData + ", header="
				+ header + ", groovy=" + groovy + "]";
	}
}
