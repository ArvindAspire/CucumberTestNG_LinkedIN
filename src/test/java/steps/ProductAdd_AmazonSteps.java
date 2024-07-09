package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.Amazon;

import static hooks.ApplicationHooks.getTag;
import static util.DriverFactory.DATA;

public class ProductAdd_AmazonSteps {

    Amazon amazon;

    public ProductAdd_AmazonSteps(){
        amazon= new Amazon();
    }


    @Given("the user launch the Amazon")
    public void theUserLaunchTheAmazon() {

        System.out.println("Amaon tag is :"+getTag());
        amazon.pageLoaded(DATA.get(getTag()).get("url"));
    }

    @Then("the user validate the amazon title")
    public void theUserValidateTheAmazonTitle() {
        Assert.assertEquals(amazon.getPageTitle(),DATA.get(getTag()).get("title"));
    }
}
