package classes;

import classes.users.Patient;

import java.math.BigDecimal;
import java.math.RoundingMode;

import classes.users.Doctor;

public class Appointment {
    private int appointmentId;
    private String appointmentDate;
    private String appointmentTime;
    private Doctor doctor;
    private Patient patient;
    private Treatment treatment;
    private static final double registrationFee = 500.00;
    private static final double taxRate = 0.025; // 2.5% tax

    public Appointment(int id, String date, String time, Doctor doctor, Patient patient, Treatment treatment) {
        this.appointmentId = id;
        this.appointmentDate = date;
        this.appointmentTime = time;
        this.doctor = doctor;
        this.patient = patient;
        this.treatment = treatment;
    }

    
    public void setAppointmentDate(String date){
        this.appointmentDate = date;
    }

    public void setAppointmentTime(String time){
        this.appointmentTime = time;
    }

    public void setDoctor(Doctor doctor){
        this.doctor = doctor;
    }

    public void setPatient(Patient patient){
        this.patient = patient;
    }

    public void setTreatment(Treatment treatment){
        this.treatment = treatment;
    }

    public String getAppointmentDate(){
        return appointmentDate;
    }

    public String getAppointmentTime(){
        return appointmentTime;
    }

    public Doctor getDoctor(){
        return doctor;
    }

    public Patient getPatient(){
        return patient;
    }

    public Treatment getTreatment(){
        return treatment;
    }

    public void showAppointmentDetails(){
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Appointment Date: " + appointmentDate);
        System.out.println("Appointment Time: " + appointmentTime);
        System.out.println("Doctor: " + doctor.getName());
        System.out.println("Patient: " + patient.getName());
        System.out.println("Treatment: " + treatment.getTreatmentName());
        System.out.println();
        System.out.println("Invoice: ");
        System.out.println("\tRegistration Fee: LKR " + registrationFee);
        System.out.println("\tTreatment Fee: LKR " + treatment.getTreatmentPrice());
        System.out.println("\tTax: LKR " + (treatment.getTreatmentPrice() * taxRate));
        System.out.println("\tFinal Amount: LKR " + calculateFinalAmount(treatment));
    }

    public double calculateFinalAmount(Treatment treatment) {
        double baseAmount = treatment.getTreatmentPrice();
        double taxAmount = baseAmount * taxRate;
        double finalAmount = baseAmount + taxAmount + registrationFee;

        // Round up to the nearest decimal
        BigDecimal roundedAmount = new BigDecimal(finalAmount).setScale(1, RoundingMode.UP);
        return roundedAmount.doubleValue();
    }
}
