package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class TestNativeApp {

	public static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		File app = new File(".\\TestApp\\selendroid-test-app-0.17.0.apk");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy XCover Pro");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION , "12");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability("noReset", true);	
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		driver = new AndroidDriver(new URL("http://127.0.01:4723/wd/hub"), capabilities);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(3000);
		
		driver.quit();
		

	}

}
