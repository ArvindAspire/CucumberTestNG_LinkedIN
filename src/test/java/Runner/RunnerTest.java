package Runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import util.ConfigReader;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"steps","hooks"},
        plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        monochrome = true,
        publish = true,
        tags = ""


)

public class RunnerTest extends AbstractTestNGCucumberTests {

    public String browser;
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {

        return super.scenarios();

    }

    @BeforeMethod
    @Parameters({"browser"})
    public void defineBrowser(String browser) throws Throwable {
        System.out.println("The browser name in test runner is : "+browser);
        ConfigReader.setBrowserType(browser);

    }
}
