import java.io.*;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PetCareScheduler {
    private static Scanner scanner =  new Scanner(System.in);
    private static Map<String, Pet> Pets = new HashMap<>();

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
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("petsData.ser"))) {
            Pets = (Map<String, Pet>) in.readObject();
            System.out.println("Pets have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("No saved data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    private static void registerPet() {
        System.out.print("Enter Pet ID: ");
        String petId = scanner.nextLine().trim();

        if (Pets.containsKey(petId)) {
            System.out.println("Pet ID already exists. Please try again.");
            return;
        }

        String name;
        while (true) {
            System.out.print("Enter Pet Name: ");
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("Name cannot be empty.");
                continue;
            }
            break;
        }

        String breed;
        while (true) {
            System.out.print("Enter Species/Breed: ");
            breed = scanner.nextLine().trim();
            if (breed.isEmpty()) {
                System.out.println("Species cannot be empty.");
                continue;
            }
            break;
        }

        Float age = null;
        while (age == null) {
            System.out.print("Enter Pet Age: ");
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Age cannot be empty.");
                    continue;
                }
                age = Float.parseFloat(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid age. Please enter a number.");
            }
        }

        String ownerName;
        while (true) {
            System.out.print("Enter Owner Name: ");
            ownerName = scanner.nextLine().trim();
            if (ownerName.isEmpty()) {
                System.out.println("Owner Name cannot be empty.");
                continue;
            }
            break;
        }

        String contactInfo;
        while (true) {
            System.out.print("Enter Contact Info: ");
            contactInfo = scanner.nextLine().trim();
            if (contactInfo.isEmpty()) {
                System.out.println("Contact Info cannot be empty.");
                continue;
            }
            break;
        }

        LocalDateTime registrationDate = LocalDateTime.now();
        ArrayList<Appointment> appointments = new ArrayList<>();

        Pet pet = new Pet();
        pet.setPetId(petId);
        pet.setName(name);
        pet.setBreed(breed);
        pet.setOwnerName(ownerName);
        pet.setContactInfo(contactInfo);
        pet.setAppointments(appointments);

        Pets.put(petId, pet);
        System.out.println("Pet has been registered successfully.");
    }

    private static void scheduleAppointment() {
    }

    private static void storeData() {
        try (ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("petsData.ser"))) {
            o.writeObject(Pets);
            System.out.println("Data stored successfully to petsData.ser");
        } catch (Exception e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    private static void displayRecords() {
    }

    private static void generateReports() {
    }
}