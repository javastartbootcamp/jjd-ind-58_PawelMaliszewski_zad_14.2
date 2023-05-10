package pl.javastart.task;

import java.util.LinkedList;
import java.util.Queue;

public class MotQueue {

    private static final Queue<Vehicle> vehicles = new LinkedList<>();

    public static void addVehicleToQueue(String type, String make, String model, int year, int mileage, String vin) {
        vehicles.add(new Vehicle(type, make, model, year, mileage, vin));
    }

    public static Vehicle getVehicleFromTheQueue() {
        Vehicle vehicle = vehicles.peek();
        vehicles.poll();
        return vehicle;
    }

    public static int queueSize() {
        return vehicles.size();
    }
}
