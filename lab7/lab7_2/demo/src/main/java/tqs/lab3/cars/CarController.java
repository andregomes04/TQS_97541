package tqs.lab3.cars;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")

public class CarController {
    
    @Autowired
    private CarManagerService CarManagerService;

    @PostMapping("/CreateCar")
    public  ResponseEntity<Car> createCar(Car car){
        return new ResponseEntity<Car> (CarManagerService.save(car), HttpStatus.CREATED);
    }

    @GetMapping("/GetAllCars")
    public List<Car> getAllCars(){
        return CarManagerService.getAllCars();
    }

    @GetMapping("/GetCar/{id}")
    public ResponseEntity<Car> GetCarById(@PathVariable long id){
        return ResponseEntity.of(CarManagerService.getCarDetails(id));
    }

}
