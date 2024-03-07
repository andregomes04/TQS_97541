package tqs.lab3.cars;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.io.IOException;


@WebMvcTest(CarController.class)
public class CarControllerTest {

	@Autowired
    private MockMvc mvc;

	@MockBean
	private CarManagerService cms;

	@Test
	void CreateCarTest() throws IOException, Exception {
		Car car1 = new Car("ford", "fiesta");
		when(cms.save(Mockito.any())).thenReturn(car1);

		mvc.perform(
                post("/api/CreateCar").contentType(MediaType.APPLICATION_JSON).content(JsonUtils.toJson(car1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.maker", is("ford"))).andExpect(jsonPath("$.model", is("fiesta")));

        verify(cms, times(1)).save(Mockito.any());

	}

}
