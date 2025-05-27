// How to easily memorize what needs to be done for creating enums having different values associated with different constants.
// The constructor is always private by default.
// Define constants with values, create private field, constructor assigns, getter returns.
// Implicitly, Java does something like:
// public static final Coin PENNY = new Coin(0.01);
public enum Coin {
    // each of these calls the enum’s constructor internally with the argument you provide.
    PENNY(0.01),
    NICKEL(0.05),
    DIME(0.1),
    QUARTER(0.25);

    private final double value;

    private Coin(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
