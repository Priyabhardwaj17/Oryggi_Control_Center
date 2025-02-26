package com.oryggi.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.oryggi.pages.ForgotPasswordPage;
import com.oryggi.utils.EmailUtils;

public class ForgotPasswordTest {
	
	@Test
	public void testForgetPassword() {
		ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
		forgotPasswordPage.enterEmail("oryggiserver@gmail.com");
		forgotPasswordPage.clickContinue();
		
		String otp = EmailUtils.getLATESTOTP();
		Assert.assertNotNull(otp,"OTP was not recieved!");
		
		forgotPasswordPage.enterOTP(otp);
		forgotPasswordPage.enterNewPassword("Test@1234");
		forgotPasswordPage.reEnterNewPassword("Test@1234");
		forgotPasswordPage.clickCreatePassword();
		
		Assert.assertTrue(forgotPasswordPage.isPasswordUpdatedMessageDisplayed());
		
	}

}
