package tqs.lab3.cars;

import java.util.List;

public interface CarRepository {

    public Car findByCarId(long id);
    public List<Car> findAll();
    
}