package testcases;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class TestFindingElements {

	public static AndroidDriver driver;
	
	public static void main(String[] args) throws MalformedURLException, InterruptedException {
		
		AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
				new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withAppiumJS(new File("C:\\Program Files\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
				.withArgument(GeneralServerFlag.BASEPATH, "/wd/hub")
				.withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
				.withLogFile(new File(System.getProperty("user.dir") + "\\src\\test\\resources\\logs\\log.txt")));
		
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
		
		//Implicit Wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.id("com.android.permissioncontroller:id/continue_button")).click();
		driver.findElement(By.id("android:id/button1")).click();		
		
//		driver.findElement(By.id("io.selendroid.testapp:id/visibleButtonTest")).click();
		
//		driver.findElement(AppiumBy.accessibilityId("visibleButtonTestCD")).click();
		
//		tagName[@attribute='value'] 
//		driver.findElement(AppiumBy.xpath("//*[@content-desc='visibleButtonTestCD']")).click();
		
//		driver.findElement(By.xpath("//android.widget.Button[@content-desc='visibleButtonTestCD']")).click();
		
//		driver.findElement(AppiumBy.xpath("//*[@text='Display text view']")).click();
		
//		driver.findElement(AppiumBy.xpath("//*[contains(@text, 'Display text')]")).click();
		
//		driver.findElement(By.xpath("//android.widget.Button[@content-desc='visibleButtonTestCD']")).click();

		//Explicit Wait
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("io.selendroid.testapp:id/visibleButtonTest"))).click();

		driver.quit();
		service.stop();
	}
}
