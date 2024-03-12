package com.example.webpages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BlazeDemoPage {

    @FindBy(name = "fromPort")
    private WebElement fromPortDropdown;

    @FindBy(name = "toPort")
    private WebElement toPortDropdown;

    @FindBy(css = ".btn-primary")
    private WebElement findFlightsButton;

    @FindBy(css = "tr:nth-child(3) .btn")
    private WebElement chooseFlightButton;

    @FindBy(id = "inputName")
    private WebElement inputName;

    @FindBy(id = "address")
    private WebElement address;

    @FindBy(id = "city")
    private WebElement city;

    @FindBy(id = "state")
    private WebElement state;

    @FindBy(id = "zipCode")
    private WebElement zipCode;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumber;

    @FindBy(id = "creditCardMonth")
    private WebElement creditCardMonth;

    @FindBy(id = "creditCardYear")
    private WebElement creditCardYear;

    @FindBy(id = "nameOnCard")
    private WebElement nameOnCard;

    @FindBy(id = "rememberMe")
    private WebElement rememberMeCheckbox;

    @FindBy(css = ".btn-primary")
    private WebElement purchaseFlightButton;

    @FindBy(css = "h1")
    private WebElement confirmationHeader;

    public BlazeDemoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void selectDepartureCity(String city) {
        Select fromPortSelect = new Select(fromPortDropdown);
        fromPortSelect.selectByVisibleText(city);
    }

    public void selectDestinationCity(String city) {
        Select toPortSelect = new Select(toPortDropdown);
        toPortSelect.selectByVisibleText(city);
    }

    public void clickFindFlightsButton() {
        findFlightsButton.click();
    }

    public void chooseFlight() {
        chooseFlightButton.click();
    }

    public void fillPassengerDetails(String name, String addr, String cityName, String stateName, String zip, String cardNumber, String month, String year, String cardHolderName) {
        inputName.sendKeys(name);
        address.sendKeys(addr);
        city.sendKeys(cityName);
        state.sendKeys(stateName);
        zipCode.sendKeys(zip);
        creditCardNumber.sendKeys(cardNumber);
        creditCardMonth.sendKeys(month);
        creditCardYear.sendKeys(year);
        nameOnCard.sendKeys(cardHolderName);
        rememberMeCheckbox.click();
    }

    public void clickPurchaseFlightButton() {
        purchaseFlightButton.click();
    }

    public String getConfirmationHeader() {
        return confirmationHeader.getText();
    }
}