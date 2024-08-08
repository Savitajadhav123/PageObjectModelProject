package objectmodel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.Abstract;

public class ProductCatalongue extends Abstract {
	WebDriver driver;

	public ProductCatalongue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = ".mb-3")
	List<WebElement> products;
	By productBy = By.cssSelector(".mb-3");

	public List<WebElement> productlist() {
		WaituntilelementAppear(productBy);

		return products;
	}

}