package com.visionit.autotesting.stepdefs;

//import java.awt.List;
import java.util.List;

import java.util.Set;
import java.util.concurrent.TimeUnit;

//import javax.swing.text.html.HTMLDocument.Iterator;
import java.util.Iterator;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class StepDefs {
	
	WebDriver driver;
	String base_url = "https://amazon.in";
	int implicit_wait_timeout_in_sec = 20;
	private Scenario scn;
	
	@Before
	public void setUp(Scenario scn)
	{
		this.scn = scn;
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
	}
	
	@After
	public void CleanUp()
	{
		driver.quit();
		scn.log("Browser Closed");
	}
	
	
    @Given("User opened browser")
    @Deprecated
    public void user_opened_browser()
    {
    	driver = new ChromeDriver();
    	driver.manage().window().maximize();
    	driver.manage().timeouts().implicitlyWait(implicit_wait_timeout_in_sec, TimeUnit.SECONDS);
        //throw new io.cucumber.java.PendingException();
    }
    
    @Given("User navigated to the home application url")
    public void user_navigated_app_url()
    {
    	driver.get(base_url);
    	scn.log("browser navigated to the url" + base_url);
    	String Expected = "Online Shopping site in India: Shop Online for Mobiles, Books, Watches, Shoes and More - Amazon.in";
    	String actual = driver.getTitle();
    	Assert.assertEquals("Title Validation", Expected, actual);
    	
    	scn.log("Title validation successful.Actual Title:" + actual);
    	//throw new io.cucumber.java.PendingException();
    }

    @When("User Search for product {string}")
    public void user_searh_for_product(String product)
    {
    	//Search for product
    	WebDriverWait webdriverwait1 = new WebDriverWait(driver, 20);
    	webdriverwait1.until(ExpectedConditions.elementToBeClickable(By.id("twotabsearchtextbox"))).sendKeys(product);
    	scn.log("product searched:" + product);
        driver.findElement(By.xpath("//input[@value='Go']")).click();
        scn.log("clicked on go button");
       //throw new io.cucumber.java.PendingException();
    }
    @Then("Search Result page is displayed")
    public void search_result_page_displayed()
    {
        WebDriverWait webdriverWait2 = new WebDriverWait(driver,20);
        webdriverWait2.until(ExpectedConditions.titleIs("Amazon.in : Laptop"));
        Assert.assertEquals("Page Title validation", "Amazon.in : Laptop", driver.getTitle());
        scn.log("page title validation successful" + driver.getTitle());

        //throw new io.cucumber.java.PendingException();

    }
    
    @When("User click on any product")
     public void user_click_on_any_product()
     {
        List<WebElement> productlist = driver.findElements(By.xpath("//a[@class='a-link-normal a-text-normal']"));
        scn.log("no of products searched" + productlist.size());
        productlist.get(0).click();
        scn.log("click on the first link. Link Text:" +productlist.get(0).getText());
    }
    
    @Then("Product Description is displayed in new tab")
     public void product_description_is_Displayed_in_new_tab()
      {
    	//Switch driver to another tab
    	Set<String> handles = driver.getWindowHandles();
        scn.log("List of windows found: "+handles.size());
        scn.log("Windows handles: " + handles.toString());
    	Iterator<String> it = handles.iterator();
    	String original = it.next();
    	String proddesc = it.next();
    	driver.switchTo().window(proddesc);
    	scn.log("Switched to new window");
    	
    	//Check product title
    	WebElement prodtitle = driver.findElement(By.id("productTitle"));
    	Assert.assertEquals("product title", true, prodtitle.isDisplayed());
        scn.log("Product Title header is matched and displayed as: " + prodtitle.getText() );

    	
    	WebElement addtocart = driver.findElement(By.id("add-to-cart-button"));
    	Assert.assertEquals("product title", true, addtocart.isDisplayed());
        scn.log("Add to cart Button is displayed");

    	
    	//switch to original window
    	driver.switchTo().window(original);
        scn.log("Switched back to Original tab");

    	
    	
    }

    
}