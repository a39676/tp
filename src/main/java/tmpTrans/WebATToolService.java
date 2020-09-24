package tmpTrans;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import at.webDriver.pojo.constant.RegexConstant;

public class WebATToolService {

	public void tabSwitch(WebDriver driver, Integer tabIndex) {
		if (tabIndex == null || tabIndex < 0) {
			tabIndex = 0;
		}

		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());

		if (tabIndex > tabs.size() - 1) {
			tabIndex = tabs.size() - 1;
		}

		driver.switchTo().window(tabs.get(tabIndex));
	}

	public void closeTab(WebDriver driver, Integer closeTabIndex) {
		/*
		 * 待完善指定标签关闭
		 */
		driver.close();
	}

	public void dragAndDrop(WebDriver driver, WebElement sourceElement, WebElement targetElement) {
		Actions builder = new Actions(driver);
		Action dragAndDrop = builder.clickAndHold(sourceElement).moveToElement(targetElement).release(targetElement)
				.build();
		dragAndDrop.perform();
	}

	public String saveImg(WebElement ele, String folderPath) throws IOException {
		if (ele == null) {
			return null;
		}
		String src = ele.getAttribute("src");
		if (StringUtils.isBlank(src)) {
			return null;
		}

		URL imageURL = new URL(src);
		BufferedImage saveImage = ImageIO.read(imageURL);
		String fileName = findFileNameFromUrl(src);
		String suffix = findFileNameSuffixFromUrl(src);

		String filePath = folderPath + File.separator + fileName;
		ImageIO.write(saveImage, suffix, new File(filePath));

		return filePath;
	}

	public String findFileNameFromUrl(String url) {
		Pattern fileNamePattern = Pattern.compile(RegexConstant.fileNameRegex);
		Matcher fileNameMatcher = fileNamePattern.matcher(url);

		if (fileNameMatcher.find()) {
			return fileNameMatcher.group(0);
		} else {
			return null;
		}
	}

	public String findFileNameSuffixFromUrl(String str) {
		Pattern fileNameSuffixPattern = Pattern.compile(RegexConstant.fileNameSuffixRegex);
		Matcher fileNameSuffixMatcher = fileNameSuffixPattern.matcher(str);

		if (fileNameSuffixMatcher.find()) {
			if (fileNameSuffixMatcher.groupCount() > 0) {
				String s = fileNameSuffixMatcher.group(0);
				return s.substring(0, s.length());
			}
		}
		return null;
	}

	public boolean alertExists(WebDriver d) {
		try {
			d.switchTo().alert();
			return true;
		} catch (NoAlertPresentException Ex) {
			return false;
		}
	}

	public WebElement filterByCorrectText(List<WebElement> eleList, String text) {
		WebElement targetEle = null;
		if(eleList == null || eleList.size() < 1 || StringUtils.isBlank(text)) {
			return targetEle;
		}
		for(int i = 0; i < eleList.size() && targetEle != null; i++) {
			if(text.equals(eleList.get(i).getText())) {
				targetEle = eleList.get(i);
			}
		}
		return targetEle;
	}
	
	public List<WebElement> filterByFuzzyText(List<WebElement> eleList, String text) {
		List<WebElement> resultList = new ArrayList<WebElement>();
		if(eleList == null || eleList.size() < 1 || StringUtils.isBlank(text)) {
			return resultList;
		}
		String tmpText = null;
		for(int i = 0; i < eleList.size(); i++) {
			tmpText = eleList.get(i).getText();
			if(tmpText != null && tmpText.contains(text)) {
				resultList.add(eleList.get(i));
			}
		}
		return resultList;
	}
}
