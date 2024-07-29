package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass {
public static WebDriver driver;
public Logger logger;
public Properties p;
	
	@BeforeClass(groups = {"Sanity", "Master", "Regression", "Datadriven"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException
	{
		
		//Loading config.properties file
		FileReader file= new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger= LogManager.getLogger(this.getClass());
		
		// If execution env is remote
		
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")){
			DesiredCapabilities cap = new DesiredCapabilities();
			
//			cap.setPlatform(Platform.WIN11);
//			cap.setBrowserName("chrome"); // As we are setting os and browser from xml in this program
			
			//os
			if(os.equalsIgnoreCase("windows")) {
				cap.setPlatform(Platform.WIN11);
			}
			
			else if(os.equalsIgnoreCase("mac")) {
				cap.setPlatform(Platform.MAC);
			}
			else if(os.equalsIgnoreCase("Linux")) {
				cap.setPlatform(Platform.LINUX);
			}
			
			else {
				System.out.println("No matching OS");
				return;
			}
			
			//br
			
			switch(br.toLowerCase()) {
			case "chrome" : cap.setBrowserName("chrome"); break;
			case "edge" : cap.setBrowserName("MicrosoftEdge"); break;
			case "firefox" : cap.setBrowserName("firefox"); break;
			default : System.out.println("No matching browser"); return;
			}
			
			driver = new RemoteWebDriver(new URL("http://localhost:8888/wd/hub"), cap);
//			driver = new RemoteWebDriver(new URL("http://localhost:8888/wd/hub"), cap); // For selenium Grid on standalone system, as localhost be default will be 4444
		}		
		
		
		
		// if its local
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")){
		switch(br.toLowerCase())
		{
		case "chrome" : driver = new ChromeDriver(); break;
		case "edge" : driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		default : System.out.println("Invalid browser name"); return;
		}
		}
		
		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.get(p.getProperty("appURL")); //reading URL from properties file
		driver.manage().window().maximize();
		
	}
	
	public String randomString() {
		String generatedstring = RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomNum() {
		String generatednum = RandomStringUtils.randomNumeric(10);
		return generatednum;
	}
	
	public String randomAlphaNum() {
		String generatedAlphaNum = RandomStringUtils.randomAlphanumeric(8);
		return generatedAlphaNum;
	}
	
	public String captureScreen(String tname) throws IOException {

		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
				
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		
		sourceFile.renameTo(targetFile);
			
		return targetFilePath;

	}
	
	
	@AfterClass(groups = {"Sanity", "Master", "Regression", "Datadriven"})
	public void tearDown(){
		driver.quit();
	}
	
	
	

}
