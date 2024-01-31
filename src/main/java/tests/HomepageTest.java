package tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.BasePage;
import pages.HomePage;

@Listeners(util.ExtentRepotsgen.class)
public class HomepageTest extends BasePage {

	//Test case to check URL redirected to valid page
	@Test(priority=5)
	public void validatePageURL() throws Exception
	{
		HomePage page=new HomePage(driver);
		String result=page.getTitle()	;
		logger.log(LogStatus.INFO, result);
		Assert.assertTrue(result.contains("Online Shopping Site"));
	}
	//Test case to check search box accept an input
	@Test(priority=6)
	public void checkSearchBox() throws Exception
	{
		HomePage page=new HomePage(driver);
		page.searchEnter("TV");
		String result=page.getSearchValue();
		logger.log(LogStatus.INFO, result);
		Assert.assertEquals("TV", result);
	}
	
}
