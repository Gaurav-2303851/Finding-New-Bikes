package TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;


public class zighome {

	public static WebDriver driver;
	public static String url = "https://www.zigwheels.com/";
	public static Properties prop;
	public static Logger logger;
	
	@BeforeClass
	// Method to set up the WebDriver instance based on the browser specified in the configuration file
	public WebDriver driverSetup() throws MalformedURLException
	{
		prop=new Properties();
		try 
		{
			// Loading the configuration file
			prop.load(new FileInputStream("src/test/resources/config.properties"));
			
		}	
		 catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {

					DesiredCapabilities cap=new DesiredCapabilities();

					if(prop.getProperty("os").matches("windows")) {
						cap.setPlatform(Platform.WIN11);
					}
						else if(prop.getProperty("os").matches("mac")) {
							cap.setPlatform(Platform.MAC);
					}
						else {
						System.out.println("no match found");
					}
					
					cap.setBrowserName("chrome");
					WebDriver driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
				}

				else if(prop.getProperty("execution_env").equalsIgnoreCase("local")) {						
		System.out.println(prop.getProperty("browser"));
       // Checking which browser is specified in the configuration file and creating the corresponding WebDriver instance
		if(prop.getProperty("browser").matches("edge")){		
			driver = new EdgeDriver();
		}else if(prop.getProperty("browser").matches("chrome")){	
			
			 ChromeOptions options = new ChromeOptions();
		       options.addArguments("--disable-notifications");
		       options.addArguments("--disable-geolocation");
		        driver = new ChromeDriver(options);
		}
				}

		// Maximizing the window
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Navigating to the URL specified in the static variable
		driver.get(url);
		logger=LogManager.getLogger(this.getClass());
		return driver;
	}
	
	
	public String captureScreen(String tname)throws IOException{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\ER_screenshots\\" + tname +"-" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
	public static void screenshot(String name) {
		TakesScreenshot ss = ((TakesScreenshot) driver);
				File src=ss.getScreenshotAs(OutputType.FILE);
		try {
			File trg=new File("./normal_Screenshot/"+name+".png");
			FileUtils.copyFile(src, trg);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	@AfterClass
	public void teardown() {
		driver.quit();
	}
	

}
