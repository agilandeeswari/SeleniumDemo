package tests;

import com.relevantcodes.extentreports.LogStatus;

import pages.BasePage;
import pages.CartPage;
import pages.HomePage;
import pages.searchResultPage;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;




@Listeners(util.ExtentRepotsgen.class)
public class CartPageTest extends BasePage{

	//Test Case to check empty cart
	@Test(priority=1)
	public void checkCartwithEmpty() throws Exception
	{
		HomePage page=new HomePage(driver);
		page.closePopup();
		page.cartClick();
		CartPage cpage=new CartPage(driver);
		String messgae=cpage.getMessage();
		logger.log(LogStatus.INFO, "Cart should be empty");
		Assert.assertTrue(messgae.contains("Missing Cart items"));
	}
	//Test case to check add an item to cart
	@Test(priority=2)
	public void checkAddItemToCart() throws Exception
	{
		HomePage page=new HomePage(driver);
		logger.log(LogStatus.INFO, "Search Item");
		page.closePopup();
		page.searchEnter("Apple iphone");
		page.enterValue();
		searchResultPage resPage=new searchResultPage(driver);
		int noOfPages=Integer.parseInt(resPage.pageNoValue());
		try {
		for(int i=0;i<noOfPages;i++)
		{
			resPage.productrefernce();
			if(i!=noOfPages-1)
			{
				resPage.nextPage();
			}	

		}
		}
		catch(Exception e)
		{
			logger.log(LogStatus.FATAL,e.getMessage());
		}	
		
		((JavascriptExecutor)driver).executeScript("window.open()");
		logger.log(LogStatus.INFO, "Switch Tab");
		page.switchtoTab();
		driver.get(searchResultPage.refMap.get("Apple iPhone 12 (Blue, 128 GB)"));
		logger.log(LogStatus.INFO, "Add Cart");
		page.addCart();
		CartPage cpage=new CartPage(driver);
		String messgae=cpage.getMessage();
		logger.log(LogStatus.INFO, "Search should not be empty");
		Assert.assertTrue(messgae.contains("Apple iPhone 12 (Blue, 128 GB)"));
		//System.out.println(page.getTitle());
		Assert.assertTrue(page.getTitle().contains("Shopping Cart"));
		page.switchToParentWindow();
		logger.log(LogStatus.INFO, "Switched to Parent window");
	}
	//TestCase to check cart after add item
	@Test(priority=3)
	public void checkCart() throws Exception
	{
		HomePage page=new HomePage(driver);
		page.closePopup();
		page.cartClick();
		CartPage cpage=new CartPage(driver);
		String messgae=cpage.getMessage();
		logger.log(LogStatus.INFO, "Search should not be empty");
		Assert.assertTrue(messgae.contains("Apple iPhone 12 (Blue, 128 GB)"));
	}
	//TestCase to check delete a cart item
	@Test(priority=4)
	public void deleteFromCart() throws Exception
	{
		HomePage page=new HomePage(driver);
		page.closePopup();
		page.cartClick();
		CartPage cpage=new CartPage(driver);
		logger.log(LogStatus.INFO, "Delete Item");
		cpage.deleteCart();
		cpage.removeAccept();
		Thread.sleep(3000);
		String message=cpage.getMessage();
		//System.out.print(message);
		logger.log(LogStatus.INFO, "Item should be deleted");
		Assert.assertTrue(message.contains("Missing Cart items"));
	}
}