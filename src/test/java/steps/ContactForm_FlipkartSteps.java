package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Flipkart;

import static hooks.ApplicationHooks.getTag;
import static util.DriverFactory.DATA;

public class ContactForm_FlipkartSteps {
    Flipkart flipkart;

    public ContactForm_FlipkartSteps(){
        flipkart= new Flipkart();
    }



    @Given("the user launch the flipkart")
    public void theUserLaunchTheFlipkart() {
        flipkart.pageLoaded(DATA.get(getTag()).get("url"));
    }

    @Then("the user validate the flipkart title")
    public void theUserValidateTheFlipkartTitle() {
        Assert.assertEquals(flipkart.getPageTitle(),DATA.get(getTag()).get("title"));
    }


}
