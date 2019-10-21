package selenium;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Selenium {

	public static void testSelenium(WebDriver driver) throws InterruptedException, IOException, AWTException {

		driver.get("http://www.baidu.com");
		Thread.sleep(1300L);

		WebElement searchBox = driver.findElement(By.id("kw"));
		searchBox.sendKeys("test");
		searchBox.submit();

		Thread.sleep(1300L);
		WebElement imageButton = driver.findElement(By.partialLinkText("图片"));
//		imageButton.click();
		WebElement link = driver.findElement(By.linkText("百度翻译"));
		link.click();
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).click(imageButton).keyUp(Keys.CONTROL).build().perform();

		JavaScriptCommonUtil jsUtil = new JavaScriptCommonUtil();
		jsUtil.openNewTab(driver);

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		System.out.println(tabs);
		driver.switchTo().window(tabs.get(1));
		driver.close();
		driver.switchTo().window(tabs.get(0));

		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrFile, new File("d:/auxiliary/tmp/screenshot.png"));

		/*
		 * https://www.guru99.com/xpath-selenium.html
		 * 
		 * xpath=/html/body/form[1] (3) - Absolute path (would break if the HTML was changed only slightly) 
		 * //form[1] (3) - First form element in the HTML
		 * xpath=//form[@id='loginForm'] (3) - The form element with attribute named ‘id’ and the value ‘loginForm’ xpath=//form[input/@name='username'] (3) - First form element with an input child element with attribute named ‘name’ and the value ‘username’ //input[@name='username'] (4) - First input element with attribute named ‘name’ and the value ‘username’
		 * //form[@id='loginForm']/input[1] (4) - First input child element of the form element with attribute named ‘id’ and the value ‘loginForm’
		 * //input[@name='continue'][@type='button'] (7) - Input with attribute named ‘name’ and the value ‘continue’ and attribute named ‘type’ and the value ‘button’ 
		 * //form[@id='loginForm']/input[4] (7) - Fourth input child element of the form element with attribute named ‘id’ and value ‘loginForm’
		 */

	}

	public static void main(String[] args) throws InterruptedException, IOException, AWTException {
		WebDriverBuilder deiverBuilder = new WebDriverBuilder();
		WebDriver driver = null;
		try {
			driver = deiverBuilder.buildEdgeWebDriver();
			testSelenium(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (driver != null) {
				driver.quit();
			}
		}
		System.exit(0);
	}
}
