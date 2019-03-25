package HandlingQRAndBarCodeAndCSSValidation.HandlingQRAndBarCodeAndCSSValidation;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class HandleBarCode {

	WebDriver driver;
	
	@BeforeMethod
	public void intitialise()
	{
		String chromePath = System.getProperty("user.dir") + "//Resources//chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", chromePath);

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		System.out.println("Executed before");
	}

	@Test(priority=0)
	public void handlingBarCode() throws IOException, NotFoundException {
		
		driver.get("https://testautomationpractice.blogspot.com/");
		String barCodeURL1 = driver.findElement(By.xpath("//*[@id='HTML12']/div[1]/img[1]")).getAttribute("src");
		String barCodeURL2 = driver.findElement(By.xpath("//*[@id='HTML12']/div[1]/img[2]")).getAttribute("src");
		
		String text1 = HandleBarCode.getTextFromBarAndQRCode(barCodeURL1);
		String text2 = HandleBarCode.getTextFromBarAndQRCode(barCodeURL2);
		
		System.out.println("Text from first bar code is "+text1);
		System.out.println("Text from first bar code is "+text2);
		
		System.out.println("First Test Executed");

	}
	
	@Test(priority=1)
	public void handlingQRCode() throws IOException, NotFoundException {
		
		driver.get("https://www.barcodesinc.com/generator/qr/?chl=Ali&chs=200x200&cht=qr&chld=H%7C0");
		String qrCodeURL = driver.findElement(By.xpath("//a[@class='qrcode']/img")).getAttribute("src");
		
		String text1 = HandleBarCode.getTextFromBarAndQRCode(qrCodeURL);
		System.out.println("Text from first bar code is "+text1);
		System.out.println("2nd Test Executed");
	}
	
	@AfterMethod()
	public void tearUp()
	{
		System.out.println("Executed every time after test");
		driver.quit();
		
	}

	public static String getTextFromBarAndQRCode(String codeURL) throws IOException, NotFoundException
	{
		URL url = new URL(codeURL);
		BufferedImage bufferedimage = ImageIO.read(url);
		LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedimage);
		BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));
		Result result = new MultiFormatReader().decode(binaryBitmap);
		return result.getText();
	}
	
}
