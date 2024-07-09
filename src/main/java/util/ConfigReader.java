package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

   private   Properties prop;

    private static String browserType = null;

    private static ThreadLocal<String> tlBrowserType= new ThreadLocal<>();

    public static void setBrowserType(String browser) {
        browserType = browser;
        tlBrowserType.set(browserType);
    }

    public static String getBrowserType() throws Throwable {
        if (browserType != null)
            return tlBrowserType.get();
        else
            throw new RuntimeException("browser not specified in the testng.xml");

    }



}
