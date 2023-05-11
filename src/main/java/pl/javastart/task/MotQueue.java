package pl.javastart.task;

import java.util.LinkedList;
import java.util.Queue;

public class MotQueue {

    private final Queue<Vehicle> vehicles = new LinkedList<>();

    public void addVehicleToTheQueue(Vehicle vehicle) {
        vehicles.add(vehicle);
    }

    public Vehicle getVehicleFromTheQueue() {
        Vehicle vehicle = vehicles.peek();
        vehicles.poll();
        return vehicle;
    }

    public boolean isEmpty() {
        return vehicles.isEmpty();
    }

    public Queue<Vehicle> getQueue() {
        return vehicles;
    }
}
