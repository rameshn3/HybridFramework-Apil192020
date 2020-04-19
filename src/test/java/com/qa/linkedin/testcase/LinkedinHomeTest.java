package com.qa.linkedin.testcase;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;

public class LinkedinHomeTest extends TestBase{
	private Logger log=Logger.getLogger(LinkedinHomeTest.class);
	private LinkedinHomePage lhmpg=null;
 
	@BeforeClass
 public void objectSetUp() {
	 log.debug("Executing the objectSetup()...");
	 lhmpg=new LinkedinHomePage();
	 
 }
	
	@Test(priority=1)
  public void verifyHomePageTitleTest() {
	 log.debug("Verifying the Linkedin Home page title");
	 lhmpg.verifyHomePageTitle();
	   
  }

@Test(priority=2)
public void verifyLinkedinLogoTest() {
	log.debug("Started Executing the verifyLinkedinLogoTest()...");
	lhmpg.verifyLinkedLogo();
}

@Test(priority=3)
public void navToSigninPageTest() throws InterruptedException {
	log.debug("started Executing the navToSigninPageTest()...");
	lhmpg.clickSigninLink();
	log.debug("verify the signin page title");
	lhmpg.verifySigninPageTitle();
	log.debug("verify signin page elements");
	lhmpg.verifySigninSectionElements();
}




}
