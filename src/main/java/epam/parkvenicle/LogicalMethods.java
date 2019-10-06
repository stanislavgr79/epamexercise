package epam.parkvenicle;

import epam.parkvenicle.cars.Car;
import epam.parkvenicle.cars.CarComparator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

class LogicalMethods {
    private final ParkVehicles parkVehicles;

    LogicalMethods(ParkVehicles parkVehicles) {
        this.parkVehicles = parkVehicles;
    }

    int resultOfCostAllCars(ParkVehicles parkVehicles){
        //noinspection OptionalGetWithoutIsPresent
        return parkVehicles.getCars().stream()
                .mapToInt(Car::getCostCar)
                .reduce(Integer::sum)
                .getAsInt();
    }

    ArrayList<Car> sortCarsByFuelSpend(ParkVehicles parkVehicles){
        return parkVehicles.getCars()
                .stream()
                .sorted(new CarComparator.FuelSpendBy100kmComparator())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Car> searchCarsByYearMadeUp(int dayMade, int monthMade, int yearMade){
        Calendar calendar = Calendar.getInstance();
        //noinspection MagicConstant
        calendar.set(yearMade, monthMade-1, dayMade);
        return parkVehicles.getCars()
                .stream()
                .filter(o-> o.getMadeYear().getTime() > calendar.getTime().getTime())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Car> searchCarsByRangeThemSpeed(int lowSpeedRangeBorder, int maxSpeedRangeBorder){
        return parkVehicles.getCars()
                .stream()
                .filter(o-> o.getSpeedCar() >= lowSpeedRangeBorder & o.getSpeedCar() <= maxSpeedRangeBorder)
                .sorted(Comparator.comparing(Car::getSpeedCar)
                        .thenComparing(Car::getModelName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
