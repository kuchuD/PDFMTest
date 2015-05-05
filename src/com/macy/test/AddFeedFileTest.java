package com.macy.test;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

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

import com.macy.dto.FeedFile;
import com.macy.dto.Login;
import com.macy.dto.Partner;
import com.macy.property.LoadProperty;


public class AddFeedFileTest {

	public static final Logger logger = Logger.getLogger(AddFeedFileTest.class);
	private static WebDriver driver;
	Login login = new Login();
	FeedFile feedBO = new FeedFile();
	LoadProperty prop = new LoadProperty();
	
	public AddFeedFileTest() {
		// TODO Auto-generated constructor stub
		logger.info("Constructor of AddFeedFileTest");
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
	   driver.findElement(By.xpath("//a[@href='/datafeed/create/355']")).click();
	   try {
		Thread.sleep(1000);
	   } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	   
	   setFeedFile();
	   
	   //Select Feed Type
		WebElement feed_type = driver.findElement(By.className("partnerType"));
  		Select dropdown_feedType = new Select(feed_type);
  		dropdown_feedType.selectByValue(feedBO.getFeedType());
 	   
 	   //Create DataFeed
 	   WebElement create_feed = driver.findElement(By.name("create"));
 	   create_feed.click();
 	   
 	    //Enter Feed Name
 	   driver.findElement(By.id("name")).sendKeys(feedBO.getFeedName());
 	   
 	   //Set File Name
 	   driver.findElement(By.id("payload.filename")).sendKeys(feedBO.getFileName());
 	   
 	   //Select Date
 	   driver.findElement(By.id(feedBO.getDate())).click();
 	   
 	   //Select transport protocol
 	   WebElement transport_protocol = driver.findElement(By.id("trgtTransportType"));
 	   Select dropdown_protocol = new Select(transport_protocol);
 	   dropdown_protocol.selectByValue(feedBO.getProtocol());
 	   
 	   //Select Server Name
 	   driver.findElement(By.id("trgtHost")).sendKeys(feedBO.getServerName());
 	   
 	   //Select Path name
 	   driver.findElement(By.id("trgtPath")).sendKeys(feedBO.getPathName());
 	   
 	   //Select User Name
 	   driver.findElement(By.id("trgtUsername")).sendKeys(feedBO.getUsername());
 	   
 	   //Select Password
 	   driver.findElement(By.id("trgtPassword")).sendKeys(feedBO.getPassword());
 	   
 	   //Click on save and return
 	   driver.findElement(By.name("submit")).click();
 	   
	}

    private void setFeedFile() {
		// TODO Auto-generated method stub
		logger.info("Going to read all feed file details");
		try{
			//File file = new File("C:\\sumanta\\PDFM\\PDFM\\src\\PDFMFeedFile.xml");
			File file = new File(prop.loadFeedFilePath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db;
			db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			logger.info("Root element " + doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("FeedFile");
			
			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					  Element fstElmnt = (Element) fstNode;
				      NodeList fdTypElmntLst = fstElmnt.getElementsByTagName("feedType");
				      Element fdTypElmnt = (Element) fdTypElmntLst.item(0);
				      NodeList fdTyp = fdTypElmnt.getChildNodes();
				      feedBO.setFeedType(((Node) fdTyp.item(0)).getNodeValue());
				      logger.info(" Select Feed Type : "  + feedBO.getFeedType());
				      
				      NodeList fdNmElmntLst = fstElmnt.getElementsByTagName("feedName");
				      Element fdNmElmnt = (Element) fdNmElmntLst.item(0);
				      NodeList fdNm = fdNmElmnt.getChildNodes();
				      feedBO.setFeedName(((Node) fdNm.item(0)).getNodeValue());
				      logger.info("Enter Feed Name : " + feedBO.getFeedName());
				      
				      NodeList filNmElmntLst = fstElmnt.getElementsByTagName("filename");
				      Element filNmElmnt = (Element) filNmElmntLst.item(0);
				      NodeList filNm = filNmElmnt.getChildNodes();
				      feedBO.setFileName(((Node) filNm.item(0)).getNodeValue());
				      logger.info("Enter File Name : " + feedBO.getFileName());
				      
				      NodeList dateLst = fstElmnt.getElementsByTagName("date");
				      Element dateElmnt = (Element) dateLst.item(0);
				      NodeList date = dateElmnt.getChildNodes();
				      feedBO.setDate(((Node) date.item(0)).getNodeValue());
				      logger.info("Select Date Check Box : " + feedBO.getDate());
				      
				      //Protocol
				      NodeList prtclElmntLst = fstElmnt.getElementsByTagName("protocol");
				      Element prtclElmnt = (Element) prtclElmntLst.item(0);
				      NodeList prtcl = prtclElmnt.getChildNodes();
				      feedBO.setProtocol(((Node) prtcl.item(0)).getNodeValue());
				      logger.info("Protocol : " + feedBO.getProtocol());
				      
				      //Server Name
				      NodeList srvrElmntLst = fstElmnt.getElementsByTagName("serverName");
				      Element srvrElmnt = (Element) srvrElmntLst.item(0);
				      NodeList srvr = srvrElmnt.getChildNodes();
				      feedBO.setServerName(((Node) srvr.item(0)).getNodeValue());
				      logger.info("Server Name : " + feedBO.getServerName());
				      
				      //Path Name
				      NodeList pthElmntLst = fstElmnt.getElementsByTagName("pathName");
				      Element pthElmnt = (Element) pthElmntLst.item(0);
				      NodeList pth = pthElmnt.getChildNodes();
				      feedBO.setPathName(((Node) pth.item(0)).getNodeValue());
				      logger.info("Path Name : " + feedBO.getPathName());
				      
				      //UserName
				      NodeList usrNamElmntLst = fstElmnt.getElementsByTagName("userName");
				      Element usrNamElmnt = (Element) usrNamElmntLst.item(0);
				      NodeList usrNam = usrNamElmnt.getChildNodes();
				      feedBO.setUsername(((Node) usrNam.item(0)).getNodeValue());
				      logger.info("User name : " + feedBO.getUsername());
				      
				      //Password
				      NodeList pwdElmntLst = fstElmnt.getElementsByTagName("password");
				      Element pwdElmnt = (Element) pwdElmntLst.item(0);
				      NodeList pwd = pwdElmnt.getChildNodes();
				      feedBO.setPassword(((Node) pwd.item(0)).getNodeValue());
				      logger.info("Password : " + feedBO.getPassword());
				      
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
