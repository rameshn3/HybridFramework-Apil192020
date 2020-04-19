package com.qa.linkedin.testcase;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLoggedinPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.util.ExcelUtils;
import com.qa.linkedin.util.TestUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;

public class SearchPeopleTest extends TestBase{
	
private String fpath=System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\linkedin\\data\\searchdata.xlsx";
private Logger log=Logger.getLogger(SearchPeopleTest.class);
 LinkedinHomePage lhmpg=null;
 LinkedinLoggedinPage llpg=null;
 SearchResultsPage srpg=null;
 
  @BeforeClass
  public void beforeClass() {
	log.debug("Started Executing the @BeforeClass Annotation - setup()..");
	lhmpg=new LinkedinHomePage();
	llpg=new LinkedinLoggedinPage();
	srpg=new SearchResultsPage();
	log.debug("click on signin link in linkedin home page");
	lhmpg.clickSigninLink();
	log.debug("do login ");
	llpg=lhmpg.doLogin(prop.getProperty("username"), prop.getProperty("pwd"));
	log.debug("verify the logged in page element");
	llpg.verifyProfileCard();
  }

  @AfterClass
  public void afterClass() {
	 log.debug("executing the @AfterClass - tearDown");
	 log.debug("logout from application");
	 llpg.doLogout();
	 
  }

  @Test(dataProvider="testData")
  public void searchDDtTest(String keyword) throws InterruptedException {
	 log.debug("started executing the @Test search test ...");
	srpg=llpg.doPeopleSearch(keyword);
	 long count=srpg.getResultsCount();
	 log.debug("results count for keyword: "+keyword+" is :"+count);
	 log.debug("clicking on home tab ");
	 driver.findElement(By.xpath("//*[@id='feed-tab-icon']")).click();
	 Thread.sleep(3000);
  }
  
  @DataProvider
  public Object[][] testData() throws InvalidFormatException, IOException{
	 
	Object[][] data=new TestUtil().getTestData(fpath,"Sheet1");
	  
	  return data;
	  
  }
  
}
