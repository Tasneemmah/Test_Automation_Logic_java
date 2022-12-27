import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Logic {
	public WebDriver driver;

	@BeforeTest

	public void Login() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.saucedemo.com/");
		driver.manage().window().maximize();

		driver.findElement(By.xpath("//*[@id=\"user-name\"]")).sendKeys("standard_user");
		driver.findElement(By.xpath("//*[@id=\"password\"]")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//*[@id=\"login-button\"]")).click();
	}

	@Test(groups = "sorting", priority = 2)
	public void sort_items_low_to_high() throws InterruptedException {
		System.out.println("Test");

		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id=\"header_container\"]/div[2]/div[2]/span/select/option[3]")).click();

		List<WebElement> thePricesList = driver.findElements(By.className("inventory_item_price"));
		List<Double> newEditedList = new ArrayList<>();

		for (int i = 0; i < thePricesList.size(); i++) {
			String price = thePricesList.get(i).getText();
			String editedPrice = price.replace("$", " ");

			double val = Double.parseDouble(editedPrice.trim());
			newEditedList.add(val);
		}

		for (int k = 0; k < newEditedList.size(); k++) {
			boolean checkProcess = newEditedList.get(0) < newEditedList.get(newEditedList.size() - 1);
			Assert.assertEquals(checkProcess, true);
		}
	}

	@Test(groups = "sorting", priority = 1)
	public void check_The_high_to_low() {
		System.out.println("we have done");
	}

	@Test(groups = "cart", priority = 3)
	public void check_add_to_cart_process() {
		System.out.println("we have done again");
	}
}