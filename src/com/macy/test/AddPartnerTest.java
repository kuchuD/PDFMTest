package com.macy.test;

import java.io.File;

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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import com.macy.dto.Login;
import com.macy.dto.Partner;
import com.macy.property.LoadProperty;

public class AddPartnerTest {
public static final Logger logger = Logger.getLogger(AddPartnerTest.class);
	
	private static WebDriver driver;
	Login loginBO = new Login();
	Partner partnerBO = new Partner();
	LoadProperty prop = new LoadProperty();
	
    public AddPartnerTest() {
		// TODO Auto-generated constructor stub
    	logger.info("Constructor call add partner Test");
    	BasicConfigurator.configure();
	}

	@BeforeClass
	public static void initiateBrowser(){
		logger.info("initiating browser");
		driver = new FirefoxDriver();
	}

    public void registerApp(){
    	logger.info("Registering PDFM");
		driver.get("https://mdc1vr1093/login");
		loginRead();
		driver.findElement(By.id("name-field")).sendKeys(loginBO.getUserName());
		driver.findElement(By.id("password-field")).sendKeys(loginBO.getPassword());
		driver.findElement(By.xpath("//input[@value='Login' and @type='submit']")).click();
		
	}

	@Test
	public void addPartner(){
		String save = "//input[@value='Save'][@type='submit']";
		registerApp();
        setPartnerField();
        
		//click partner
        driver.findElement(By.linkText("Add New Partner")).click();
        
      //Add partner type
      		driver.findElement(By.id("name")).sendKeys(partnerBO.getPartnerName());

      		//Select Radio of type
      		WebElement partnerType_element_radio = driver.findElement(By.id(partnerBO.getPartnerType()));
      		partnerType_element_radio.click();

      		//Select drop down
      		WebElement pricing_days = driver.findElement(By.id(partnerBO.getPricingDays()));
      		Select dropdown_days = new Select(pricing_days);
      		dropdown_days.selectByValue("5");

      		//Select radio of active type
      		WebElement activation_type = driver.findElement(By.id(partnerBO.getActive()));
      		activation_type.click();

      		//FTP or SFTP Details
      		WebElement transport_protocol = driver.findElement(By.id(partnerBO.getProtocol()));
      		Select dropdown_protocol = new Select(transport_protocol);
      		dropdown_protocol.selectByValue("FtpOutput");

      		driver.findElement(By.id("trgtHost")).sendKeys(partnerBO.getServer());
      		driver.findElement(By.id("trgtPath")).sendKeys(partnerBO.getPath());
      		driver.findElement(By.id("trgtUsername")).sendKeys(partnerBO.getUserName());
      		driver.findElement(By.id("trgtPassword")).sendKeys(partnerBO.getPassword());

      		driver.findElement(By.xpath(save)).click();
      		driver.findElement(By.className("btn-logout")).click();
	}
	
	private void setPartnerField() {
		// TODO Auto-generated method stub
		try {
		//File file = new File("C:\\sumanta\\LunaWorkspace\\PDFMACY\\src\\PDFMPartner.xml");
	    File file = new File(prop.loadPartnerPath());
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);
		doc.getDocumentElement().normalize();
		//logger.info("Root element " + doc.getDocumentElement().getNodeName());
		NodeList nodeLst = doc.getElementsByTagName("partner");
		//logger.info("Information of all employees");

		for (int s = 0; s < nodeLst.getLength(); s++) {

			Node fstNode = nodeLst.item(s);

			if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

				  Element fstElmnt = (Element) fstNode;
			      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("partnername");
			      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
			      NodeList fstNm = fstNmElmnt.getChildNodes();
			      partnerBO.setPartnerName(((Node) fstNm.item(0)).getNodeValue());
			      logger.info("Partner Name : "  + partnerBO.getPartnerName());
			      
			      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("partnertype");
			      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
			      NodeList lstNm = lstNmElmnt.getChildNodes();
			      partnerBO.setPartnerType(((Node) lstNm.item(0)).getNodeValue());
			      logger.info("partnertype : " + partnerBO.getPartnerType());
			      
			      NodeList prcngDaysElmntLst = fstElmnt.getElementsByTagName("pricingdays");
			      Element prcngDaysElmnt = (Element) prcngDaysElmntLst.item(0);
			      NodeList prcngDays = prcngDaysElmnt.getChildNodes();
			      partnerBO.setPricingDays(((Node) prcngDays.item(0)).getNodeValue());
			      logger.info("Pricing days : " + partnerBO.getPricingDays());
			      
			      NodeList activeElmntLst = fstElmnt.getElementsByTagName("active");
			      Element activeElmnt = (Element) activeElmntLst.item(0);
			      NodeList active = activeElmnt.getChildNodes();
			      partnerBO.setActive(((Node) active.item(0)).getNodeValue());
			      logger.info("active type : " + partnerBO.getActive());
			      
			      //Protocol
			      NodeList prtclElmntLst = fstElmnt.getElementsByTagName("protocol");
			      Element prtclElmnt = (Element) prtclElmntLst.item(0);
			      NodeList prtcl = prtclElmnt.getChildNodes();
			      partnerBO.setProtocol(((Node) prtcl.item(0)).getNodeValue());
			      logger.info("Protocol : " + partnerBO.getProtocol());
			      
			      //Server Name
			      NodeList srvrElmntLst = fstElmnt.getElementsByTagName("server");
			      Element srvrElmnt = (Element) srvrElmntLst.item(0);
			      NodeList srvr = srvrElmnt.getChildNodes();
			      partnerBO.setServer(((Node) srvr.item(0)).getNodeValue());
			      logger.info("Server Name : " + partnerBO.getServer());
			      
			      //Path Name
			      NodeList pthElmntLst = fstElmnt.getElementsByTagName("path");
			      Element pthElmnt = (Element) pthElmntLst.item(0);
			      NodeList pth = pthElmnt.getChildNodes();
			      partnerBO.setPath(((Node) pth.item(0)).getNodeValue());
			      logger.info("Path Name : " + partnerBO.getPath());
			      
			      //UserName
			      NodeList usrNamElmntLst = fstElmnt.getElementsByTagName("username");
			      Element usrNamElmnt = (Element) usrNamElmntLst.item(0);
			      NodeList usrNam = usrNamElmnt.getChildNodes();
			      partnerBO.setUserName(((Node) usrNam.item(0)).getNodeValue());
			      logger.info("User name : " + partnerBO.getUserName());
			      
			      //Password
			      NodeList pwdElmntLst = fstElmnt.getElementsByTagName("password");
			      Element pwdElmnt = (Element) pwdElmntLst.item(0);
			      NodeList pwd = pwdElmnt.getChildNodes();
			      partnerBO.setPassword(((Node) pwd.item(0)).getNodeValue());
			      //logger.info("Password : " + partnerBO.getPassword());
			}
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
	}

	public void loginRead() {

		try {
			//File file = new File("C:\\sumanta\\LunaWorkspace\\PDFMACY\\src\\PDFMLogin.xml");
			File file = new File(prop.loadLoginPath());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			//logger.info("Root element " + doc.getDocumentElement().getNodeName());
			NodeList nodeLst = doc.getElementsByTagName("user");
			

			for (int s = 0; s < nodeLst.getLength(); s++) {

				Node fstNode = nodeLst.item(s);

				if (fstNode.getNodeType() == Node.ELEMENT_NODE) {

					  Element fstElmnt = (Element) fstNode;
				      NodeList fstNmElmntLst = fstElmnt.getElementsByTagName("username");
				      Element fstNmElmnt = (Element) fstNmElmntLst.item(0);
				      NodeList fstNm = fstNmElmnt.getChildNodes();
				      loginBO.setUserName(((Node) fstNm.item(0)).getNodeValue());
				     // logger.info("User Name : "  + loginBO.getUserName());
				      NodeList lstNmElmntLst = fstElmnt.getElementsByTagName("password");
				      Element lstNmElmnt = (Element) lstNmElmntLst.item(0);
				      NodeList lstNm = lstNmElmnt.getChildNodes();
				      loginBO.setPassword(((Node) lstNm.item(0)).getNodeValue());
				     // logger.info("Password : " + loginBO.getPassword());
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
