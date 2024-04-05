package tqs.homework.bus.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tqs.homework.bus.data.Bus;
import tqs.homework.bus.repository.BusRepository;

@Service
public class BusService {
    private BusRepository br;

    public Bus save(Bus bus) {
        return br.save(bus);
    }

    public List<Bus> getAllBuses(){
        return br.findAllBuses();
    }
}
