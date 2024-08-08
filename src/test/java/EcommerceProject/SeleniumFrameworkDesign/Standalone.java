package EcommerceProject.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;

public class Standalone {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));

		String itema = "ADIDAS ORIGINAL";

		WebElement prod = products.stream().filter(product -> {
			try {
				return product.findElement(By.cssSelector("b")).getText().equals(itema);
			} catch (NoSuchElementException e) {
				return false; // Return false if the <b> element is not found
			}
		}).findFirst().orElse(null);
		prod.findElement(By.xpath("//*[@id=\"products\"]/div[1]/div[2]/div[2]/div/div/button[2]")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();

		List<WebElement> cartproducts = driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		Boolean match = cartproducts.stream().anyMatch(cartp -> cartp.getText().equals(itema));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//div[@class='subtotal cf ng-star-inserted']/ul/li[3]/button")).click();
		Actions action = new Actions(driver);
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();

		WebElement element = driver.findElement(By.cssSelector(".action__submit"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", element);
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

	}

}
