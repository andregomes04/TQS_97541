package tqs.homework.bus.repository;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tqs.homework.bus.data.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    public Optional<Bus> findById(Long Id);
    public ArrayList<Bus> findAllBuses(); 
    public ArrayList<Bus> findByItenerary(String origin, String destination);
}
