package tqs.homework.bus.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import tqs.homework.bus.data.Bus;
import tqs.homework.bus.service.BusService;

@RestController
public class BusController {

    private BusService busser;
    
    public BusController(BusService br){
        this.busser = br;
    }
    
    @GetMapping("/")
    public String getPage(){
        return "welcome";
    }

    @GetMapping("/buses")
    public List<Bus> getAllBuses(){
        return busser.getAllBuses();
    }
}
