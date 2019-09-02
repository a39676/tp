package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;

public class Appium {
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Galaxy Nexus API 29_06");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "10");
        capabilities.setCapability("appPackage", "com.komorebi.memo");
        capabilities.setCapability("appActivity", "com.komorebi.memo.MainActivity");

        @SuppressWarnings("rawtypes")
		AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        Map<String, String> map = androidDriver.getAppStringMap();
        
        System.out.println(map);

        androidDriver.quit();
    }

}
