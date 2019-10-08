package epam.parkvenicle;


import epam.parkvenicle.comparators.CarComparator;
import epam.parkvenicle.model.cars.BusCar;
import epam.parkvenicle.model.cars.Car;
import epam.parkvenicle.model.cars.PassengerCar;
import epam.parkvenicle.model.cars.TrackCar;
import epam.parkvenicle.model.types.TypeCar;
import epam.parkvenicle.model.types.TypeMass;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


class ParkVehicles {

    final ParkVehicleLogic parkVehicleLogic;

    private List<? extends Car> cars;

    private static ParkVehicles instance;

    private ParkVehicles()
    {
        parkVehicleLogic = new ParkVehicleLogic();
    }

    static ParkVehicles getInstance() {
//        synchronized ()
        if (instance == null) {
            instance = new ParkVehicles();
        }
        return instance;
    }

    class ParkVehicleLogic {

        int resultOfCostAllCars(ParkVehicles parkVehicles) {
            //noinspection OptionalGetWithoutIsPresent
            return parkVehicles.getCars().stream()
                    .mapToInt(Car::getCostCar)
                    .reduce(Integer::sum)
                    .getAsInt();
        }

        List<Car> sortCarsByFuelSpend(ParkVehicles parkVehicles) {
            return parkVehicles.getCars()
                    .stream()
                    .sorted(new CarComparator.FuelSpendBy100kmComparator())
                    .collect(Collectors.toList());
        }

        List<Car> searchCarsByYearMadeUp(int dayMade, int monthMade, int yearMade) {
            Calendar calendar = Calendar.getInstance();
            //noinspection MagicConstant
            calendar.set(yearMade, monthMade - 1, dayMade);
            return getCars()
                    .stream()
                    .filter(o -> o.getMadeYear().getTime() > calendar.getTime().getTime())
                    .collect(Collectors.toList());
        }

        List<Car> searchCarsByRangeThemSpeed(int lowSpeedRangeBorder, int maxSpeedRangeBorder) {
            return getCars()
                    .stream()
                    .filter(o -> o.getSpeedCar() >= lowSpeedRangeBorder & o.getSpeedCar() <= maxSpeedRangeBorder)
                    .sorted(Comparator.comparing(Car::getSpeedCar)
                            .thenComparing(Car::getModelName))
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


    List<? extends Car> getCars() {
        return cars;
    }

    void setCars(List<? extends Car> cars) {
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

    @Override
    public String toString() {
        return "ParkVehicles{" +
                "cars=" + cars +
                '}';
    }
}
