package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

 WebDriver driver;
 List<String> tabs;
	
	By search=By.xpath(".//*[@class='Pke_EE']");
	By cart=By.xpath(".//*[@alt='Cart']");
	By addtoCart=By.cssSelector("button._2KpZ6l._2U9uOA._3v1-ww");
	By popup=By.cssSelector("span._30XB9F");
	
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
	}
	public String getTitle() throws Exception
	{
		return driver.getTitle();
	}
	public String getSearchValue() throws Exception
	{
		return driver.findElement(search).getAttribute("value");
	}
	public void searchEnter(String searchThing) throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(search));
		driver.findElement(search).clear();
		driver.findElement(search).sendKeys(searchThing);
	}	
	public void enterValue() throws Exception
	{
		driver.findElement(search).sendKeys(Keys.ENTER);
	}
	public void cartClick() throws Exception
	{
		JavascriptExecutor js=(JavascriptExecutor)driver;
		js.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(cart));
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(cart));
		js.executeScript("arguments[0].click()",driver.findElement(cart));
	}
	public void closePopup() throws Exception
	{
		try
		{
			driver.findElement(popup).click();
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
	}
	public void openTab() throws Exception
	{
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL+"t");
	}
	
	public void switchtoTab() throws Exception
	{
		tabs= new ArrayList<String> (driver.getWindowHandles());
		driver.switchTo().window(tabs.get(1));
		
	}
	public void switchToParentWindow() throws Exception
	{
		driver.switchTo().window(tabs.get(0));
	}
	public void addCart() throws Exception
	{
		driver.findElement(addtoCart).click();
	}
}
