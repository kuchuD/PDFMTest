package com.macy.test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.macy.dto.FeedData;
import com.macy.dto.Login;
import com.macy.property.LoadProperty;

public class CancelFeedDataTest {

	public static final Logger logger = Logger.getLogger(CancelFeedDataTest.class);
	private static WebDriver driver;
	Login login = new Login();
	FeedData feedBO = new FeedData();
	LoadProperty prop = new LoadProperty();

	public CancelFeedDataTest() {
		// TODO Auto-generated constructor stub
		logger.info("Constructor of CancelFeedDataTest");
		BasicConfigurator.configure();
	}

	@BeforeClass
	public static void initiateBrowser(){
		driver = new FirefoxDriver();
	}

	@Test
	public void addFeed(){
		registerApp();
		//Click on feed
		logger.info("clicking on corresponding partner");
		setFeedData();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		//Select Partner 
		driver.findElement(By.xpath(feedBO.getPartnerName())).click();

		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Select Feed
		driver.findElement(By.xpath(feedBO.getFeedName())).click();

		//Click Data Tab
		driver.findElement(By.linkText("Data")).click();
		
		//Click on add field
		driver.findElement(By.linkText("Add Data Field")).click();
		
		//Click source data
		WebElement source_data = driver.findElement(By.name("pageFields[2].fieldReference"));
  		Select dropdown_sourceData = new Select(source_data);
  		dropdown_sourceData.selectByValue(feedBO.getSourceData());
  		
  		//Enter header value
  		driver.findElement(By.name("pageFields[2].title")).sendKeys(feedBO.getHeader());
  		
  		//Enter Groovy
  		//driver.findElement(By.name("pageFields[2].before")).sendKeys(feedBO.getGroovy());
  		
  		//Click on save
  		driver.findElement(By.name("cancel")).click();
		
	}

	private void setFeedData() {
		// TODO Auto-generated method stub
		logger.info("Going to read all feed data details");
		try{
			//File file = new File("C:\\sumanta\\PDFM\\PDFM\\src\\PDFMFeedFile.xml");
			File file = new File(prop.loadFeedDataPath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			logger.info("Root element " + doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("FeedData");

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element fstElmnt = (Element) fstNode;
					NodeList prtnrNmElmntLst = fstElmnt.getElementsByTagName("partnerName");
					Element prtnrNmElmnt = (Element) prtnrNmElmntLst.item(0);
					NodeList prtnrNm = prtnrNmElmnt.getChildNodes();
					feedBO.setPartnerName(((Node) prtnrNm.item(0)).getNodeValue());
					logger.info("Select Partner Name : "  + feedBO.getPartnerName());

					NodeList fdNmElmntLst = fstElmnt.getElementsByTagName("feedName");
					Element fdNmElmnt = (Element) fdNmElmntLst.item(0);
					NodeList fdNm = fdNmElmnt.getChildNodes();
					feedBO.setFeedName(((Node) fdNm.item(0)).getNodeValue());
					logger.info("Enter Feed Name : " + feedBO.getFeedName());

					NodeList srcDataElmntLst = fstElmnt.getElementsByTagName("sourceData");
					Element srcDataElmnt = (Element) srcDataElmntLst.item(0);
					NodeList srcData = srcDataElmnt.getChildNodes();
					feedBO.setSourceData(((Node) srcData.item(0)).getNodeValue());
					logger.info("Select source data : " + feedBO.getSourceData());

					NodeList headerLst = fstElmnt.getElementsByTagName("header");
					Element headerElmnt = (Element) headerLst.item(0);
					NodeList header = headerElmnt.getChildNodes();
					feedBO.setHeader(((Node) header.item(0)).getNodeValue());
					logger.info("Enter header value : " + feedBO.getHeader());

					
					NodeList groovyElmntLst = fstElmnt.getElementsByTagName("groovy");
					Element groovyElmnt = (Element) groovyElmntLst.item(0);
					NodeList groovy = groovyElmnt.getChildNodes();
					feedBO.setGroovy(((Node) groovy.item(0)).getNodeValue());
					logger.info("Enter Groovy logic: " + feedBO.getGroovy());

				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

	public void registerApp(){
		logger.info("Registering PDFM");
		driver.get("https://mdc1vr1093/login");
		loginRead();
		logger.info("logged into PDFM");
		driver.findElement(By.id("name-field")).sendKeys(login.getUserName());
		driver.findElement(By.id("password-field")).sendKeys(login.getPassword());
		driver.findElement(By.xpath(login.getLink())).click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loginRead() {

		try {
			//File file = new File("C:\\sumanta\\PDFM\\PDFM\\src\\PDFMLogin.xml");
			File file = new File(prop.loadLoginPath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			logger.info("Root element " + doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("user");


			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					Element fstElmnt = (Element) fstNode;
					NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("username");
					Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
					NodeList fstNm = fstNmElmnt.getChildNodes();
					login.setUserName(((Node) fstNm.item(0)).getNodeValue());


					NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("password");
					Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
					NodeList lstNm = lstNmElmnt.getChildNodes();
					login.setPassword(((Node) lstNm.item(0)).getNodeValue());


					NodeList linkElmntLst = fstElmnt.getElementsByTagName("link");
					Element linkElmnt = (Element) linkElmntLst.item(0);
					NodeList link = linkElmnt.getChildNodes();
					login.setLink(((Node) link.item(0)).getNodeValue());

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterClass
	public static void signOut(){
		driver.close();
	}
}
