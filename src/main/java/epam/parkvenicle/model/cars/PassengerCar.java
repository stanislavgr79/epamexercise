package epam.parkvenicle.model.cars;

import epam.parkvenicle.model.types.TypeCar;

import java.util.Objects;

public class PassengerCar extends Car{

    private final TypeCar typeCar;
    private int passengersCapacity;

    public PassengerCar(String modelName, int yearMade, int monthMade, int dayMade, int costCar,
                        int speedCar, int fuelCapacity, int fuelSpendBy100km, TypeCar typeCar, int passengersCapacity)
    {
        super(modelName, yearMade, monthMade, dayMade, costCar, speedCar, fuelCapacity, fuelSpendBy100km);
        this.typeCar = typeCar;
        this.passengersCapacity = passengersCapacity;
    }

    private PassengerCar(Builder builder) {
        super(builder);
        typeCar = builder.typeCar;
        passengersCapacity = builder.passengersCapacity;
    }

    public int getPassengersCapacity() {
        return passengersCapacity;
    }

    public TypeCar getTypeDrive() {
        return typeCar;
    }

    public static class Builder extends Car.Builder<Builder> {
        private final TypeCar typeCar;
        private int passengersCapacity;

        public Builder(TypeCar typeCar) {
            this.typeCar = Objects.requireNonNull(typeCar);
        }

        public Builder setPassengersCapacity(int passengersCapacity) {
            this.passengersCapacity = passengersCapacity;
            return this;
        }

        @Override
        public <R extends Car> R doBuild() {
            return (R) new PassengerCar (this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }

    public void drive(){
        System.out.println("passengerCar drive");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerCar)) return false;
        if (!super.equals(o)) return false;
        PassengerCar that = (PassengerCar) o;
        return getPassengersCapacity() == that.getPassengersCapacity() &&
                typeCar == that.typeCar;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), typeCar, getPassengersCapacity());
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                super.toString() +
                " {typeCar=" + typeCar +
                ", passengersCapacity=" + passengersCapacity +
                '}';
    }
}
