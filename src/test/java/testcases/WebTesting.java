package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class WebTesting {

	public static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withLogFile(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\log.txt"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub"));		
				
		/*
		 * Short Code to start Appium Server 	
		 * 	
		 * AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder().usingPort(4723);
			
			AppiumDriverLocalService server = AppiumDriverLocalService.buildService(serviceBuilder);
			server.start();
		 * 
		 * */
		
		
		service.start();
 
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy XCover Pro");
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
		capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));
		capabilities.setCapability("chromeDriverExecutable", "C:\\Users\\dhawa\\node_modules\\appium\\node_modules\\appium-chromedriver\\chromedriver.exe");
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get("https:google.com");
		
		Thread.sleep(5000);
		
		driver.quit();
		service.stop();
	
	
	}

}
