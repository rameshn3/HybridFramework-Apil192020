package com.qa.linkedin.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
public static WebDriver driver=null;
public static WebDriverWait wait=null;
public static Properties prop=null;
public Logger log=Logger.getLogger(TestBase.class);

@BeforeTest
public void setup() throws IOException {
	log.debug("Started Executing the browser Initilization and URL setup()");
	log.debug("create objetc for properties class");
	prop=new Properties();
	log.debug("read the property file");
	FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\linkedin\\config\\config.properties");
	log.debug("load the properties");
	prop.load(fis);
	log.debug("getting the browser value from properties file");
	String browser=prop.getProperty("browser");
	log.debug("browsername is-->"+browser);
	 if(browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
				//launch the browser
				//interface refvar=new implmentingclass();
			 driver=new FirefoxDriver();
		  }else if(browser.equalsIgnoreCase("chrome")) {
			 WebDriverManager.chromedriver().setup();
				
				// interface refobj=new implementingclass();
				driver = new ChromeDriver();
		  }else if(browser.equalsIgnoreCase("edge")) {
			
			WebDriverManager.edgedriver().setup();
			
		    driver = new EdgeDriver();
		  }else if(browser.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();
		  }
			log.debug("maximize the window");
				driver.manage().window().maximize();
				log.debug("add implicit wait");
				driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitwait")), TimeUnit.SECONDS);
				log.debug("create Object for WebDriverWait class");
			  wait =new WebDriverWait(driver,Integer.parseInt(prop.getProperty("explicitwait")));
			 log.debug("open the application url"+prop.getProperty("url"));
			  driver.get(prop.getProperty("url"));
	
	
}

@AfterTest
public void tearDown() {
	
	if(driver!=null) {
		driver.quit();
	}
}



}
