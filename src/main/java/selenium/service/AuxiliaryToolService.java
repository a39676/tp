package selenium.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;

import selenium.constant.RegexConstant;

public class AuxiliaryToolService {

	public void saveImageFromWebElement(WebElement webElement, String fileName) {
		/*
		 * TODO testing
		 */
		String src = webElement.getAttribute("src");
//		\.\w{3,4}($|\?)
		
//		String suffix = 
//		ImageIO.write(im, formatName, output);
	}
	
	public static void main(String[] args) {
		String url = "http://www.somthing.com/urlPart/someFiles.som";
		
		Pattern fileNameSuffixPattern = Pattern.compile(RegexConstant.fileNameSuffixRegex);
		Matcher fileNameSuffixMatcher = fileNameSuffixPattern.matcher(url);
		Pattern fileNamePattern = Pattern.compile(RegexConstant.fileNameRegex);
		Matcher fileNameMatcher = fileNamePattern.matcher(url);
		
		
		if(fileNameSuffixMatcher.find()) {
			System.out.println(fileNameSuffixMatcher.groupCount());
			System.out.println(fileNameSuffixMatcher.group(0));
			System.out.println(fileNameSuffixMatcher.group(1));
		} else {
			System.out.println("f");
		}
		
		
		if(fileNameMatcher.find()) {
			System.out.println(fileNameMatcher.groupCount());
			System.out.println(fileNameMatcher.group(0));
		} else {
			System.out.println("f");
		}
	}
}
