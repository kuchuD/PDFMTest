package com.macy.test;



import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.macy.dto.Login;
import com.macy.property.LoadProperty;


public class LoginTest {
	
	public static final Logger logger = Logger.getLogger(LoginTest.class);
	private static WebDriver driver;
    Login loginBO = new Login();
    LoadProperty prop = new LoadProperty();
    
    public LoginTest() {
		// TODO Auto-generated constructor stub
    	BasicConfigurator.configure();
    	logger.info("Constructor call Login Test");
    }

	@BeforeClass
	public static void initiateBrowser(){
		logger.info("initiating browser");
		driver = new FirefoxDriver();
	}

	@Test
	public void registerApp(){
		logger.info("registering login page of current user");
		driver.get("https://mdc1vr1093/login");
		loginRead();
		driver.findElement(By.id("name-field")).sendKeys(loginBO.getUserName());
		driver.findElement(By.id("password-field")).sendKeys(loginBO.getPassword());
		driver.findElement(By.xpath(loginBO.getLink())).click();
		driver.findElement(By.className("btn-logout")).click();
		//utility.loginAsUser(); 
    }

	public void loginRead() {
    
		try {
			//File file = new File("C:\\sumanta\\PDFM\\PDFM\\src\\PDFMLogin.xml");
			File file = new File(prop.loadLoginPath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			
			NodeList nodeLst = doc.getElementsByTagName("user");
			

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					  Element fstElmnt = (Element) fstNode;
				      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("username");
				      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				      NodeList fstNm = fstNmElmnt.getChildNodes();
				      loginBO.setUserName(((Node) fstNm.item(0)).getNodeValue());
				      
				      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("password");
				      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
				      NodeList lstNm = lstNmElmnt.getChildNodes();
				      loginBO.setPassword(((Node) lstNm.item(0)).getNodeValue());
				      
				      NodeList linkElmntLst = fstElmnt.getElementsByTagName("link");
				      Element linkElmnt = (Element) linkElmntLst.item(0);
				      NodeList link = linkElmnt.getChildNodes();
				      loginBO.setLink(((Node) link.item(0)).getNodeValue());
				      
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void signOut(){
		logger.info("closing PDFM");
		driver.close();
	}

}
