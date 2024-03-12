package com.example.tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Dimension;
import com.example.webpages.BlazeDemoPage;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import io.github.bonigarcia.seljup.DockerBrowser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.github.bonigarcia.seljup.BrowserType.FIREFOX;;

@ExtendWith(SeleniumJupiter.class)
public class FlightTest {

    private WebDriver driver;
    private BlazeDemoPage blazeDemoPage;

    @BeforeEach
    public void setUp(@DockerBrowser(type = FIREFOX) WebDriver driver) {
        this.driver = driver;
        blazeDemoPage = new BlazeDemoPage(driver);
        driver.manage().window().setSize(new Dimension(838, 614));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void bookFlight() {
        driver.get("https://blazedemo.com/");
        
        blazeDemoPage.selectDepartureCity("San Diego");
        blazeDemoPage.selectDestinationCity("Dublin");
        blazeDemoPage.clickFindFlightsButton();
        blazeDemoPage.chooseFlight();
        blazeDemoPage.fillPassengerDetails("André", "123 Aveiro", "Aveiro", "Portugal", "14558", "555555555555", "10", "405", "Andre Gomes");
        blazeDemoPage.clickPurchaseFlightButton();

        assertEquals("Thank you for your purchase today!", blazeDemoPage.getConfirmationHeader());
    }
}