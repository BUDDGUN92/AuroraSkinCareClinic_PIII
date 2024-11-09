import java.util.*;

import classes.*;
import classes.users.Doctor;
import classes.users.FrontDeskOperator;
import classes.users.Patient;

public class AuroraSkinCare {
    List<Doctor> doctorsList;
    List<Patient> patientsList;
    List<Appointment> appointmentsList;
    List<Treatment> treatmentsList;
    private static List<FrontDeskOperator> operators;

    private static FrontDeskOperator loggedInUser;
    private static Scanner scanner = new Scanner(System.in);

    public AuroraSkinCare() {
        operators = new ArrayList<FrontDeskOperator>();
        patientsList = new ArrayList<Patient>();
        doctorsList = new ArrayList<Doctor>();
        appointmentsList = new ArrayList<Appointment>();
        treatmentsList = new ArrayList<Treatment>();
    }

    public void addFrontDeskUser() {
        System.out.println("Adding Front Desk User");
        System.out.println();
        int id = operators.size() + 1;

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();

        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        FrontDeskOperator user = new FrontDeskOperator(id, name, email, phone, password);
        operators.add(user);
        System.out.println("*** Front Desk User Added ***");

        // scanner.close();
    }

    public void addPatient() {
        System.out.println("Adding Patient");
        System.out.println();
        int id = patientsList.size() + 1;

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Patient Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Patient Phone: ");
        String phone = scanner.nextLine();

        System.out.println("Enter Patient NIC: ");
        String nic = scanner.nextLine();

        Patient patient = new Patient(id, name, email, phone, nic);
        patientsList.add(patient);
        System.out.println("*** Patient Added ***");

        // scanner.close();
    }

    public void addDoctor() {
        System.out.println("Adding Doctor");
        System.out.println();
        int id = doctorsList.size() + 1;

        System.out.print("Enter Doctor Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Doctor Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Doctor Phone: ");
        String phone = scanner.nextLine();

        Doctor doctor = new Doctor(id, name, email, phone);
        doctorsList.add(doctor);
        System.out.println("*** Doctor Added ***");

        // scanner.close();
    }

    public void addTreatment() {
        System.out.println("Adding Treatment");
        System.out.println();
        int id = treatmentsList.size() + 1;

        System.out.print("Enter Treatment Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Treatment Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline character

        Treatment treatment = new Treatment(id, name, price);
        treatmentsList.add(treatment);
        System.out.println("*** Treatment Added ***");

        // scanner.close();
    }

    public void addAppointment() {
        System.out.println("Adding Appointment");
        System.out.println();

        Doctor doctor = null;
        Patient patient = null;
        Treatment treatment = null;
        int id = appointmentsList.size() + 1;

        showDoctors();

        System.out.print("Enter Doctor ID: ");
        boolean validDoctorId = false;
        int doctorId;
        while (!validDoctorId) {
            doctorId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (Doctor doc : doctorsList) {
                if (doctorId == doc.getId()) {
                    validDoctorId = true;
                    doctor = doc;
                    break;
                } else {
                    System.out.println("Invalid Doctor ID. Please try again.");
                }
            }
        }

        System.out.print("Enter Appointment Date: ");
        String date = scanner.nextLine();

        AppointmentScheduler scheduler = new AppointmentScheduler();
        
        if(scheduler.isSlotAvailable(doctor, date)){
            scheduler.displayAvailableSlots(doctor, date);
        } else {
            System.out.println("No available slots for the selected date.");
            return;
        }

        System.out.println("Enter Appointment Time (HH:mm) from available slots:");
        String time = scanner.nextLine();

        if (scheduler.bookAppointment(doctor, date, time)) {
            System.out.println("*** Appointment Successfully Booked ***");
        } else {
            System.out.println("Failed to book appointment. Please check available slots.");
        }

        showPatients();

        System.out.print("Enter Patient ID: ");
        boolean validPatientId = false;
        int patientId;
        while (!validPatientId) {
            patientId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (Patient pat : patientsList) {
                if (patientId == pat.getId()) {
                    validPatientId = true;
                    patient = pat;
                    break;
                } else {
                    System.out.println("Invalid Patient ID. Please try again.");
                }
            }
        }

        showTreatments();

        System.out.print("Enter Treatment ID: ");
        boolean validTreatmentId = false;
        int treatmentId;
        while (!validTreatmentId) {
            treatmentId = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            for (Treatment tr : treatmentsList) {
                if (treatmentId == tr.getTreatmentId()) {
                    validTreatmentId = true;
                    treatment = tr;
                    break;
                } else {
                    System.out.println("Invalid Treatment ID. Please try again.");
                }
            }
        }

        try {
            Appointment appointment = new Appointment(id, date, time, doctor, patient, treatment);
            appointmentsList.add(appointment);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return;
        }

        System.out.println("*** Appointment Added ***");
    }

    public void showFrontDeskUsers() {
        System.out.println("*** List of Front Desk Users ***");
        for (FrontDeskOperator user : operators) {
            user.printPerson();
            System.out.println("******************");
        }
        System.out.println("*** End of Front Desk Users ***");
    }

    public void showPatients() {
        System.out.println("*** List of Patients ***");
        for (Patient patient : patientsList) {
            patient.printPatient();
            System.out.println("******************");
        }
        System.out.println("*** End of Patients ***");
    }

    public void showDoctors() {
        System.out.println("*** List of Doctors ***");
        for (Doctor doctor : doctorsList) {
            doctor.printPerson();
            System.out.println("******************");
        }
        System.out.println("*** End of Doctors ***");
    }

    public void showTreatments() {
        System.out.println("*** List of Treatments ***");
        for (Treatment treatment : treatmentsList) {
            treatment.showTreatmentDetails();
            System.out.println("******************");
        }
        System.out.println("*** End of Treatments ***");
    }

    public void showAppointments() {
        System.out.println("*** List of Appointments ***");
        for (Appointment appointment : appointmentsList) {
            appointment.showAppointmentDetails();
            System.out.println("******************");
        }
        System.out.println("*** End of Appointments ***");
        System.out.println();
    }

    public void searchAppointmentByPatientInfo() {
        System.out.println("Search Appointment by Patient Name or NIC");
        System.out.print("Enter Patient Name or NIC: ");
        String searchTerm = scanner.nextLine().toLowerCase();
    
        boolean found = false;
    
        for (Appointment appointment : appointmentsList) {
            Patient patient = appointment.getPatient();
            if (patient.getName().toLowerCase().contains(searchTerm) || patient.getNic().equalsIgnoreCase(searchTerm)) {
                System.out.println("Appointment found:");
                appointment.showAppointmentDetails();
                found = true;
                System.out.println("******************");
            }
        }
    
        if (!found) {
            System.out.println("No appointment found for the given search term.");
        }
    }

    
    public void loginFrontDeskUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        for (FrontDeskOperator user : operators) {
            if (user.login(email, password)) {
                System.out.println("** Welcome, " + user.getName() + "! **");
                loggedInUser = user;
                clearConsole();
                displayMenu();
                return;
            }
        }

        System.out.println("Login failed. User not found or incorrect credentials.");
    }

    public void logoutFrontDeskUser(FrontDeskOperator user) {
        if (user == loggedInUser) {
            user.logout();
            clearConsole();
            System.out.println("Logout successful for " + user.getName());
            return;
        }
        System.out.println("No user is currently logged in.");
    }

    public void displayLoginMenu() {
        int choice;
        do {
            System.out.println("1. Login");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    loginFrontDeskUser();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            // scanner.close();

        } while (choice != 0);
    }

    public void displayMenu() {
        int choice;
        do {
            System.out.println("** Welcome to Aurora Skin Care **");
            System.out.println("Menu:");
            System.out.println("1. Show Front Desk Users");
            System.out.println("2. Add Front Desk User");
            System.out.println("3. Show Patients");
            System.out.println("4. Add Patient");
            System.out.println("5. Show Doctors");
            System.out.println("6. Add Doctor");
            System.out.println("7. Add Treatment");
            System.out.println("8. Show Treatments");
            System.out.println("9. Add Appointment");
            System.out.println("10. Show Appointments");
            System.out.println("11. Search Appointments");
            System.out.println("0. Logout");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    clearConsole();
                    showFrontDeskUsers();
                    break;
                case 2:
                    clearConsole();
                    addFrontDeskUser();
                    break;
                case 3:
                    clearConsole();
                    showPatients();
                    break;
                case 4:
                    clearConsole();
                    addPatient();
                    break;
                case 5:
                    clearConsole();
                    showDoctors();
                    break;
                case 6:
                    clearConsole();
                    addDoctor();
                    break;
                case 7:
                    clearConsole();
                    addTreatment();
                    break;
                case 8:
                    clearConsole();
                    showTreatments();
                    break;
                case 9:
                    clearConsole();
                    addAppointment();
                    break;
                case 10:
                    clearConsole();
                    showAppointments();
                    break;
                case 11:
                    clearConsole();
                    searchAppointmentByPatientInfo();
                    break;
                case 0:
                    System.out.println("Logging out...");
                    logoutFrontDeskUser(loggedInUser);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            // scanner.close();

        } while (choice != 0);
    }

    public static void main(String[] args) {
        AuroraSkinCare app = new AuroraSkinCare();
        initializeFrontDeskUser();

        if (loggedInUser == null) {
            app.initializeSamples();
            app.displayLoginMenu();
        } else {
            app.displayMenu();
        }
    }

    private static void initializeFrontDeskUser() {
        FrontDeskOperator admin = new FrontDeskOperator(operators.size() + 1, "Admin", "admin@aurora.com", "1234567890", "admin");
        operators.add(admin);
    }

    private void initializeSamples() {
        Doctor doc1 = new Doctor(doctorsList.size() + 1, "John Doe", "jd@aurora.com", "0123456789");
        doctorsList.add(doc1);
        Doctor doc2 = new Doctor(doctorsList.size() + 1, "Jane Doe", "jane@aurora.com", "552543210");
        doctorsList.add(doc2);

        Patient pat1 = new Patient(patientsList.size() + 1, "Alice", "alice@aurora.com", "9876543210", "123456789V");
        patientsList.add(pat1);

        Treatment treat1 = new Treatment(treatmentsList.size() + 1, "Acne Treatment", 2750.00);
        treatmentsList.add(treat1);
        Treatment treat2 = new Treatment(treatmentsList.size() + 1, "Skin Whitening", 7650.00);
        treatmentsList.add(treat2);
        Treatment treat3 = new Treatment(treatmentsList.size() + 1, "Mole Removal", 3850.00);
        treatmentsList.add(treat3);
        Treatment treat4 = new Treatment(treatmentsList.size() + 1, "Laser Treatment", 12500.00);
        treatmentsList.add(treat4);
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
