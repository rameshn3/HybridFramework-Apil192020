package com.qa.linkedin.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.util.TestUtil;

public class LinkedinHomePage extends TestBase{
	private Logger log=Logger.getLogger(LinkedinHomePage.class);
	public LinkedinHomePage() {
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(xpath="//*[@class='nav__logo-link']")
	private WebElement linkedinLogo;
	
	@FindBy(xpath = "//a[@class='nav__button-secondary'][contains(.,'Sign in')]")
	private WebElement signinLink;
	
	@FindBy(css="h1.header__content__heading")
	private WebElement welcomeHeading;
	
	@FindBy(id="username")
	WebElement emailEditbox;
	
	@FindBy(name="session_password")
	WebElement passwordEditbox;
	
	@FindBy(id="password-visibility-toggle")
	WebElement showLink;
	
	@FindBy(id="rememberMeOptIn-checkbox")
	WebElement rememberMeCheckbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	WebElement signinBtn;
	
	
	public void verifyHomePageTitle() {
		log.debug("verifying the linkedin home page title: LinkedIn: Log In or Sign Up");
		wait.until(ExpectedConditions.titleIs("LinkedIn: Log In or Sign Up"));
		Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up");
		
	}
	
	public void verifyLinkedLogo() {
		log.debug("Verify the linkedin logo in homepage");
		wait.until(ExpectedConditions.visibilityOf(linkedinLogo));
		Assert.assertTrue(linkedinLogo.isDisplayed(), "linkedinLogo is not present");
	}
	
	public void clickSigninLink() {
		try {
			log.debug("checking whether signin lick is clickable or not in homepage");
			wait.until(ExpectedConditions.elementToBeClickable(signinLink));
			log.debug("click on signin link in homepage");
			//linkedinLogo.click();
			TestUtil.safeJavaScriptClick(driver,signinLink);
		} catch (Exception e) {
			log.debug("signin link is not clickable");
			e.printStackTrace();
		}
	}
	
	public void verifySigninPageTitle() {
		log.debug("Verify the Signin page title:LinkedIn Login, Sign in | LinkedIn");
		wait.until(ExpectedConditions.titleIs("LinkedIn Login, Sign in | LinkedIn"));
		Assert.assertEquals(driver.getTitle(), "LinkedIn Login, Sign in | LinkedIn");
		
	}
	
	public void verifySigninSectionElements() throws InterruptedException {
		log.debug("wait for the welcome Back heading in Signin page");
		wait.until(ExpectedConditions.visibilityOf(welcomeHeading));
		Assert.assertTrue(welcomeHeading.isDisplayed());
		log.debug("wait for the email editbox in Signin page");
		wait.until(ExpectedConditions.visibilityOf(emailEditbox));
		Assert.assertTrue(emailEditbox.isDisplayed());
		log.debug("wait for the passwordEditbox in Signin page");
		wait.until(ExpectedConditions.visibilityOf(passwordEditbox));
		Assert.assertTrue(passwordEditbox.isDisplayed());
		log.debug("wait for the show link in Signin page");
		wait.until(ExpectedConditions.visibilityOf(showLink));
		Assert.assertTrue(showLink.isDisplayed());
		log.debug("wait for the singin button in Signin page");
		wait.until(ExpectedConditions.visibilityOf(signinBtn));
		Assert.assertTrue(signinBtn.isDisplayed());
	}
	
	public void checkRememberMeCheckbox() {
		log.debug("checking the remember me checkbox");
		if(!rememberMeCheckbox.isSelected()) {
			log.debug("click on the rememberme checkbox");
			rememberMeCheckbox.click();
		}
	}
	
	public LinkedinLoggedinPage doLogin(String uname,String pwd) {
		log.debug("clear the content in emaileditbox");
		emailEditbox.clear();
		log.debug("type the email in emaileditbox:"+uname);
		emailEditbox.sendKeys(uname);
		log.debug("clear the content in passwordeditbox");
		passwordEditbox.clear();
		log.debug("type the password in emaileditbox:"+pwd);
		passwordEditbox.sendKeys(pwd);
		log.debug("click on signin button");
		signinBtn.click();
		return new LinkedinLoggedinPage();
	}
	
	
	
}
