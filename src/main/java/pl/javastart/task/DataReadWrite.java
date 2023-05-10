package pl.javastart.task;

import java.io.*;
import java.util.Scanner;

public class DataReadWrite {
    private static final Scanner sc = new Scanner(System.in);

    public static void motApp() throws IOException {
        readFile();
        int option = -1;
        do {
            printOptions();
            option = sc.nextInt();
            sc.nextLine();
            if (option == 0) {
                if (MotQueue.queueSize() == 0) {
                    return;
                } else {
                    saveQueueToTheFile();
                }
            } else if (option == 1) {
                enterVehicleDetailsAndAddToTheQueue();
            } else if (option == 2) {
                getVehicleOutOfTheQueueAndPrint();
            } else {
                System.err.println("Błędny wybór spróbuj jeszcze raz");
            }
        } while (option != 0);
    }

    private static void enterVehicleDetailsAndAddToTheQueue() {
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
        MotQueue.addVehicleToQueue(type, make, model, year, mileage, vin);
    }

    private static final String fileName = "src\\main\\java\\pl\\javastart\\task\\VehicleQueue.csv";

    private static void readFile() throws IOException {
        try (Scanner sc = new Scanner(new File(fileName))
        ) {
            if (sc.hasNextLine()) {
                while (sc.hasNextLine()) {
                    String text = sc.nextLine();
                    String[] split = text.split(",");
                    MotQueue.addVehicleToQueue(split[0], split[1], split[2], Integer.parseInt(split[3]), Integer.parseInt(split[4]), split[5]);
                }
                var fileWriter = new BufferedWriter(new FileWriter(fileName));
                fileWriter.write("");
                fileWriter.close();
            }
        }
    }

    private static void saveQueueToTheFile() throws IOException {
        var fileWriter = new BufferedWriter(new FileWriter(fileName));
        Vehicle vehicle = null;
        do {
            vehicle = MotQueue.getVehicleFromTheQueue();
            if (vehicle != null) {
                fileWriter.write(vehicle.toString());
                fileWriter.newLine();
            }
            fileWriter.flush();
        } while (vehicle != null);
    }

    public static void getVehicleOutOfTheQueueAndPrint() {
        if (MotQueue.queueSize() > 0) {
            Vehicle vehicle = MotQueue.getVehicleFromTheQueue();
            System.out.println(vehicle);
        } else {
            System.out.println("Kolejka pusta");
        }
    }

    private static void printOptions() {
        System.out.println("""
                Wybierz opcję:
                0 -> zakończ program
                1 -> dodaj pojazd do kolejki
                2 -> pobierz pojazd z kolejki
                """);
    }
}