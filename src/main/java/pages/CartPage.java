package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage{
	
	 WebDriver driver;
	By text=By.cssSelector("div._1YokD2._2GoDe3.col-12-12");
	By deleteCart=By.xpath(".//*[contains(text(),'Remove')]");
	By removeCart=By.xpath(".//*[@class='row _1lPa3S']/div/div[2]");
	
	public CartPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String getMessage() throws Exception
	{
		 WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		 wait.until(ExpectedConditions.visibilityOfElementLocated(text));
		 return driver.findElement(text).getText();
	}
	public void deleteCart() throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(deleteCart));
		driver.findElement(deleteCart).click();
	}
	public void removeAccept() throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(removeCart));
		driver.findElement(removeCart).click();
	}
} 
