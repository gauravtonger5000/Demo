package Page_Object_Model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TransactionPage {
	
	WebDriver driver;
	
	By Transaction = By.xpath("//a[@id=\"TV1t0\"]");
	
	public TransactionPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void clickTransaction() {
		driver.findElement(Transaction).click();
		//System.out.println("Clicked on Transaction");
	}
	
	

}
