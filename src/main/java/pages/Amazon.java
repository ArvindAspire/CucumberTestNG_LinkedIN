package pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.DriverFactory;

import java.time.Duration;

import static util.DriverFactory.getDriver;

public class Amazon {



    @FindBy(id="twotabsearchtextbox")
    WebElement searchInp;

    WebDriver driver;

    public Amazon(){
        this.driver= getDriver();
        PageFactory.initElements(driver,this);
    }

    public void pageLoaded(String url){
        driver.get(url);
        FluentWait wait = new FluentWait(driver);
        wait.pollingEvery(Duration.ofSeconds(1));
        wait.withTimeout(Duration.ofSeconds(60));
        wait.ignoring(NoSuchElementException.class);
        wait.ignoring(StaleElementReferenceException.class);
        wait.until(pageLoad -> searchInp.isDisplayed());

    }
    public String getPageTitle(){
        return driver.getTitle();
    }


}
