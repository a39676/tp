package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class Appium {
	
//	public static void main(String[] args) throws MalformedURLException, InterruptedException {
//
//        DesiredCapabilities capabilities = new DesiredCapabilities();
//        capabilities.setCapability("deviceName", "Galaxy Nexus API 29_06");
//        capabilities.setCapability("automationName", "Appium");
//        capabilities.setCapability("platformName", "Android");
//        capabilities.setCapability("platformVersion", "10");
//        capabilities.setCapability("appPackage", "com.komorebi.memo");
//        capabilities.setCapability("appActivity", "com.komorebi.memo.MainActivity");
//
//        @SuppressWarnings("rawtypes")
//		AndroidDriver androidDriver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
//
//        Map<String, String> map = androidDriver.getAppStringMap();
//        
//        System.out.println(map);
//
//        androidDriver.quit();
//    }
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws MalformedURLException, InterruptedException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "127.0.0.1:62001");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appPackage", "com.tencent.wework");
        capabilities.setCapability("appActivity", "com.tencent.wework.launch.LaunchSplashActivity");
//        capabilities.setCapability("chromeOptions:androidProcess", "com.tencent.mm:tools");
//        capabilities.setCapability("appActivity", "com.tencent.wecast.sender.wechatwork.activity.WeCastMainActivity");

		AndroidDriver d = null;
        try {
        	d = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        	
        	Map<String, String> map = d.getAppStringMap();
        	System.out.println(map);

        	List cjvList = d.findElements(By.id("cjv"));
        	AndroidElement tmp = null;
        	AndroidElement workTable = null;
        	for(Object i : cjvList) {
        		if(i instanceof AndroidElement) {
        			tmp = (AndroidElement) i;
        			System.out.println(tmp.getText());
        			System.out.println(tmp.getId());
        			
        			if(tmp.getText() != null) {
        				if(tmp.getText().equals("工作台")) {
        					workTable = (AndroidElement) i;
        				}
        			}
        		}
        	}
        	
        	if(workTable != null) {
        		workTable.click();
        	}
        	
        	Thread.sleep(3000L);
        	
        	List cz3List = d.findElements(By.id("cz3"));
        	AndroidElement miniP = null;
        	for(Object i : cz3List) {
        		if(i instanceof AndroidElement) {
        			tmp = (AndroidElement) i;
        			System.out.println(tmp.getText());
        			System.out.println(tmp.getId());
        			
        			if(tmp.getText() != null) {
        				if(tmp.getText().equals("东方思维易办公（测试）")) {
        					miniP = (AndroidElement) i;
        				}
        			}
        		}
        	}
        	if(miniP != null) {
        		miniP.click();
        	}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(d != null) {
				d.quit();
			}
		}
        
    }

}
