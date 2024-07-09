package hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import util.ConfigReader;
import util.DriverFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import static util.DriverFactory.getDriver;

public class ApplicationHooks {

    private DriverFactory driverFactory;

    private ConfigReader configReader;

    private ExtentReports extent;

    public static ThreadLocal<String> TAG= new ThreadLocal<>();

    public static String  getTag(){
        return TAG.get();
    }
    @Before(order=0)
    public void getProperty()  {
        configReader= new ConfigReader();
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/report.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

    }

    @Before(order=1)
    public void  launchBrowser(Scenario scenario) throws Throwable {
        driverFactory = new DriverFactory();
        String browser= ConfigReader.getBrowserType();
        System.out.println("Browser is : "+browser);
        driverFactory.init_Browser(browser);
        if(scenario.getSourceTagNames().iterator().hasNext())
            TAG.set(scenario.getSourceTagNames().iterator().next());

    }

    @After(order=0)
    public void quitBrowser(){

        getDriver().quit();
    }

    @After(order=1)
    public void addScreenshot(Scenario scenario) throws IOException {
        File screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
        byte[] fileContent = FileUtils.readFileToByteArray(screenshot);
        scenario.attach(fileContent, "image/png", "screenshot");

    }





}
