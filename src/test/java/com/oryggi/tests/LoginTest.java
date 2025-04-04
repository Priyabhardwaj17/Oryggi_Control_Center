package com.oryggi.tests;



import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.oryggi.base.BaseTest;
import com.oryggi.pages.LoginPage;

public class LoginTest extends BaseTest  {

	@Test
	public void verifyLoginPageTitle() {
		 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		    // Wait until the title is updated correctly
		    wait.until(ExpectedConditions.titleContains("Oryggi Control Center"));
		String expectedTitle = "Oryggi Control Center";
		String actualTitle =  driver.getTitle();
		// Print title for debugging
	    System.out.println("Actual Title Retrieved: '" + actualTitle + "'");
		 // Assert with TestNG
	    Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch!");
	}
	
	@Test
	public void testLoginWithUsername() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserNameOrEmail("Admin");
		loginPage.enterPassword("Oryggi@123");
		loginPage.clickLogin();
		
		 // Wait for URL to change after successful login
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("dashboard"));

	    // Get actual URL
	    String actualURL = driver.getCurrentUrl();
	    String expectedURL = "https://localhost/OryggiManagerWeb/dashboards/employee-dashboard";  // Replace with actual dashboard URL

	    // Assert that the actual URL matches expected
	    Assert.assertEquals(actualURL, expectedURL, "Login failed! User is not redirected to Dashboard.");
		
	}
	
	@Test
	public void testLoginWithEmail() throws InterruptedException {
		LoginPage loginPage = new LoginPage(driver);
		loginPage.enterUserNameOrEmail("oryggiserver@gmail.com");
		loginPage.enterPassword("Oryggi@123");
		loginPage.clickLogin();
		
		 // Wait for URL to change after successful login
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.urlContains("dashboard"));

	    // Get actual URL
	    String actualURL = driver.getCurrentUrl();
	    String expectedURL = "https://localhost/OryggiManagerWeb/dashboards/employee-dashboard";  

	    // Assert that the actual URL matches expected
	    Assert.assertEquals(actualURL, expectedURL, "Login failed! User is not redirected to Dashboard.");
	
	}
	
	@Test
	public void testLoginWithInvalidCredentials() throws InterruptedException {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUserNameOrEmail("InvalidUser");
	    loginPage.enterPassword("WrongPassword");
	    loginPage.clickLogin();

	    // Wait for Snackbar message to appear
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(("//simple-snack-bar/span"))));
   // Capture Snackbar text
	    String actualMessage1 = snackBar.getText();
	    String expectedMessage = "Invalid User Id or Password.";  

	    // Assert that the Snackbar displays the correct message
	    Assert.assertTrue(actualMessage1.contains(expectedMessage), "Error message mismatch! Expected: " + expectedMessage + ", Found: " + actualMessage1);
	}
	
	@Test
	public void testEmptyFieldsValidation() throws InterruptedException {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.clickLogin();  // Click Login without entering credentials

	    // Wait for the snackbar message to appear
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));

	    // Get the actual message displayed
	    String actualMessage = snackBar.getText();
	    String expectedMessage = "User Id cannot be Empty.";

	    // Assertion to verify correct validation message
	    Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch!");
	}
	
	@Test
	public void testLoginWithEmptyPassword() {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterUserNameOrEmail("Admin");
	    loginPage.clickLogin();
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));
	    
	    String actualMessage = snackBar.getText();
	    String expectedMessage = "Password cannot be Empty.";
	    Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch!");
	}
	
	@Test
	public void testLoginWithEmptyUsername() {
	    LoginPage loginPage = new LoginPage(driver);
	    loginPage.enterPassword("Oryggi@1234");
	    loginPage.clickLogin();
	    
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));
	    
	    String actualMessage = snackBar.getText();
	    String expectedMessage = "User Id cannot be Empty.";
	    Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch!");
	}
	
	@Test
	public void testRememberMeFunctionality() {
	    LoginPage loginPage = new LoginPage(driver);

	    System.out.println("Entering username...");
	    loginPage.enterUserNameOrEmail("Admin");

	    System.out.println("Entering password...");
	    loginPage.enterPassword("Oryggi@1234");

	    System.out.println("Clicking Remember Me checkbox...");
	    loginPage.selectRememberMe();  // Click checkbox

	    System.out.println("Clicking Login button...");
	    loginPage.clickLogin();

	    // Refresh page
	    System.out.println("Refreshing page...");
	    driver.navigate().refresh();

	    // Verify remembered username
	    String rememberedUsername = loginPage.getUsernameFieldText();
	    System.out.println("Remembered Username: " + rememberedUsername);

	    Assert.assertEquals(rememberedUsername, "Admin", "Remember Me functionality failed!");
	}
	
	@Test
	public void testPasswordMasking() {
	    LoginPage loginPage = new LoginPage(driver);

	    // Get password field from LoginPage
	    WebElement passwordField = loginPage.getPasswordField();

	    // Use JavaScript to get the 'type' attribute
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    String fieldType = (String) js.executeScript("return arguments[0].type;", passwordField);

	    // Assert that the password field is masked
	    Assert.assertEquals(fieldType, "password", "Password is NOT masked!");
	}	
}

	

