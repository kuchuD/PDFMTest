package com.macy.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperty {
	private Properties configProp = new Properties();

	public String loadLoginPath() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pathName = configProp.getProperty("loginPath");
		return pathName;
	}
	
	public String loadPartnerPath() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pathName = configProp.getProperty("partnerPath");
		return pathName;
	}
	
	public String loadFeedFilePath() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pathName = configProp.getProperty("feedFilePath");
		return pathName;
	}
	
	public String loadFeedDataPath() {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("config.properties");
		try {
			configProp.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String pathName = configProp.getProperty("feedDataPath");
		return pathName;
	}
}
