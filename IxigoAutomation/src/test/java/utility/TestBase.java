package utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	WebDriver driver;
	Properties prop;
	FileInputStream fis = null;

	@BeforeSuite
	public void prerequisites() {

		prop = new Properties();
		try {
			prop.load(new FileInputStream(System.getProperty("user.dir") + "/config.properties"));
			System.out.println("Config file found");
		} catch (IOException e) {
			System.out.println("Properties Files not forund..!");
		}
	}

	@BeforeMethod
	public void setUp() {

		driver.get("https://www.ixigo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

	@AfterSuite()
	public void postTasks() throws IOException {

		if (fis != null) {
			fis.close();
		}
	}
}
