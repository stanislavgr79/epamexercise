package epam.parkvenicle.cars;

import java.util.Comparator;

public abstract class CarComparator implements Comparator<Car> {

    public static class modelComparator extends CarComparator{
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getModelName().compareTo(car2.getModelName());
        }
    }

    public static class madeYearComparator extends CarComparator implements Comparator<Car> {
        @Override
        public int compare(Car car1, Car car2) {
            return (int) (car1.getMadeYear().getTime() - car2.getMadeYear().getTime());
        }
    }

    public static class costCarComparator extends CarComparator{
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getCostCar() - car2.getCostCar();
        }
    }
    public static class speedCarComparator extends CarComparator{
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getSpeedCar() - car2.getSpeedCar();
        }
    }

    public static class fuelCapacityComparator extends CarComparator{
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getFuelCapacity() - car2.getFuelCapacity();
        }
    }

    public static class FuelSpendBy100kmComparator extends CarComparator {
        @Override
        public int compare(Car car1, Car car2) {
            return car1.getFuelSpendBy100km() - car2.getFuelSpendBy100km();
        }

    }

}
