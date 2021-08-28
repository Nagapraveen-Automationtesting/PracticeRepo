package testCases;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.json.JSONException;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TCcccccccccc01 {
	public static void main(String[] args) {
		WebDriver driver;
		try {
			/* ************************* Setup ************************* */
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--start-maximized");
			WebDriverManager.chromedriver().setup();
			HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
			chromePrefs.put("profile.default_content_settings.popups", 0);
			chromePrefs.put("download.prompt_for_download", false);
			options.setExperimentalOption("prefs", chromePrefs);
			options.addArguments("--test-type");
			options.addArguments("--disable-extensions");
			System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
			DesiredCapabilities cap = DesiredCapabilities.chrome();
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
			cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			options.setCapability("goog:loggingPrefs", logPrefs);
			cap.setCapability(ChromeOptions.CAPABILITY, options);
			cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			driver = new ChromeDriver(cap);
			driver.get("https://www.anthem.com/");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Thread.sleep(5000);
			
			/* ****************** Actual script starts ******************* */
			driver.findElement(By.xpath("//a[contains(text(), 'Individual & Family')]")).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath("(//a[contains(text(), 'Individual & Family')])[2]")).click();
			Thread.sleep(5000);
			List<LogEntry> logs = driver.manage().logs().get(LogType.PERFORMANCE).getAll();
			for (Iterator<LogEntry> it = logs.iterator(); it.hasNext();) {
				LogEntry entry = it.next();
				try {
					JSONObject json = new JSONObject(entry.getMessage());
					System.out.println(json);
					if ((json.toString().toLowerCase().contains("Events"))) {
						JSONObject message = json.getJSONObject("message");
						String method = message.getString("method");
						System.out.println(method);
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			driver.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
