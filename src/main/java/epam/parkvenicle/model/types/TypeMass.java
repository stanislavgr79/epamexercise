package epam.parkvenicle.model.types;

public enum TypeMass {SMALL("down 3 tons"), MEDIUM("up to 5 tons");
    private final String  MASS_CARGO;

    TypeMass(String MASS_CARGO) {
        this.MASS_CARGO = MASS_CARGO;
    }


}