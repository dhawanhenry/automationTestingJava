package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class TestHardwareKeys {

	public static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withLogFile(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\log.txt"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub"));
				
		service.start();
		
		File app = new File(".\\TestApp\\selendroid-test-app-0.17.0.apk");		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 5");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "io.selendroid.testapp.HomeScreenActivity");
		capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "io.selendroid.testapp");
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		Thread.sleep(3000);		
		driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("android:id/button1")).click();		
		Thread.sleep(3000);		

		driver.findElement(By.id("io.selendroid.testapp:id/buttonStartWebview")).click();
		Thread.sleep(3000);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		Thread.sleep(3000);
		//driver.pressKey(new KeyEvent(AndroidKey.HOME));
		driver.findElement(By.id("io.selendroid.testapp:id/my_text_field")).click();
			
//		driver.pressKey(new KeyEvent(AndroidKey.A));
//		driver.pressKey(new KeyEvent(AndroidKey.P));
//		driver.pressKey(new KeyEvent(AndroidKey.P));
//		driver.pressKey(new KeyEvent(AndroidKey.I));
//		driver.pressKey(new KeyEvent(AndroidKey.U));
//		driver.pressKey(new KeyEvent(AndroidKey.M));
		
		Actions action = new Actions(driver);
		action.sendKeys("Appium").perform();
		
		
				
		driver.toggleWifi();
		Thread.sleep(3000);
		try {
			driver.toggleAirplaneMode();
		}catch (Throwable t) {
			System.out.print("Airplane mode active");
		}
		
		
		Thread.sleep(10000);
		
		driver.quit();
		service.stop();
		
		
	}

}
