package tqs.homework.bus.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import tqs.homework.bus.data.Bus;

public interface BusRepository extends JpaRepository<Bus, Long> {
    public List<Bus> findAllBuses(); 
    public List<Bus> findByItenerary(String origin, String destination);
}
