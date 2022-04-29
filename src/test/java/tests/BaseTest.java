package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	WebDriver driver;

	// broswer is passed through testng.xml
	@BeforeMethod
	@Parameters(value = { "browser" })
	public void setUp(String browser) {
		if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("no browser is defined in the xml file");
		}

		driver.get("https://app.hubspot.com/login");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
