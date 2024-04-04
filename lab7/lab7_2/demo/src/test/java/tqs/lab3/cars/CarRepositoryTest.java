package tqs.lab3.cars;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {
    
    @Autowired // get a test-friendly Entity Manager
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository cr;

    @Test
    public void testValidCarId() {
       
        Car car = new Car("ford", "focus");
        entityManager.persistAndFlush(car);

        Car myDB = cr.findByCarId(car.getCarId());

        assertThat(myDB).isNotNull();
        assertThat(myDB.getModel()).isEqualTo("focus");
        assertThat(myDB.getMaker()).isEqualTo("ford");
    }

    @Test
    public void testInvalidCarId() {
    
        Car myDB = cr.findByCarId(156L);
        assertThat(myDB).isNull();
    }

    @Test
    public void testAllCars() {

        Car car1 = new Car("Ford", "Focus");
        Car car2 = new Car("Toyota", "Corolla");
        Car car3 = new Car("Honda", "Civic");
        Car car4 = new Car("Chevrolet", "Malibu");

        entityManager.persist(car1);
        entityManager.persist(car2);
        entityManager.persist(car3);
        entityManager.persist(car4);
        entityManager.flush();

        List<Car> cars = cr.findAll();

        assertThat(cars).hasSize(4).extracting(Car::getModel).containsOnly("Focus", "Corolla", "Civic", "Malibu");
    }

}
