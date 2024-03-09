import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.*;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
public class FlightTest_C {

    private WebDriver driver;
    

    @BeforeEach
    public void setUp(WebDriver driver) {
        this.driver = driver;
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void bookFlight() {
        driver.get("https://blazedemo.com/");
        driver.manage().window().setSize(new Dimension(838, 614));
        
        WebElement fromPortDropdown = driver.findElement(By.name("fromPort"));
        Select fromPortSelect = new Select(fromPortDropdown);
        fromPortSelect.selectByVisibleText("San Diego");

        WebElement toPortDropdown = driver.findElement(By.name("toPort"));
        Select toPortSelect = new Select(toPortDropdown);
        toPortSelect.selectByVisibleText("Dublin");

        driver.findElement(By.cssSelector(".btn-primary")).click();

        driver.findElement(By.cssSelector("tr:nth-child(3) .btn")).click();

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

        driver.findElement(By.cssSelector(".btn-primary")).click();

        assertEquals("Thank you for your purchase today!", driver.findElement(By.cssSelector("h1")).getText());
    }
}
