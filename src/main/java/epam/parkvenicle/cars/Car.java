package epam.parkvenicle.cars;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public abstract class  Car {
    private String modelName;
    private Date madeYear;
    private int costCar;
    private int speedCar;
    private int fuelCapacity;
    private int fuelSpendBy100km;

    Car(String modelName, int dayMade, int monthMade, int yearMade,
               int costCar, int speedCar, int fuelCapacity, int fuelSpendBy100km) {
        this.modelName = modelName;
        Calendar calendar = Calendar.getInstance();
        calendar.set(yearMade, monthMade, dayMade);
        this.madeYear = calendar.getTime();
        this.costCar = costCar;
        this.speedCar = speedCar;
        this.fuelCapacity = fuelCapacity;
        this.fuelSpendBy100km = fuelSpendBy100km;
    }

    Car(Builder<?> builder) {
        modelName = builder.modelName;
        madeYear = builder.madeYear;
        costCar = builder.costCar;
        speedCar = builder.speedCar;
        fuelCapacity = builder.fuelCapacity;
        fuelSpendBy100km = builder.fuelSpendBy100km;
    }

    public abstract static class Builder<T extends Builder<T>> {
        private String modelName;
        private Date madeYear;
        private int costCar;
        private int speedCar;
        private int fuelCapacity;
        private int fuelSpendBy100km;

        public T setModelName(String modelName) {
            this.modelName = modelName;
            return self();
        }

        public T setMadeYear(int dayMade, int monthMade, int yearMade) {
            Calendar calendar = Calendar.getInstance();
            //noinspection MagicConstant
            calendar.set(yearMade, monthMade - 1, dayMade);
            this.madeYear = calendar.getTime();
            return self();
        }

        public T setCostCar(int costCar) {
            this.costCar = costCar;
            return self();
        }

        public T setSpeedCar(int speedCar) {
            this.speedCar = speedCar;
            return self();
        }

        public T setFuelCapacity(int fuelCapacity) {
            this.fuelCapacity = fuelCapacity;
            return self();
        }

        public T setFuelSpendBy100km(int fuelSpendBy100km) {
            this.fuelSpendBy100km = fuelSpendBy100km;
            return self();
        }

        public abstract <R extends Car> R doBuild();

        protected abstract T self();
    }

    public String getModelName() {
        return modelName;
    }

    public Date getMadeYear() {
        return madeYear;
    }

    public int getCostCar() {
        return costCar;
    }

    public int getSpeedCar() {
        return speedCar;
    }

    public int getFuelCapacity() {
        return fuelCapacity;
    }

    public int getFuelSpendBy100km() {
        return fuelSpendBy100km;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return getCostCar() == car.getCostCar() &&
                getSpeedCar() == car.getSpeedCar() &&
                getFuelCapacity() == car.getFuelCapacity() &&
                getFuelSpendBy100km() == car.getFuelSpendBy100km() &&
                getModelName().equals(car.getModelName()) &&
                getMadeYear().toString().equals(car.getMadeYear().toString());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getModelName(), getMadeYear(), getCostCar(), getSpeedCar(), getFuelCapacity(), getFuelSpendBy100km());
    }

    @Override
    public String toString() {
        return "Car{" +
                "modelName='" + modelName + '\'' +
                ", madeYear=[" + DateFormat.getDateInstance(DateFormat.MEDIUM).format(madeYear) +
                "], costCar=" + costCar +
                ", speedCar=" + speedCar +
                ", fuelCapacity=" + fuelCapacity +
                ", fuelSpendBy100km=" + fuelSpendBy100km +
                '}';
    }
}
