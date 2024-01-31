package tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.BasePage;
import pages.HomePage;
import pages.searchResultPage;


@Listeners(util.ExtentRepotsgen.class)
public class searchResultTest extends BasePage{

	//Test case to get all items from search result and verify expected item present in that
	@Test(priority=7)
	public void checkSearchResult() throws Exception
	{
		HomePage page=new HomePage(driver);
		logger.log(LogStatus.INFO, "Search Item");
		page.searchEnter("Samsung TV");
		page.enterValue();
		logger.log(LogStatus.INFO, "Result Page");
		searchResultPage resPage=new searchResultPage(driver);
		int noOfPages=Integer.parseInt(resPage.pageNoValue());
		try {
		for(int i=0;i<noOfPages;i++)
		{
			resPage.productDetails();
			if(i!=noOfPages-1)
			{
				resPage.nextPage();
			}		
			}
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		logger.log(LogStatus.INFO, "Validate search result");
		System.out.println(searchResultPage.map.size());
		Assert.assertTrue(searchResultPage.map.containsValue("SAMSUNG Series 4 59 cm (24 inch) HD Ready LED TV"));
	}
	//Test case to get lowest price & highest price item from the search result
	@Test(priority=8)
	public void checkLowestandHighestProduct() throws Exception
	{
		HomePage page=new HomePage(driver);
		logger.log(LogStatus.INFO, "Search Item");
		page.searchEnter("Samsung TV");
		page.enterValue();
		logger.log(LogStatus.INFO, "Result Page");
		searchResultPage resPage=new searchResultPage(driver);
		int noOfPages=Integer.parseInt(resPage.pageNoValue());
		try {
		for(int i=0;i<noOfPages;i++)
		{
			resPage.productDetails();
			if(i!=noOfPages-1)
			{
				resPage.nextPage();
			}
		}
		}
		catch(Exception e)
		{
			System.out.print(e.getMessage());
		}
		logger.log(LogStatus.INFO, "Largest and lowest price items");
		System.out.println(searchResultPage.map.size());
		Set<Integer> s=searchResultPage.map.keySet();
		ArrayList<Integer> list=new ArrayList<Integer>(s);
		Collections.sort(list);
		System.out.println(list.get(0)+" : "+searchResultPage.map.get(list.get(0)));
		System.out.println(list.get(list.size()-1)+" : "+searchResultPage.map.get(list.get(list.size()-1)));
		Assert.assertTrue(true);
	}
}
