/*
 * The following is a copy of the script 1 code used as part of a blog tutorial
 * to teach Selenium basic.  This blog can be found here,
 * http://testsheepnz.blogspot.co.nz/2016/07/automation-20-gui-8-building-selenium.html
 * 
 * For more information - please reread.
 * 
 * Mike Talks, Aug 2016
 */


//package commenting;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class addComment {

	public static void main(String[] args) {

		// Create a new instance of the Firefox driver
		// This copies all the information about the page we've loaded into
		// the driver object
		WebDriver driver = new FirefoxDriver();

		// Open our target page ... a previous blog article
		driver.get("http://testsheepnz.blogspot.co.nz/2016/07/im-hoping-that-this-blog-will-have-most.html");

		// Print out the page title
		System.out.println("Page title is: " + driver.getTitle());

		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

		// Switch to the frame that comments are in
		driver.switchTo().frame(4);

		// Select to comment as "anonymous"
		Select select = new Select(driver.findElement(By.name("identityMenu")));
		// select.selectByIndex(8);
		select.selectByValue("ANON");

		// Select the comment body
		String stringComment = "This is my comment";
		WebElement element = driver.findElement(By.id("commentBodyField"));
		element.sendKeys(stringComment);

		// Select to preview
		element = driver.findElement(By.id("postCommentPreview"));
		element.submit();

		// Confirm get told "Anonymous said" / Comment
		if (driver.getPageSource().contains("Anonymous") && driver.getPageSource().contains("said")) {
			System.out.println("Confirmed: Anonymous said");
		}

		if (driver.getPageSource().contains(stringComment)) {
			System.out.println("Confirmed: " + stringComment);
		}

	}

}
