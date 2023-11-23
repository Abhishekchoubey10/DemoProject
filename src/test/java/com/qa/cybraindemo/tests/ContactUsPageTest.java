package com.qa.cybraindemo.tests;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.qa.cybraindemo.base.BaseTest;
import com.qa.cybraindemo.utils.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

public class ContactUsPageTest extends BaseTest {


	@Description("Submit Contact us form..")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 2)
	public void submitContactusForm() {
		contactusPage.formSubmission(prop.getProperty("name"), prop.getProperty("email"), prop.getProperty("phone"),
				prop.getProperty("message"));
	}

}
