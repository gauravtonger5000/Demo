package Page_Object_Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("http://edmstest.infoman-edms.com/General/pgGLogin.aspx?LogOut=True");
		driver.manage().window().maximize();
	
		LoginPage login_page = new LoginPage(driver); // making an object of LoginPage class
		
		login_page.login("admin@tmpl", "ADMIN@#"); //calling the method
		
		TransactionPage transaction = new TransactionPage(driver);
		transaction.clickTransaction();
		
		PurchaseEntryPage purchase = new PurchaseEntryPage(driver);
		purchase.clickOnPurchaseEntry();
        driver.switchTo().frame("frmContent");
		purchase.selectLoc();
		Thread.sleep(2000);
		
	//	purchase.purchaseEntry("Local Purchase", "AAN MOTORS PVT. LTD.","01/03/2024","IV123","123","25/02/2024","Plant 2","4 MINUTE MILE MARKETING SERVICE PVT LTD (4 MINUTE)","By Road","CRB","UP 16","CHSA",""
	//			+ "123","29/02/2024","09 -Uttar Pradesh","143","CH1220224","51182338000R","ENG1k234","BRICK_RED","KeyN123","Greater Noida Showroom","NA","OK","123223","7537","23222","Bharat I");
		
		purchase.purchaseEntry("Local Purchase", "AAN MOTORS PVT. LTD.","01/03/2024","IV123","123","25/02/2024","Plant 2","4 MINUTE MILE MARKETING SERVICE PVT LTD (4 MINUTE)","By Road","CRB","UP 16","CHSA",""
							+ "123","29/02/2024","09 -Uttar Pradesh","143","51182338000R","BRICK_RED","CHSSS4","E234","KeyN123","Greater Noida Showroom","NA","OK","123223","7997","23222","Bharat I");
		
		Thread.sleep(2000);
		WebElement saveButton = driver.findElement(By.xpath("//input[@id=\"ctl00_cpForm_btnSubmit\"]"));
	    saveButton.click();
	    Thread.sleep(6000);
	    driver.switchTo().alert().accept();
	    Thread.sleep(2000);

		driver.switchTo().parentFrame();
	    Thread.sleep(2000);
		LogoutPage logout = new LogoutPage(driver);
		logout.clicklogoutButton();
		
		Thread.sleep(2000);
		driver.close();
		
	}

}
