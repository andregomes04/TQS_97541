package tqs.lab4;

import org.slf4j.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.invoke.MethodHandles.lookup;
import static org.slf4j.LoggerFactory.getLogger;
import static org.assertj.core.api.Assertions.assertThat;

import io.github.bonigarcia.seljup.SeleniumJupiter;

@ExtendWith(SeleniumJupiter.class)
class HelloWorldTest {

    static final Logger log = getLogger(lookup().lookupClass());

    /*private WebDriver driver; 

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup(); 
    }

    @BeforeEach
    void setup() {
        driver = new ChromeDriver(); 
    }
    */

    @Test
    void test(ChromeDriver driver) {
        // Exercise
        String sutUrl = "https://www.ultimate-guitar.com/explore";
        driver.get(sutUrl); 
        String title = driver.getTitle(); 
        log.debug("The title of {} is {}", sutUrl, title); 

        // Verify
        assertThat(title).isEqualTo("Explore chords and tabs @ Ultimate-Guitar.Com"); 
    }

    /* 
    @AfterEach
    void teardown() {
        driver.quit(); 
    }
    */

}