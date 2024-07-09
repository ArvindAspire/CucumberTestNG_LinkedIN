package util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.IOException;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class DriverFactory {

    private WebDriver driver;

    public static ThreadLocal<WebDriver> tl = new ThreadLocal<>();

    public  void init_Browser(String browser) throws Exception {
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
                tl.set(driver);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
                tl.set(driver);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                driver.manage().window().maximize();
                tl.set(driver);
                break;

            default:
                throw new Exception("Browser Selected is not Valid");


        }

    }
    public static WebDriver getDriver(){
            return tl.get();
        }


    public static ConcurrentHashMap<String, HashMap<String,String>> DATA;

    static {
        try {
            DATA = ExcelReader.getData();

           for(Map.Entry m : DATA.entrySet()){
               System.out.println(m.getKey()+ " : "+m.getValue());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
