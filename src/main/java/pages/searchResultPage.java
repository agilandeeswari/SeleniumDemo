package pages;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class searchResultPage {

	WebDriver driver;
	public static HashMap<Integer,String> map=new HashMap<Integer,String>();
	public static HashMap<String,String> refMap=new HashMap<String,String>();
	
	By pageNo=By.xpath(".//*[@class='_2MImiq']/span[1]");
	By productName=By.cssSelector("div._4rR01T");
	By productPrice=By.cssSelector("div._30jeq3._1_WHN1");
	By refence=By.xpath(".//*[@class='_4rR01T']/../../..");
	By nextPage=By.xpath(".//span[contains(text(),'Next')]");
	
	public searchResultPage(WebDriver driver)
	{
		this.driver=driver;
	}
	
	public String pageNoValue() throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@class='_2MImiq']/span[1]")));
		String noOfPages = driver.findElement(By.xpath(".//*[@class='_2MImiq']/span[1]")).getText().replaceFirst("Page 1 of ", "");
		return noOfPages;
	}
	public void productDetails() throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));		
		wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
		driver.navigate().refresh();
		List<WebElement> Item=driver.findElements(productName);
		List<WebElement> cost=driver.findElements(productPrice);
		try
		{
	
			for(int i=0;i<Item.size();i++)
			{
					map.put(Integer.parseInt(cost.get(i).getText().replaceAll("[^0-9]","")),Item.get(i).getText());
		
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Item.get(Item.size()-1));
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());	
		}
	}
	public HashMap<String,String> productrefernce() throws Exception
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(20));		
		wait.until(ExpectedConditions.visibilityOfElementLocated(productName));
		driver.navigate().refresh();
		List<WebElement> Item=driver.findElements(productName);
		List<WebElement> refer=driver.findElements(refence);
		try
		{
	
			for(int i=0;i<Item.size();i++)
			{
					refMap.put(Item.get(i).getText(),refer.get(i).getAttribute("href"));
		
			}
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", Item.get(Item.size()-1));
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());	
		}
		return refMap;
	}
	public void nextPage() throws Exception
	{
		driver.findElement(nextPage).click();
	}
	public void chooseItem(String item) throws Exception
	{
		((JavascriptExecutor) driver).executeScript("window.open("+refMap.get(item)+")");
	}
}
