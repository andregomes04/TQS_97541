package tqs.lab5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightSteps {

    private WebDriver driver;

    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        driver = new ChromeDriver();
        driver.get(url);
    }

    @And("I select {string} as the departure city")
    public void iSelectDepartureCity(String departureCity) {
        WebElement fromPortDropdown = driver.findElement(By.name("fromPort"));
        Select fromPortSelect = new Select(fromPortDropdown);
        fromPortSelect.selectByVisibleText(departureCity);
    }

    @And("I select {string} as the destination city")
    public void iSelectDestinationCity(String destinationCity) {
        WebElement toPortDropdown = driver.findElement(By.name("toPort"));
        Select toPortSelect = new Select(toPortDropdown);
        toPortSelect.selectByVisibleText(destinationCity);
    }

    @And("I click on Find Flights")
    public void iClickOnFindFlightsButton() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @And("I choose the first flight")
    public void iChooseFirstFlight() {
        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();
    }

    @And("I fill in passenger details")
    public void iFillInPassengerDetails() {
        driver.findElement(By.id("inputName")).sendKeys("André");
        driver.findElement(By.id("address")).sendKeys("123 Aveiro");
        driver.findElement(By.id("city")).sendKeys("Aveiro");
        driver.findElement(By.id("state")).sendKeys("Portugal");
        driver.findElement(By.id("zipCode")).sendKeys("14558");
        driver.findElement(By.id("creditCardNumber")).sendKeys("555555555555");
        driver.findElement(By.id("creditCardMonth")).sendKeys("10");
        driver.findElement(By.id("creditCardYear")).sendKeys("405");
        driver.findElement(By.id("nameOnCard")).sendKeys("Andre Gomes");
        driver.findElement(By.id("rememberMe")).click();
    }

    @And("I click on the Purchase Flight button")
    public void iClickOnPurchaseFlightButton() {
        driver.findElement(By.cssSelector(".btn-primary")).click();
    }

    @Then("I should see the confirmation message {string}")
    public void iShouldSeeConfirmationMessage(String confirmationMessage) {
        assertEquals(confirmationMessage, driver.findElement(By.cssSelector("h1")).getText());
        driver.quit();
    }
}
