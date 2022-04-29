package tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.CodilityPage;

public class CodilityTest {
	
	public static void main(String[] args) throws InterruptedException{
		
		WebDriver driver;
		CodilityPage codilityPage;
		
		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		
		driver.get("https://testscriptdemo.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		codilityPage= new CodilityPage(driver);
		codilityPage.clickOnClothingMenuFromCategories();
		codilityPage.verifyTheClothingPage("https://testscriptdemo.com/?product_cat=clothing");
		codilityPage.scrollTillTheWebElement(codilityPage.Bikini);
		codilityPage.clickOnWishListIcon(codilityPage.Bikini.getText());
		codilityPage.scrollTillTheWebElement(codilityPage.singleShirt);
		codilityPage.clickOnWishListIcon(codilityPage.singleShirt.getText());
		codilityPage.scrollTillTheWebElement(codilityPage.blackPants);
		codilityPage.clickOnWishListIcon(codilityPage.blackPants.getText());
		codilityPage.scrollTillTheWebElement(codilityPage.eveningTrousers);
		codilityPage.clickOnWishListIcon(codilityPage.eveningTrousers.getText());
		codilityPage.clickOnWishListIcon();
		codilityPage.scrollTillTheWebElement(codilityPage.wishListSection);
		codilityPage.getPriceOfProductsInWishList("Unit price");
		
		
		//driver.close();
	
		
		
	}
	
	
}
