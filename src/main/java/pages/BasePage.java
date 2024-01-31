package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class BasePage {

	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest logger;
    public String URL="https://www.flipkart.com/";
	
	@BeforeSuite(alwaysRun=true)
	public void setup() throws Exception
	{
		ChromeOptions op=new ChromeOptions();
		op.addArguments("no-sandbox");
		driver = new ChromeDriver(op);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	@BeforeMethod(alwaysRun=true)
	public void start() throws Exception
	{
		driver.get(URL);

	}
	@AfterSuite(alwaysRun=true)
	public void tearUp() throws Exception
	{
		driver.quit();
	}
}
