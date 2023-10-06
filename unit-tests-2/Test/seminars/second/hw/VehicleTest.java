package seminars.second.hw;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    public void testCarInstanceIsVehicleInstance() {
        Car car = new Car("Компания", "Модель", 2023);
        assertTrue(car instanceof Vehicle);
    }

    @Test
    public void testCarHasFourWheels() {
        Car car = new Car("Компания", "Модель", 2023);
        assertEquals(4, car.getNumWheels());
    }

    @Test
    public void testMotorcycleHasTwoWheels() {
        Motorcycle motorcycle = new Motorcycle("Компания", "Модель", 2023);
        assertEquals(2, motorcycle.getNumWheels());
    }

    @Test
    public void testCarTestDriveSpeed() {
        Car car = new Car("Компания", "Модель", 2023);
        car.testDrive();
        assertEquals(60, car.getSpeed());
    }

    @Test
    public void testMotorcycleTestDriveSpeed() {
        Motorcycle motorcycle = new Motorcycle("Компания", "Модель", 2023);
        motorcycle.testDrive();
        assertEquals(75, motorcycle.getSpeed());
    }

    @Test
    public void testCarParkSpeed() {
        Car car = new Car("Компания", "Модель", 2023);
        car.testDrive();
        car.park();
        assertEquals(0, car.getSpeed());
    }

    @Test
    public void testMotorcycleParkSpeed() {
        Motorcycle motorcycle = new Motorcycle("Компания", "Модель", 2023);
        motorcycle.testDrive();
        motorcycle.park();
        assertEquals(0, motorcycle.getSpeed());
    }
}
