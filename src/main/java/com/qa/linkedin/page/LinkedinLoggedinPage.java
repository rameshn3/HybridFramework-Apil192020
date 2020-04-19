package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class LinkedinLoggedinPage extends TestBase{
	
	private Logger log=Logger.getLogger(LinkedinLoggedinPage.class);
	public LinkedinLoggedinPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//div[contains(@class,'profile-rail-card')]")
	WebElement profile_rail_card;
	
	@FindBy(xpath="//*[contains(@class,'nav-item__profile-member-photo nav-item__icon')]")
	WebElement profile_image;
	
	@FindBy(xpath = "//a[contains(@data-control-name,'nav.settings_signout')]")
	WebElement signout_link;
	
	@FindBy(xpath = "//input[contains(@placeholder,'Search') and @role='combobox']")
	WebElement search_editbox;
	
	@FindBy(css="button[data-control-name='nav.search_button']")
	WebElement search_torch_icon;
	
	
	
	public void verifyProfileCard(){
		log.debug("wait for the profile rail card");
		wait.until(ExpectedConditions.visibilityOf(profile_rail_card));
		log.debug("assert the profile rail card element");
		Assert.assertTrue(profile_rail_card.isDisplayed());
	}
	
	
	public void doLogout() {
		log.debug("click on profile image icon");
		wait.until(ExpectedConditions.visibilityOf(profile_image));
		profile_image.click();
		log.debug("waiting for the element signout link in the menu");
		wait.until(ExpectedConditions.visibilityOf(signout_link));
		log.debug("clicking on signout link");
		signout_link.click();
	}
	
	public SearchResultsPage doPeopleSearch(String keyword) throws InterruptedException {
		log.debug("type the people search "+ keyword+" in search editbox");
		search_editbox.clear();
		search_editbox.sendKeys(keyword);
		log.debug("click on searc htorch icon");
		search_torch_icon.click();
		Thread.sleep(5000);
		return new SearchResultsPage();	
	}
	
	
}
