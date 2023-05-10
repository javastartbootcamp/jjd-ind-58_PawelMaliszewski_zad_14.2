package pl.javastart.task;

import java.util.Objects;

public class Vehicle {
    private final String type;
    private final String make;
    private final String model;
    private final int year;
    private final int mileage;
    private final String vin;

    public Vehicle(String type, String make, String model, int year, int mileage, String vin) {
        this.type = type;
        this.make = make;
        this.model = model;
        this.year = year;
        this.mileage = mileage;
        this.vin = vin;
    }

    @Override
    public String toString() {
        return type + "," + make + "," + model + "," + year + "," + mileage + "," + vin;
    }
}
