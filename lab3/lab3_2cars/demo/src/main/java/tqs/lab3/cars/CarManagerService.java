package tqs.lab3.cars;

import java.util.List;
import java.util.Optional;

public class CarManagerService {
    
    private CarRepository carRepository;

    public Car save(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long id){
        Car car = carRepository.findByCarId(id);
        if (car != null)
            return Optional.of(car);

        return Optional.empty();
    }
}
