package HandlingQRAndBarCodeAndCSSValidation.HandlingQRAndBarCodeAndCSSValidation;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class DemoPractise1 {
	
	WebDriver driver;
	
	@Test
	public void cssColorValidation() throws InterruptedException, IOException
	{
		String chromeDriverPath= System.getProperty("user.dir") + "//Resources//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		ChromeOptions options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.get("https://classic.crmpro.com/index.html");
		
		Thread.sleep(3000);
		WebElement userName =  driver.findElement(By.name("username"));
		driver.switchTo().activeElement();
		driver.switchTo().activeElement();
		driver.switchTo().activeElement();
		driver.findElement(By.name("username")).sendKeys("ali0311");
		driver.findElement(By.name("password")).sendKeys("Mind@786");
		WebElement loginBtn = driver.findElement(By.xpath("//input[@type='submit']"));
		JavascriptExecutor js = (JavascriptExecutor)driver;
    	js.executeScript("arguments[0].click();", loginBtn);
    	
    	driver.switchTo().frame("mainpanel");
    	
    	WebElement homeBtn = driver.findElement(By.xpath("//a[@title='Home']"));
    	WebElement calendBtn = driver.findElement(By.xpath("//a[@title='Calendar']"));
    	
    	//convert RGB to Hex
    	String rgb = homeBtn.getCssValue("background-color").trim();
    	String checkValue = rgb.replace("rgba(", "");
    	String color1[];
    	color1 = rgb.replace("rgba(", "").split(","); 
    	String hex = String.format("#%02x%02x%02x", Integer.parseInt(color1[0].trim()), Integer.parseInt(color1[1].trim()), Integer.parseInt(color1[2].trim()));
    	System.out.println("Convert rgb to hex : " + hex.toUpperCase()); 
    	
    	//We can validate with assertion with below printed values...every color has unique CSSvalue of "background-color" and "color"
    	System.out.println("This is Home background-color : "+homeBtn.getCssValue("background-color"));
    	System.out.println("This is Home only color : "+homeBtn.getCssValue("color"));
    	System.out.println("This is Calender background-color : "+calendBtn.getCssValue("background-color"));
    	System.out.println("This is Calender only color : "+calendBtn.getCssValue("color"));
    	
    	driver.switchTo().defaultContent(); 
    	
    	driver.quit();
		
	}
	

}
