package epam.parkvenicle;

import epam.parkvenicle.cars.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import static epam.parkvenicle.types.TypeDrive.COUNTRY_DRIVE;
import static epam.parkvenicle.types.TypeMass.SMALL;

public class LogicalMethodsTest {
    private ParkVenicles parkVenicles = ParkVenicles.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(LogicalMethodsTest.class);

    @Before
    public void setUp() {
        DbInit.startUp();
    }

    @After
    public void tearDown() {
        parkVenicles.getCars().clear();
    }

    @Test
    public void testResultOfCostAllCars() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        int expected = parkVenicles.logicalMethods.resultOfCostAllCars(parkVenicles);
        int actual = 119000;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSortCarsByFuelSpend() {
        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.now();
        logger.info("TEST {} {}", dt1, dt2);

        boolean checkFuelNextCarUp = true;
        ArrayList<? extends Car> expected = parkVenicles.logicalMethods.sortCarsByFuelSpend(parkVenicles);
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
        ArrayList<? extends Car> expected = parkVenicles.logicalMethods
                .searchCarsByYearMadeUp(1, 1, 2014);
        ArrayList<? super Car> temp = new ArrayList<>();
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
        ArrayList<? extends Car> actual = (ArrayList<? extends Car>) temp;
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

        ArrayList<? extends Car> expected = parkVenicles.logicalMethods
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