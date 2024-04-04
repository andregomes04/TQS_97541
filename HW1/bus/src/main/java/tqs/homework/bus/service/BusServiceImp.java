package tqs.homework.bus.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import tqs.homework.bus.data.Bus;
import tqs.homework.bus.repository.BusRepository;

@Service
public class BusServiceImp implements BusService{

    private final BusRepository busrep;

    public BusServiceImp(BusRepository br) {
        this.busrep = br;
    }

    @Override
    public Optional<Bus> getBusById(Long id) {

        Optional<Bus> bus1 = busrep.findById(id);
        if (bus1 != null){
            return bus1;
        }
        return Optional.empty();
    }

    @Override
    public List<Bus> getBusesByItenerary(String origin, String destination) {
        ArrayList<Bus> BusList = busrep.findByItenerary(origin, destination);
        return BusList;
    }
    
}
