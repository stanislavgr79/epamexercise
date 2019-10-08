package epam.parkvenicle;

import epam.parkvenicle.comparators.CarComparator;
import epam.parkvenicle.model.cars.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.*;

import static epam.parkvenicle.model.types.TypeCar.ARENDA;
import static epam.parkvenicle.model.types.TypeCar.TAXI;
import static epam.parkvenicle.model.types.TypeDrive.CITY_DRIVE;
import static epam.parkvenicle.model.types.TypeDrive.COUNTRY_DRIVE;
import static epam.parkvenicle.model.types.TypeMass.MEDIUM;
import static epam.parkvenicle.model.types.TypeMass.SMALL;

public class ParkVehicleLogicTest {
    private static ParkVehicles parkVehicles = ParkVehicles.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ParkVehicleLogicTest.class);

    static class TestDataStorage {

        private List<? extends Car> busesCar = Arrays.asList(
                new BusCar.Builder(CITY_DRIVE)
                        .setModelName("Ikarus")
                        .setMadeYear(11, 1, 2010)
                        .setCostCar(20000)
                        .setSpeedCar(100)
                        .setFuelCapacity(250)
                        .setFuelSpendBy100km(14)
                        .setPassengersCapacity(32)
                        .doBuild(),

                new BusCar.Builder(COUNTRY_DRIVE)
                .setModelName("Mersedes")
                    .setMadeYear(10, 10, 2015)
                    .setCostCar(25000)
                    .setSpeedCar(130)
                    .setFuelCapacity(250)
                    .setFuelSpendBy100km(14)
                    .setPassengersCapacity(40)
                    .doBuild()
        );

        private List<? extends Car> tracksCar = Arrays.asList(
                new TrackCar.Builder(SMALL)
                        .setModelName("Volvo")
                        .setMadeYear(14, 5, 2014)
                        .setCostCar(20000)
                        .setSpeedCar(120)
                        .setFuelCapacity(200)
                        .setFuelSpendBy100km(14)
                        .setCargoCapacity(3000)
                        .doBuild(),

                new TrackCar.Builder(MEDIUM)
                        .setModelName("Mersedes")
                        .setMadeYear(16, 8, 2009)
                        .setCostCar(35000)
                        .setSpeedCar(140)
                        .setFuelCapacity(380)
                        .setFuelSpendBy100km(15)
                        .setCargoCapacity(10000)
                        .doBuild()
        );

        private List<? extends Car> passengersCar = Arrays.asList(
                new PassengerCar.Builder(TAXI)
                        .setModelName("BMW 540")
                        .setMadeYear(6, 1, 2013)
                        .setCostCar(12000)
                        .setSpeedCar(170)
                        .setFuelCapacity(75)
                        .setFuelSpendBy100km(10)
                        .setPassengersCapacity(4)
                        .doBuild(),

                new PassengerCar.Builder(ARENDA)
                        .setModelName("Audi A4")
                        .setMadeYear(23, 8, 2012)
                        .setCostCar(7000)
                        .setSpeedCar(160)
                        .setFuelCapacity(70)
                        .setFuelSpendBy100km(9)
                        .setPassengersCapacity(4)
                        .doBuild()
        );
    }

    @Parameterized.Parameters
    public static List<? extends Car> data() {
        List<? super Car> temp = new ArrayList<>();
        temp.addAll(new TestDataStorage().busesCar);
        temp.addAll(new TestDataStorage().tracksCar);
        temp.addAll(new TestDataStorage().passengersCar);
        return (List<? extends Car>)temp;
    }


        @Before
    public void setUp() {
        parkVehicles.setCars(data());
    }

    @After
    public void tearDown() {
        parkVehicles.getCars().clear();
    }

    @Test
    public void testResultOfCostAllCars() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        int expected = parkVehicles.parkVehicleLogic.resultOfCostAllCars(parkVehicles);
        int actual = 119000;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortCarsByFuelSpend() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        boolean checkFuelNextCarUp = true;
        List<? extends Car> expected = parkVehicles.parkVehicleLogic.sortCarsByFuelSpend(parkVehicles);
        int fuelSpend = 0;
        for (Car el : expected) {
            if (el.getFuelSpendBy100km() < fuelSpend) {
                checkFuelNextCarUp = false;
                break;
            } else {
                fuelSpend = el.getFuelSpendBy100km();
            }
        }
        Assert.assertTrue(checkFuelNextCarUp);
    }

    @Test
    public void testSearchCarsByYearMadeUp() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        List<? extends Car> expected = parkVehicles.parkVehicleLogic
                .searchCarsByYearMadeUp(1, 1, 2014);
        List<? super Car> temp = new ArrayList<>();
        Car busCar1 = new BusCar.Builder(COUNTRY_DRIVE)
                .setModelName("Mersedes")
                .setMadeYear(10,10,2015)
                .setCostCar(25000)
                .setSpeedCar(130)
                .setFuelCapacity(250)
                .setFuelSpendBy100km(14)
                .setPassengersCapacity(40)
                .doBuild();
        Car trackCar1 = new TrackCar.Builder(SMALL)
                .setModelName("Volvo")
                .setMadeYear(14, 5, 2014)
                .setCostCar(20000)
                .setSpeedCar(120)
                .setFuelCapacity(200)
                .setFuelSpendBy100km(14)
                .setCargoCapacity(3000)
                .doBuild();

        temp.add(busCar1);
        temp.add(trackCar1);

        List<? extends Car> actual = (List<? extends Car>) temp;

        boolean checkSize = true;
        boolean checkElement = true;
        if (expected.size() != actual.size()) checkSize = false;

        expected.sort(new CarComparator.madeYearComparator());
        actual.sort(new CarComparator.madeYearComparator());
        Assert.assertTrue(checkSize);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSearchCarsByRangeThemSpeed() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        List<? extends Car> expected = parkVehicles.parkVehicleLogic
                .searchCarsByRangeThemSpeed(120,130);
        boolean checkSpeed = true;
        for(Car el: expected){
            int speed = el.getSpeedCar();
            if(speed < 120 || speed > 130){
                checkSpeed = false;
                break;
            }
        }
        Assert.assertTrue(checkSpeed);
    }
}