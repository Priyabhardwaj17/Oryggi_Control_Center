package com.oryggi.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	private WebDriverWait wait;
	
	//locators
	private By userNameOrEmailField = By.xpath("//input[@data-placeholder='Please enter UId/Email here...']");
	private By passwordField = By.xpath("//input[@name='password']");
	private By loginButton = By.xpath("//span[text()='Login']");
	
	//constructor
	public LoginPage(WebDriver driver) {
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //Explicit wait
	}
	
	//actions
	public void enterUserNameOrEmailField(String userInput) {
		WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameOrEmailField));
		usernameField.sendKeys(userInput);
	}
	
	public void enterPassword(String password) {
		 WebElement passwordFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
		 passwordFieldElement.sendKeys(password);
	}
	
	public void clickLogin() throws InterruptedException {
		 WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
	        loginBtn.click();
	}
	

}
