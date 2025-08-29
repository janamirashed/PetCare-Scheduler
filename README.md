# 🐾 PetCare Scheduler (Java CLI Application)

A simple **command-line Pet Care Scheduler** written in Java.  
This program allows pet owners to register pets, schedule and manage appointments, generate reports, and save/load data from a file.

---

## ✨ Features

- ➕ **Register Pets**  
  Record a pet with:  
  - Unique ID  
  - Name  
  - Age  
  - Breed  
  - Owner’s name and contact  

- 📅 **Schedule Appointments**  
  Track pet appointments with:  
  - Appointment type (vet visit, vaccination, grooming, etc.)  
  - Date and time (validated to be in the future)  
  - Optional notes  

- 📋 **View Pets**  
  List all registered pets along with their details.  

- 🗂️ **View Pet Appointments**  
  - Display all appointments for a specific pet  
  - Show both upcoming and past appointments  
  - Automatically sorted by date  

- 📊 **Generate Reports**  
  - List pets with appointments in the next 7 days  
  - Identify pets overdue for vet visits (no visits in last 6 months)  

- 💾 **Save and Load Data**  
  - Persist pets and their appointments using Java serialization (`.ser` file)  
  - Automatically load saved data when program starts  

---

## 🛠️ Tech Stack

- **Language:** Java (JDK 8+ recommended)  
- **Libraries:**  
  - `java.util` (HashMap, ArrayList, Scanner, Comparator)  
  - `java.time` (LocalDateTime, DateTimeFormatter)  
  - `java.io` (FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream, Serializable)  

---

## 📂 Project Structure

```text
petcare-scheduler/
├── Pet.java                # Represents a pet and its appointments
├── Appointment.java        # Represents a pet appointment (type, date, notes)
├── PetCareScheduler.java   # Main driver class with CLI menu and logic
└── README.md               # Project documentation
```
---

## 🔮 Future Improvements

- Add GUI interface for better usability  
- Export reports to CSV or JSON  
- Send notifications/reminders for upcoming appointments  
- Allow editing or deleting pets/appointments  
- Support multiple owners per pet  
- Implement unit tests for better code reliability  
- Enhance reports with charts or visual summaries  
