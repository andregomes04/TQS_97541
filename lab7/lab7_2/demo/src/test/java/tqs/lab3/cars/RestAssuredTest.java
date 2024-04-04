package tqs.lab3.cars;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import java.util.Optional;


@WebMvcTest(CarController.class)
public class RestAssuredTest {
    @Autowired
    private MockMvc mvcClient;

    @MockBean
    private CarManagerService cms;

    @Test
    public void testCreateCar() throws Exception {
        Car c1 = new Car("ford", "fiesta");

        when(cms.save(Mockito.any())).thenReturn(c1);

        RestAssuredMockMvc
            .given().mockMvc(mvcClient)
            .when().post("/api/CreateCar")
            .then().log().body()
            .and().body("model", equalTo(c1.getModel()));

        verify(cms, times(1)).save(c1);
    }
}