package tqs.lab3.cars;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "car")
public class Car {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    private String maker, model;

    public Car(){

    }

    public Car(String maker, String model){
        this.maker = maker;
        this.model = model;
    }

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getMaker() {
        return maker;
    }

    public void setMaker(String maker) {
        this.maker = maker;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car Id: " + carId + " ; Model: " + model + " ; Maker: " + maker + ";" ;
    }
    
}
