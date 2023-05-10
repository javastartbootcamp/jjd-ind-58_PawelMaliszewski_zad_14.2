package pl.javastart.task;

import java.io.*;
import java.util.Scanner;

public class DataReadWrite {
    private static final int CLOSE_PROGRAM = 0;
    private static final int ADD_TO_THE_QUEUE = 1;
    private static final int GET_OUT_OF_THE_QUEUE = 2;
    private final Scanner sc = new Scanner(System.in);
    private final MotQueue motQueue = new MotQueue();

    public void motApp() throws IOException {
        readFile();
        clearTheFile();
        int option = -1;
        do {
            printOptions();
            option = sc.nextInt();
            sc.nextLine();
            if (option == CLOSE_PROGRAM) {
                if (motQueue.isEmpty()) {
                    return;
                } else {
                    saveQueueToTheFile();
                }
            } else if (option == ADD_TO_THE_QUEUE) {
                motQueue.addVehicleToTheQueue(createVehicle());
            } else if (option == GET_OUT_OF_THE_QUEUE) {
                getVehicleOutOfTheQueueAndPrint();
            } else {
                System.err.println("Błędny wybór spróbuj jeszcze raz");
            }
        } while (option != 0);
    }

    private void clearTheFile() throws IOException {
        var fileWriter = new BufferedWriter(new FileWriter(fileName));
        fileWriter.write("");
        fileWriter.close();
    }

    private Vehicle createVehicle() {
        System.out.println("Podaj typ pojazdu");
        String type = sc.nextLine();
        System.out.println("Podaj markę");
        String make = sc.nextLine();
        System.out.println("Podaj model");
        String model = sc.nextLine();
        System.out.println("Podaj rok produkcji");
        int year = sc.nextInt();
        System.out.println("Podaj przebieg w km");
        int mileage = sc.nextInt();
        sc.nextLine();
        System.out.println("Podaj numer nadwozia VIN");
        String vin = sc.nextLine();
        return new Vehicle(type, make, model, year, mileage, vin);
    }

    private final String fileName = "src\\main\\java\\pl\\javastart\\task\\VehicleQueue.csv";

    private void readFile() throws IOException {
        try (Scanner sc = new Scanner(new File(fileName))
        ) {
            if (sc.hasNextLine()) {
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] split = text.split(",");
                    Vehicle vehicle = new Vehicle(split[0], split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5]);
                    motQueue.addVehicleToTheQueue(vehicle);
                }
            }
        }
    }

    private void saveQueueToTheFile() throws IOException {
        var fileWriter = new BufferedWriter(new FileWriter(fileName));
        Vehicle vehicle = null;
        do {
            vehicle = motQueue.getVehicleFromTheQueue();
            if (vehicle != null) {
                fileWriter.write(vehicle.toString());
                fileWriter.newLine();
            }
            fileWriter.flush();
        } while (vehicle != null);
    }

    public void getVehicleOutOfTheQueueAndPrint() {
        if (!motQueue.isEmpty()) {
            Vehicle vehicle = motQueue.getVehicleFromTheQueue();
            System.out.println(vehicle);
        } else {
            System.out.println("Kolejka pusta");
        }
    }

    private void printOptions() {
        System.out.print("Wybierz opcję:\n"
                         + CLOSE_PROGRAM + " -> zakończ program\n"
                         + ADD_TO_THE_QUEUE + " -> dodaj pojazd do kolejki\n"
                         + GET_OUT_OF_THE_QUEUE + " -> pobierz pojazd z kolejki\n");
    }
}