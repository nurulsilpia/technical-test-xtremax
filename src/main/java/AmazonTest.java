import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		// Waits
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		driver.manage().window().maximize();
		driver.get("https://www.amazon.com/");

		// nav left
		WebElement navLeft = driver.findElement(By.id("nav-hamburger-menu"));
		navLeft.click();
		WebElement electronic = driver.findElement(By.linkText("Electronics"));
		electronic.click();
		WebElement tvAndVid = driver.findElement(By.xpath("//*[@id=\"hmenu-content\"]/ul[5]/li[15]/a"));
		js.executeScript("arguments[0].scrollIntoView();", tvAndVid);
		tvAndVid.click();
		
		// department
		WebElement departmen = driver.findElement(By.id("departments"));
		WebElement televisions = departmen.findElement(By.linkText("Televisions"));
		televisions.click();
		WebElement size = driver.findElement(By.xpath("//*[@id=\"p_n_size_browse-bin/1232879011\"]/span/a"));
		js.executeScript("arguments[0].scrollIntoView();", size);
		size.click();
		
		// short
		WebElement dropDownContainer = driver.findElement(By.className("a-dropdown-container"));
		dropDownContainer.click();
		WebElement selectMenu = driver.findElement(By.id("s-result-sort-select"));
		Select selectObject = new Select(selectMenu);
		selectObject.selectByValue("price-desc-rank");
		
		List<WebElement> titleProduct = driver.findElements(By.className("s-title-instructions-style"));
		for (WebElement title : titleProduct) {
			System.out.println(title.getText());
			System.out.println("--------------");
		}

		List<WebElement> priceProduct = driver.findElements(By.className("a-price"));
		for (WebElement price : priceProduct) {
			System.out.println(price.getText());
			System.out.println("--------------");
		}
		
		// --model year 2017---
		WebElement modelYear = driver.findElement(By.xpath("//*[@id=\"p_n_feature_nine_browse-bin/16591230011\"]/span/a"));
		js.executeScript("arguments[0].scrollIntoView();", modelYear);
		modelYear.click();
		
		WebElement price = driver.findElement(By.id("priceRefinements"));
		WebElement highPrice = price.findElement(By.id("high-price"));
		highPrice.sendKeys("150" + Keys.ENTER);
		
		WebElement product = driver.findElement(By.xpath("//*[@id=\"search\"]/div[1]/div[1]/div/span[3]/div[2]/div[2]/div/div/div/div/div[3]/div[1]/h2/a"));
		product.click();
		WebElement btnAddToList = driver.findElement(By.xpath("//*[@id=\"wishListMainButton\"]/span/a"));
		js.executeScript("arguments[0].scrollIntoView();", btnAddToList);
		btnAddToList.click();

		//login confirmation
		String confirmationText = driver.findElement(By.tagName("h1")).getText();
		if (confirmationText.contains("Sign-In")) {
			System.out.println("you haven't logged in yet");
			System.out.println("--------------");
		}else {
			System.out.println("you already logged in");
		}
		
		//login page
		WebElement email = driver.findElement(By.id("ap_email"));
		email.sendKeys("dummyemail");
		WebElement btnCtn = driver.findElement(By.id("continue"));
		btnCtn.click();
		
		String errorMsg = driver.findElement(By.id("auth-error-message-box")).getText();
		System.out.println(errorMsg);
		
		Thread.sleep(2000); 
		driver.close(); 
		driver.quit();
		 
	}

}
