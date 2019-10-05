package epam.parkvenicle;

import epam.parkvenicle.cars.Car;
import epam.parkvenicle.cars.CarComparator;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.stream.Collectors;

class LogicalMethods {
    private final ParkVenicles parkVenicles;

    LogicalMethods(ParkVenicles parkVenicles) {
        this.parkVenicles = parkVenicles;
    }

    int resultOfCostAllCars(ParkVenicles parkVenicles){
        //noinspection OptionalGetWithoutIsPresent
        return parkVenicles.getCars().stream()
                .mapToInt(Car::getCostCar)
                .reduce(Integer::sum)
                .getAsInt();
    }

    ArrayList<Car> sortCarsByFuelSpend(ParkVenicles parkVenicles){
        return parkVenicles.getCars()
                .stream()
                .sorted(new CarComparator.FuelSpendBy100kmComparator())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Car> searchCarsByYearMadeUp(int dayMade, int monthMade, int yearMade){
        Calendar calendar = Calendar.getInstance();
        //noinspection MagicConstant
        calendar.set(yearMade, monthMade-1, dayMade);
        return parkVenicles.getCars()
                .stream()
                .filter(o-> o.getMadeYear().getTime() > calendar.getTime().getTime())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    ArrayList<Car> searchCarsByRangeThemSpeed(int lowSpeedRangeBorder, int maxSpeedRangeBorder){
        return parkVenicles.getCars()
                .stream()
                .filter(o-> o.getSpeedCar() >= lowSpeedRangeBorder & o.getSpeedCar() <= maxSpeedRangeBorder)
                .sorted(Comparator.comparing(Car::getSpeedCar)
                        .thenComparing(Car::getModelName))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
