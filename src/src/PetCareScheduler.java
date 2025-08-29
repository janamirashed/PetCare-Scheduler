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
        pet.setAge(age.intValue());
        pet.setOwnerName(ownerName);
        pet.setContactInfo(contactInfo);
        pet.setAppointments(appointments);

        Pets.put(petId, pet);
        System.out.println("Pet has been registered successfully.");
    }

    private static void scheduleAppointment() {
        System.out.print("Enter Pet ID: ");
        String petID = scanner.nextLine().trim();

        if (!Pets.containsKey(petID)) {
            System.out.println("Error: No pet found with this ID.");
            return;
        }
        String type;
        while (true) {
            System.out.print("Enter Appointment Type (Vet Visit / Vaccination / Grooming): ");
            type = scanner.nextLine().trim();
            if (type.isEmpty()) {
                System.out.println("Appointment type cannot be empty.");
                continue;
            }
            if (!(type.equalsIgnoreCase("Vet Visit") ||
                    type.equalsIgnoreCase("Vaccination") ||
                    type.equalsIgnoreCase("Grooming"))) {
                System.out.println("Invalid type. Please enter from the following: Vet Visit / Vaccination / Grooming.");
                continue;
            }
            break;
        }

            LocalDateTime appDateTime = null;
            while (appDateTime == null) {
                try {
                    System.out.print("Enter Appointment Date & Time (dd-MM-yyyy HH:mm): ");
                    String input = scanner.nextLine().trim();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    appDateTime = LocalDateTime.parse(input, formatter);

                    if (appDateTime.isBefore(LocalDateTime.now())) {
                        System.out.println("Appointment date/time must be in the future.");
                        appDateTime = null;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid date/time format. Please use dd-MM-yyyy HH:mm");
                }
            }

            System.out.print("Enter Notes (optional): ");
            String notes = scanner.nextLine().trim();

            Appointment appointment = new Appointment(type, appDateTime, notes);
            Pets.get(petID).getAppointments().add(appointment);

            System.out.println("Appointment scheduled successfully for Pet ID " + petID);
        }
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
        if (Pets.isEmpty()) {
            System.out.println("No pets registered yet.");
            return;
        }

        System.out.println("\n=== Display Records Menu ===");
        System.out.println("1. All Registered Pets");
        System.out.println("2. All Appointments for a Specific Pet");
        System.out.println("3. Upcoming Appointments (All Pets)");
        System.out.println("4. Past Appointment History (Each Pet)");
        System.out.print("Choose an option (1-4): ");

        try {
            int  choice = Integer.parseInt(scanner.nextLine().trim());
            switch (choice) {
                case 1:
                    System.out.println("\n--- All Registered Pets ---");
                    for (Pet pet : Pets.values()) {
                        System.out.println(pet);
                    }
                    break;
                case 2:
                    System.out.println("Enter Pet ID: ");
                    String petId = scanner.nextLine().trim();
                    Pet pet =  Pets.get(petId);
                    if (pet == null) {
                        System.out.println("Pet with this ID does not exist.");
                    } else {
                        System.out.println("\nAppointments for Pet: " + pet.getName());
                        if(pet.getAppointments().isEmpty()) {
                            System.out.println("No past appointments for this Pet.");
                        } else {
                            for (Appointment appointment : pet.getAppointments()) {
                                System.out.println(appointment);
                            }
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n--- Upcoming Appointments (All Pets) ---");
                    LocalDateTime now = LocalDateTime.now();
                    for(Pet p : Pets.values()) {
                        for(Appointment appointment : p.getAppointments()) {
                            if(appointment.getDateTime().isAfter(now)) {
                                System.out.println("Pet: " + p.getName() + " (" + p.getPetId() + ") -> " + appointment);
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n--- Past Appointment History (Each Pet) ---");
                    LocalDateTime current = LocalDateTime.now();
                    for(Pet p : Pets.values()) {
                        for(Appointment appointment : p.getAppointments()) {
                            if(appointment.getDateTime().isBefore(current)) {
                                System.out.println("Pet: " + p.getName() + " (" + p.getPetId() + ") -> " + appointment);
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
              }
        } catch(NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 4.");
        }
    }

    private static void generateReports() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nextWeek = now.plusWeeks(1);
        LocalDateTime sixMonthsAgo = now.minusMonths(6);

        System.out.println("\n=== Report ===");

        System.out.println("\nPets with upcoming appointments in the next week:");
        boolean foundUpcoming = false;
        for (Pet pet : Pets.values) {
            for (Appointment appointment : pet.getAppointments()) {
                if (appointment.getDateTime().isAfter(now) && appointment.getDateTime().isBefore(nextWeek)) {
                    System.out.println("Pet: " + pet.getName() + " (" + pet.getPetId() + ") -> " + appointment);
                    foundUpcoming = true;
                }
            }
        }
        if (!foundUpcoming) {
            System.out.println("No pets with upcoming appointments in the next week.");
        }

        System.out.println("\nPets overdue for a vet visit (last 6 months):");
        boolean foundOverdue = false;
        for(Pet pet : Pets.values()) {
            LocalDateTime lastVetVisit = null;
            for (Appointment appointment : pet.getAppointments()) {
                if (appointment.getAppType().equalsIgnoreCase("vet visit")) {
                    if (lastVetVisit == null || appointment.getDateTime().isBefore(lastVetVisit)) {
                        lastVetVisit = appointment.getDateTime();
                    }
                }
            }
            if (lastVetVisit != null || lastVetVisit.isBefore(sixMonthsAgo)) {
                System.out.println("- " + pet.getName() + " (" + pet.getPetId() + ") is overdue for a vet visit.");
            }
        }
        if (!foundOverdue) {
            System.out.println("No pets are overdue for a vet visit.");
        }
    }
}