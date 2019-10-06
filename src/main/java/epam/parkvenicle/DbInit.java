package epam.parkvenicle;

import epam.parkvenicle.cars.BusCar;
import epam.parkvenicle.cars.Car;
import epam.parkvenicle.cars.PassengerCar;
import epam.parkvenicle.cars.TrackCar;

import java.util.ArrayList;

import static epam.parkvenicle.types.TypeCar.ARENDA;
import static epam.parkvenicle.types.TypeCar.TAXI;
import static epam.parkvenicle.types.TypeDrive.CITY_DRIVE;
import static epam.parkvenicle.types.TypeDrive.COUNTRY_DRIVE;
import static epam.parkvenicle.types.TypeMass.MEDIUM;
import static epam.parkvenicle.types.TypeMass.SMALL;

class DbInit {

    static void startUp(){
        ParkVehicles parkVehicles = ParkVehicles.getInstance();
         parkVehicles.setCars((ArrayList<? extends Car>) initializeCars());

    }

    private static ArrayList<? super Car> initializeCars(){
        BusCar busCar1 = new BusCar.Builder(CITY_DRIVE)
                .setModelName("Ikarus")
                .setMadeYear(11,1,2010)
                .setCostCar(20000)
                .setSpeedCar(100)
                .setFuelCapacity(250)
                .setFuelSpendBy100km(14)
                .setPassengersCapacity(32)
                .doBuild();

        BusCar busCar2 = new BusCar.Builder(COUNTRY_DRIVE)
                .setModelName("Mersedes")
                .setMadeYear(10,10,2015)
                .setCostCar(25000)
                .setSpeedCar(130)
                .setFuelCapacity(250)
                .setFuelSpendBy100km(14)
                .setPassengersCapacity(40)
                .doBuild();

        TrackCar trackCar1 = new TrackCar.Builder(SMALL)
                .setModelName("Volvo")
                .setMadeYear(14, 5, 2014)
                .setCostCar(20000)
                .setSpeedCar(120)
                .setFuelCapacity(200)
                .setFuelSpendBy100km(14)
                .setCargoCapacity(3000)
                .doBuild();

        TrackCar trackCar2 = new TrackCar.Builder(MEDIUM)
                .setModelName("Mersedes")
                .setMadeYear(16, 8, 2009)
                .setCostCar(35000)
                .setSpeedCar(140)
                .setFuelCapacity(380)
                .setFuelSpendBy100km(15)
                .setCargoCapacity(10000)
                .doBuild();

        PassengerCar passengerCar1 = new PassengerCar.Builder(TAXI)
                .setModelName("BMW 540")
                .setMadeYear(6, 1, 2013)
                .setCostCar(12000)
                .setSpeedCar(170)
                .setFuelCapacity(75)
                .setFuelSpendBy100km(10)
                .setPassengersCapacity(4)
                .doBuild();

        PassengerCar passengerCar2 = new PassengerCar.Builder(ARENDA)
                .setModelName("Audi A4")
                .setMadeYear(23, 8, 2012)
                .setCostCar(7000)
                .setSpeedCar(160)
                .setFuelCapacity(70)
                .setFuelSpendBy100km(9)
                .setPassengersCapacity(4)
                .doBuild();

        ArrayList<? super Car> listCars = new ArrayList<>();
        listCars.add(busCar1);
        listCars.add(busCar2);
        listCars.add(trackCar1);
        listCars.add(trackCar2);
        listCars.add(passengerCar1);
        listCars.add(passengerCar2);
        return listCars;
    }
}
