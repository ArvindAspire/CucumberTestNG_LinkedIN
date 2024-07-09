package steps;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Google;

import static hooks.ApplicationHooks.getTag;
import static util.DriverFactory.DATA;

public class AccountsCreate_GoogleSteps {
    Google google;

    public AccountsCreate_GoogleSteps(){
        google= new Google();
    }


    @Given("the user launch the google")
    public void theUserLaunchTheGoogle() {

        google.pageLoaded(DATA.get(getTag()).get("url"));
    }


    @Then("the user validate the google title")
    public void theUserValidateTheGoogleTitle() {
        Assert.assertEquals(google.getPageTitle(),DATA.get(getTag()).get("title"));
    }
}
