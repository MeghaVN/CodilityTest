package pages;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CodilityPage {

	WebDriver driver;
	WebDriverWait wait;

	public CodilityPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	@FindBy(xpath = "//a[contains(text(),'Categories')]")
	public WebElement CategoriesMenu;

	@FindBy(xpath = "//a[text()='Clothing']")
	public WebElement Clothing;

	@FindBy(xpath = "//div[@class='heading-row row']//i[@class='la la-shopping-bag' ]")
	public WebElement CartIcon;

	@FindBy(xpath = "//div[@class='heading-row row']//i[@class='lar la-heart' ]")
	public WebElement wishListIcon;

	@FindBy(xpath = "//a[@role='button' and contains(text(),'Accept all')]")
	public WebElement acceptAll;

	@FindBy(xpath = "//h2[text()='Hard top']")
	public WebElement hardTop;

	@FindBy(xpath = "//h2[text()='Single Shirt']")
	public WebElement singleShirt;

	@FindBy(xpath = "//h2[text()='Evening trousers']")
	public WebElement eveningTrousers;

	@FindBy(xpath = "//h2[text()='Black trousers']")
	public WebElement blackPants;

	@FindBy(xpath = "//h2[text()='Bikini']")
	public WebElement Bikini;

	@FindBy(xpath = "//a[text()='Add to cart']")
	public WebElement addToCart;

	@FindBy(xpath = "//h1[text()='Wishlist']")
	public WebElement wishListSection;

	@FindBy(xpath = "//form[@id='yith-wcwl-form']")
	public WebElement wishListForm;

	@FindBy(xpath = "//tbody/tr")
	public List<WebElement> totalNoRows;

	// String priceOfProd = "//h2[text()='Hard
	// top']/parent::a/span[@class='price']//span[@class='woocommerce-Price-currencySymbol']"

	public void openUrl(String url) {

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void clickOnClothingMenuFromCategories() throws InterruptedException {

		Actions action = new Actions(driver);
		wait = new WebDriverWait(driver, 20);
		WebElement mainMenu = wait.until(ExpectedConditions.visibilityOf(CategoriesMenu));
		action.moveToElement(mainMenu).build().perform();
		Thread.sleep(5000);
		WebElement subMenu = wait.until(ExpectedConditions.visibilityOf(Clothing));
		action.moveToElement(subMenu).click().build().perform();
		Thread.sleep(6000);

	}

	public void verifyTheClothingPage(String url) {
		// String ClothingPageUrl = "https://testscriptdemo.com/?product_cat=clothing";
		String currentPageUrl = driver.getCurrentUrl();
		if (currentPageUrl.equals(url)) {
			System.out.println("The user is navigated to the submenu page..");
		} else {
			System.out.println("the user is not navigates");
		}

		WebElement acceptAllButton = wait.until(ExpectedConditions.visibilityOf(acceptAll));
		acceptAllButton.click();

	}

	public void scrollTillTheWebElement(WebElement element) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
	}

	public void clickOnWishListIcon(String prodName) throws InterruptedException {

		WebElement wishList = driver.findElement(By.xpath("//h2[text()='" + prodName
				+ "']/parent::a/following-sibling::div//div[@class='product-wishlist']/div"));
		WebElement addToWishListLink = wait.until(ExpectedConditions.visibilityOf(wishList));
		addToWishListLink.click();
		Thread.sleep(6000);
		System.out.println("clicked on wishlist");
	}

	public String getCostOfProduct(String prodName) {

		WebElement priceOfProfElement = driver.findElement(By.xpath("//h2[text()='" + prodName
				+ "']/parent::a/span[@class='price']//span[@class='woocommerce-Price-currencySymbol']"));
		wait.until(ExpectedConditions.visibilityOf(priceOfProfElement));
		String priceOfElement = priceOfProfElement.getText();
		System.err.println(priceOfElement);
		return priceOfElement;

	}

	public void clickOnWishListIcon() throws InterruptedException {
		wait.until(ExpectedConditions.visibilityOf(wishListIcon));
		scrollTillTheWebElement(wishListIcon);
		wishListIcon.click();
		// wait(2000);

	}

	public void switchToWishListForm() {
		wait.until(ExpectedConditions.visibilityOf(wishListForm));
	}

	public int getColumnID(String columnName) {
		int columnID = 0;
		List<WebElement> cols;
		cols = driver.findElements(By.xpath("//table/thead/tr/th"));

		for (int i = 0; i <= cols.size(); i++) {
			System.out.println(i);
			String label = cols.get(i).getText();
			System.out.println(label);
			if (label.equals(columnName)) {
				System.out.println(i);
				columnID = i + 2;
				System.out.println(columnID);
				break;
			}
		}
		return columnID;
	}

	public String getPriceOfProductsInWishList(String columnName) {
		int columnId = getColumnID(columnName);
		List<Integer> priceList = new ArrayList<Integer>();
		int totalRows;
		String row1 = "#yith-wcwl-row-16 > td.product";
		String row2 = "#yith-wcwl-row-15 > td.product";
		String row3 = "#yith-wcwl-row-20 > td.product";
		String row4 = "#yith-wcwl-row-22 > td.product";
		String priceTag = "-price > ins > span > bdi";
		String addToCart = "-add-to-cart > a";
//		String allPrice[];
		
		List<String> rows = new ArrayList<String>();
		List<String> allPrice = new ArrayList<String>();
		rows.add(row1);
		rows.add(row2);
		rows.add(row3);
		rows.add(row4);
		
		for (int i = 1; i <= rows.size(); i++) {
		WebElement price = driver.findElement(By.cssSelector(rows.get(i-1) + priceTag));
		String priceValue = price.getText().substring(1);
		allPrice.add((priceValue));
		System.out.println(i+" value is "+priceValue);
		
		
		}
		
//		System.out.println("total rows:" + totalNoRows.size());
//		for (int i = 0; i < totalNoRows.size(); i++) {
//
//			// WebElement price =
//			// driver.findElement(By.xpath("//tbody/tr["+i+")/td["+columnId+"]
//
//			WebElement priceUnit = driver
//					.findElement(By.cssSelector("#yith-wcwl-row-" + i + " > td.product-price > ins > span > bdi"));
//			System.out.println(priceUnit);
//			System.out.println(priceUnit.getText());
//			int price = Integer.parseInt(priceUnit.getText());
//			priceList.add(price);
//
//		}
		System.out.println(Collections.min(allPrice));
		return Collections.min(allPrice);	
	}

}
