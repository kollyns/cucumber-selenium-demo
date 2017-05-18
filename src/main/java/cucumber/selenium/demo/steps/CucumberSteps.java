package cucumber.selenium.demo.steps;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import gherkin.formatter.model.DataTableRow;

public class CucumberSteps {

    private static final Logger LOG = Logger.getLogger(CucumberSteps.class);
    @Autowired
    private WebDriver driver;

    @Given("^I open \"(.*?)\" url$")
    public void i_open_url(String url) throws Throwable {
	LOG.debug("Browser navigate to url: " + url);
	getDriver().navigate().to(url);
    }

    @And("^I enter the following values:$")
    public void i_enter_text(DataTable datatable) throws Throwable {
	List<DataTableRow> rows = datatable.getGherkinRows();
	for (DataTableRow row : rows) {
	    List<String> cell = row.getCells();

	    String fieldName = cell.get(0);
	    String fieldValue = cell.get(1);

	    WebElement input = getDriver().findElement(By.cssSelector("input[id=" + fieldName + "]"));
	    input.sendKeys(fieldValue);
	}
    }

    @When("^I click the button$")
    public void i_click_the_button() throws Throwable {
	WebElement button = getDriver().findElement(By.cssSelector("button"));
	button.click();
    }

    @And("^I wait (\\d+) seconds$")
    public void i_wait_seconds(long timeout) throws Throwable {
	LOG.debug("Sleeping for [" + timeout + "] seconds. ");
	Thread.sleep(timeout * 1000);
    }

    @Then("^I should see a dialog with:$")
    public void i_should_see_a_dialog_with(DataTable datatable) throws Throwable {
	WebElement dialog = getDriver().findElement(By.cssSelector("div.alert"));
	String actual = dialog.getText();

	List<DataTableRow> rows = datatable.getGherkinRows();

	for (DataTableRow row : rows) {
	    List<String> cell = row.getCells();

	    String expected = cell.get(0);
	    org.junit.Assert.assertEquals("Expected values didn't matched.", expected, actual);
	}

	getDriver().close();
    }

    private WebDriver getDriver() {
	return driver;
    }
}
