package com.qa.cybraindemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.cybraindemo.utils.Constants;
import com.qa.cybraindemo.utils.ElementUtil;

import io.qameta.allure.Step;

public class ContactUsPage {
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//1. Private By locators 
	private By PageName = By.xpath("//section[@id='contact']/h3");
	private By Name = By.xpath("//input[@id='cname' and @name ='name']");
	private By Email = By.id("cemail");
	private By PhoneNumber = By.id("cphone");
	private By Option = By.xpath("//select[@id='Company-type-2']/option[@value='Startup']");
	private By Message = By.xpath("//textarea[@name='message']");
	private By Checkbox = By.xpath("//input[@type='checkbox']");
	private By Submit = By.xpath("//button[contains(text(), Submit) and @data-toggle='modal']");
	
	//2. Public Page constant 
	public ContactUsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	
	
	//3. Contact us form submission 
	@Step("Submit contact us form....")
	public boolean formSubmission(String nm, String email, String ph, String msg) {
		eleutil.waitForElementToBeVisible(Name, Constants.DEFAULT_TIME_OUT);
		eleutil.doSendKeys(Name, nm);
		eleutil.doSendKeys(Email, email);
		eleutil.doSendKeys(PhoneNumber, ph);
		eleutil.doClick(Option);
		eleutil.doSendKeys(Message, msg);
		eleutil.doClick(Checkbox);
		eleutil.doClick(Submit);
		if(eleutil.doIsDisplayed(PageName)) {
			return true;
		}
		return false;
	}
}
