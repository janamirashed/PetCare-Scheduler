import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetCareScheduler {
    private static Scanner scanner =  new Scanner(System.in);
    private static List<Pet> pets = new ArrayList<>();
    private static List<Appointment>  appointments = new ArrayList<>();

    public static void main(String[] args) {
        loadDataFromFile();
        boolean running = true;
        while (running) {
            System.out.println("\n=== Pet Care Scheduler ===");
            System.out.println("1. Register Pet");
            System.out.println("2. Schedule Appointment");
            System.out.println("3. Store Data");
            System.out.println("4. Display Records");
            System.out.println("5. Generate Reports");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        registerPet();
                        continue;
                    case 2:
                        scheduleAppointment();
                        continue;
                    case 3:
                        storeData();
                        continue;
                    case 4:
                        displayRecords();
                        continue;
                    case 5:
                        generateReports();
                        continue;
                    case 6:
                        running = false;
                        System.out.println("Exiting... Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }

    private static void loadDataFromFile() {
    }

    private static void registerPet() {
    }

    private static void scheduleAppointment() {
    }

    private static void storeData() {
    }

    private static void displayRecords() {
    }

    private static void generateReports() {
    }
}