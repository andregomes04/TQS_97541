package tqs.homework.bus.data;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;
    private String destination;
    private LocalDate date;
    private LocalTime time;
    private int seats;

    public Bus(String origin, String destination, LocalDate date, LocalTime time) {
        this.origin = origin;
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.seats = 30;
    }

    public int getSeats() {
        return seats;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Bus [origin=" + origin + ", destination=" + destination + ", date=" + date + ", time=" + time
                + ", seats=" + seats + "]";
    }
}
