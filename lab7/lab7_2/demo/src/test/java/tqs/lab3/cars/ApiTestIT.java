package tqs.lab3.cars;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource( locations = "application-integrationtest.properties")
public class ApiTestIT {

    // will need to use the server port for the invocation url
    @LocalServerPort
    int randomServerPort;

    // a REST client that is test-friendly
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }

    @Test
    void whenValidInput_thenCreateCar() {
       Car c1 = new Car("ford", "focus");
       ResponseEntity<Car> entity = restTemplate.postForEntity("/api/CreateCar", c1, Car.class);

       List<Car> found = repository.findAll();
       assertThat(found).extracting(Car::getMaker).containsOnly("ford");
    }

    @Test
     void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("peugeot", "208");
        createTestCar("renault", "clio");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/api/GetAllCars", HttpMethod.GET, null, new ParameterizedTypeReference<List<Car>>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getModel).containsExactly("208", "clio");

    }


    private void createTestCar(String maker, String model) {
        Car c = new Car(maker, model);
        repository.saveAndFlush(c);
    }

    
}
