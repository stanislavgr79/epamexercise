package epam.parkvenicle.cars;

import epam.parkvenicle.types.TypeMass;

import java.util.Objects;

public class TrackCar extends Car {

    private final TypeMass typeMass;
    private int cargoCapacity;

    public TrackCar(String modelName, int yearMade, int monthMade, int dayMade,
                  int costCar, int speedCar, int fuelCapacity, int fuelSpendBy100km,
                  TypeMass typeMass, int cargoCapacity) {
        super(modelName, yearMade, monthMade, dayMade, costCar, speedCar, fuelCapacity, fuelSpendBy100km);
        this.typeMass = typeMass;
        this.cargoCapacity = cargoCapacity;
    }

    private TrackCar(Builder builder) {
        super(builder);
        typeMass = builder.typeMass;
        cargoCapacity = builder.cargoCapacity;
    }

    public int getCargoCapacity() {
        return cargoCapacity;
    }

    public TypeMass getTypeMass() {
        return typeMass;
    }

    public static class Builder extends Car.Builder<Builder> {
        private final TypeMass typeMass;
        private int cargoCapacity;

        public Builder(TypeMass typeMass) {
            this.typeMass = Objects.requireNonNull(typeMass);
        }

        public Builder setCargoCapacity(int cargoCapacity) {
            this.cargoCapacity = cargoCapacity;

            return this;
        }

        @Override
        public <R extends Car> R doBuild() {
            return (R) new TrackCar (this);
        }

        @Override
        public Builder self() {
            return this;
        }

    }
    public void drive(){
        System.out.println("trackCar drive");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrackCar)) return false;
        if (!super.equals(o)) return false;
        TrackCar trackCar = (TrackCar) o;
        return getCargoCapacity() == trackCar.getCargoCapacity() &&
                getTypeMass() == trackCar.getTypeMass();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTypeMass(), getCargoCapacity());
    }

    @Override
    public String toString() {
        return "TrackCar{" +
                super.toString() +
                " {typeMass=" + typeMass +
                ", cargoCapacity=" + cargoCapacity +
                '}';
    }
}
