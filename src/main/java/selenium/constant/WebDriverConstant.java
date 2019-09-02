package selenium.constant;

import selenium.type.BrowserConfigType;

public class WebDriverConstant {

	public static final String chromeDriver = BrowserConfigType.chrome.getDriver();
	public static final String fireFoxDriver = BrowserConfigType.fireFox.getDriver();
	public static final String geckoDriver = BrowserConfigType.gecko.getDriver();
	public static final String edgeDriver = BrowserConfigType.edge.getDriver();
	
}
