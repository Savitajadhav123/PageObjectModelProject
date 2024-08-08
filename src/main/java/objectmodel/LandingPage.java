package objectmodel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract;

public class LandingPage extends Abstract {
	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(id = "userEmail")
	WebElement userEmail;
	@FindBy(id = "userPassword")
	WebElement password;
	@FindBy(id = "login")
	WebElement submit;

	public void login(String Email, String Password) {
		userEmail.sendKeys(Email);
		password.sendKeys(Password);
		submit.click();
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");

	}
}