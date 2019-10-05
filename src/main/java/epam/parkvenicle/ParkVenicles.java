package epam.parkvenicle;


import epam.parkvenicle.cars.BusCar;
import epam.parkvenicle.cars.Car;
import epam.parkvenicle.cars.PassengerCar;
import epam.parkvenicle.cars.TrackCar;
import epam.parkvenicle.types.TypeCar;
import epam.parkvenicle.types.TypeMass;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class ParkVenicles {

    final LogicalMethods logicalMethods;

    private ArrayList<? extends Car> cars;

    private static ParkVenicles instance;

    private ParkVenicles()
    {
        logicalMethods = new LogicalMethods(this);
    }

    static synchronized ParkVenicles getInstance() {
        if (instance == null) {
            instance = new ParkVenicles();
        }
        return instance;
    }

    ArrayList<? extends Car> getCars() {
        return cars;
    }

    void setCars(ArrayList<? extends Car> cars) {
        this.cars = cars;
    }

    List<PassengerCar> getPassengerCar() {
        return (List<PassengerCar>) cars.stream()
                .filter(o -> o instanceof PassengerCar)
                .collect(Collectors.toList());
    }


    List<TrackCar> getTrackCar() {
        return (List<TrackCar>) cars.stream()
                .filter(o -> (o.getClass().equals(TrackCar.class)))
                .collect(Collectors.toList());
    }

    List<BusCar> getBusCar() {
        return (List<BusCar>) cars.stream()
                .filter(o -> (o.getClass().equals(BusCar.class)))
                .collect(Collectors.toList());
    }

    List<PassengerCar> getPassengerCarsByTypeCar(TypeCar typeCar) {
        return  getPassengerCar().stream()
                .filter(o-> o.getTypeDrive() == typeCar)
                .collect(Collectors.toList());
    }

    List<TrackCar> getTrackCarsByTypeMass(TypeMass typeMass) {
        return  getTrackCar().stream()
                .filter(o-> o.getTypeMass() == typeMass)
                .collect(Collectors.toList());
    }

    PassengerCar getPassengerCarWithMaxPassengersCapacity() {
        PassengerCar passengerCar = null;
        try {
            passengerCar = getPassengerCar().stream()
                    .max(Comparator.comparingInt(PassengerCar::getPassengersCapacity))
                    .orElse(null);
            if(passengerCar == null){
                throw new NullPointerException();
            }
        } catch (NullPointerException e){
            System.out.println("Элемент коллекции не найден.");
        }

        return passengerCar;
    }

    TrackCar getTrackCarByMaxCargoCapacity (){
        TrackCar trackCar = null;
        try {
            trackCar = getTrackCar().stream()
                    .max(Comparator.comparingInt(TrackCar::getCargoCapacity))
                    .orElse(null);
            if (trackCar == null){
                throw new NullPointerException();
            }
        }catch (NullPointerException e){
            System.out.println("Элемент коллекции не найден.");
        }

        return trackCar;
    }



}
