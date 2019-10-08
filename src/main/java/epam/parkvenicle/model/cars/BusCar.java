package epam.parkvenicle.model.cars;

import epam.parkvenicle.model.types.TypeDrive;

import java.util.Objects;

public class BusCar extends Car{

    private final TypeDrive typeDrive;
    private int passengersCapacity;

    public BusCar(String modelName, int yearMade, int monthMade, int dayMade,
                  int costCar, int speedCar, int fuelCapacity, int fuelSpendBy100km,
                  TypeDrive typeDrive, int passengersCapacity) {
        super(modelName, yearMade, monthMade, dayMade, costCar, speedCar, fuelCapacity, fuelSpendBy100km);
        this.typeDrive = typeDrive;
        this.passengersCapacity = passengersCapacity;
    }

    private BusCar(Builder builder) {
        super(builder);
        typeDrive = builder.typeDrive;
        passengersCapacity = builder.passengersCapacity;
    }

    int getPassengersCapacity() {
        return passengersCapacity;
    }

    TypeDrive getTypeDrive() {
        return typeDrive;
    }

    public static class Builder extends Car.Builder<Builder> {
        private final TypeDrive typeDrive;
        private int passengersCapacity;

        public Builder(TypeDrive typeDrive) {
            this.typeDrive = Objects.requireNonNull(typeDrive);
        }

        public Builder setPassengersCapacity(int passengersCapacity) {
            this.passengersCapacity = passengersCapacity;
            return this;
        }

        @Override
        public <R extends Car> R doBuild() {
            return (R) new BusCar (this);
        }

        @Override
        public Builder self() {
            return this;
        }
    }

    public void drive(){
        System.out.println("busCar drive");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusCar)) return false;
        if (!super.equals(o)) return false;
        BusCar busCar = (BusCar) o;
        return getPassengersCapacity() == busCar.getPassengersCapacity() &&
                getTypeDrive() == busCar.getTypeDrive();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTypeDrive(), getPassengersCapacity());
    }

    @Override
    public String toString() {
        return "BusCar{" +
                super.toString() +
                " {typeDrive=" + typeDrive +
                ", passengersCapacity=" + passengersCapacity +
                '}';
    }
}
