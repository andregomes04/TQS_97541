package tqs.lab3.cars;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;


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

	@Test
        void givenManyCars_whenGetCars_thenReturnJsonArray() throws Exception {
        Car c1 = new Car("peugeot", "208");
        Car c2 = new Car("honda", "civic");
        Car c3 = new Car("renault", "clio");

        List<Car> allCars = Arrays.asList(c1, c2, c3);

        when( cms.getAllCars()).thenReturn(allCars);

        mvc.perform(
                get("/api/GetAllCars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].model", is(c1.getModel())))
                .andExpect(jsonPath("$[1].model", is(c2.getModel())))
                .andExpect(jsonPath("$[2].model", is(c3.getModel())));

        verify(cms, times(1)).getAllCars();
    }

	@Test
	void GetCarTest() throws IOException, Exception {
		Car car = new Car("ford", "focus");
		when(cms.getCarDetails(Mockito.anyLong())).thenReturn(Optional.of(car)); // optional.of to match return of cms

		mvc.perform(
                get("/api/GetCar/1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // status isOk() instead of isGenerated() cause car is already generated
                .andExpect(jsonPath("$.maker", is("ford"))).andExpect(jsonPath("$.model", is("focus")));

        verify(cms, times(1)).getCarDetails(Mockito.anyLong());

	}

	@Test
	void GetNonExistingCarTest() throws IOException, Exception {

		when(cms.getCarDetails(Mockito.anyLong())).thenReturn(Optional.empty()); // return empty on call of get car details

		mvc.perform(
                get("/api/GetCar/5").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound()); // status isNotFound() cause car doesnt exist
            
        verify(cms, times(1)).getCarDetails(Mockito.anyLong());
		
	}
}
