package testcases;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class WebTesting {

	public static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		AndroidDriver driver = new AndroidDriver(capabilities);
		
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy XCover Pro");

		
		capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		capabilities.setCapability("chromeDriverExecutable", "C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver\\win\\chromedriver.exe");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("http://google.com");
		driver.findElement(By.name("q")).sendKeys("Hello Appium");
		
		driver.quit();
		
	}

}