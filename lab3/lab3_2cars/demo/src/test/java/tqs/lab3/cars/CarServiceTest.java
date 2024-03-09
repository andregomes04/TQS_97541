package tqs.lab3.cars;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    @Mock( lenient = true)
    private CarRepository cr;

    @InjectMocks
    private CarManagerService cms;

    Car c1 = new Car("ford", "focus");
    Car c2 = new Car("ford", "fiesta");
    Car c3 = new Car("peugeot", "208");

    @BeforeEach
    public void setUp() {

        //these expectations provide an alternative to the use of the repository
        List<Car> allCars = Arrays.asList(c1,c2,c3);

        Mockito.when(cr.save(c1)).thenReturn(c1);
        Mockito.when(cr.save(c2)).thenReturn(c2);
        Mockito.when(cr.save(c3)).thenReturn(c3);
        Mockito.when(cr.findAll()).thenReturn(allCars);
        Mockito.when(cr.findByCarId(c1.getCarId())).thenReturn(c1);
        Mockito.when(cr.findByCarId(c2.getCarId())).thenReturn(c2);
        Mockito.when(cr.findByCarId(c3.getCarId())).thenReturn(c3);
    }

    @Test
    public void TestSave() {
        assertThat(cms.save(c1).getMaker()).isEqualTo("ford");
        assertThat(cms.save(c2).getModel()).isEqualTo("fiesta");
        Mockito.verify(cr, VerificationModeFactory.times(1)).save(c1);
        Mockito.verify(cr, VerificationModeFactory.times(1)).save(c2);
    }

    @Test
    public void TestGetAllCars(){
        List<Car> myCars = cms.getAllCars();
        assertThat(myCars).hasSize(3).extracting(Car::getModel).contains(c1.getModel(), c2.getModel(), c3.getModel());
        Mockito.verify(cr, VerificationModeFactory.times(1)).findAll();
    }

    @Test 
    public void TestGetCarById(){
        assertThat(cms.getCarDetails(c3.getCarId())).isEqualTo(Optional.of(c3));
        Mockito.verify(cr, VerificationModeFactory.times(1)).findByCarId(c3.getCarId());
    }

    @Test 
    public void TestGetWrongCarById(){
        assertThat(cms.getCarDetails(10L)).isEmpty();
        Mockito.verify(cr, VerificationModeFactory.times(1)).findByCarId(10L);
    }
}
