package classes;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

import classes.users.Doctor;

public class AppointmentScheduler {
    private Map<Doctor, List<LocalDateTime>> bookings = new HashMap<>();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public void displayAvailableSlots(Doctor doctor, String dateString) {
        LocalDate date = parseDate(dateString);
        if (date == null) return;
    
        List<LocalTime> bookedTimes = getBookedTimesForDoctor(doctor, date);
        List<LocalTime> availableSlots = doctor.getAvailableSlots(date.getDayOfWeek(), bookedTimes);
    
        System.out.println("Available slots on " + date + ":");
        for (int i = 0; i < availableSlots.size(); i++) {
            System.out.println((i + 1) + ". " + availableSlots.get(i));
        }
    }
    
    public boolean isSlotAvailable(Doctor doctor, String dateString){
        LocalDate date = parseDate(dateString);
        if (date == null) {return false;}
        List<LocalTime> bookedTimes = getBookedTimesForDoctor(doctor, date);
        List<LocalTime> availableSlots = doctor.getAvailableSlots(date.getDayOfWeek(), bookedTimes);
    
        if (availableSlots.isEmpty()) {
            return false;
        }
    
        return true;
    }

    public boolean bookAppointment(Doctor doctor, String dateString, String timeString) {
        LocalDate date = parseDate(dateString);
        LocalTime time = parseTime(timeString);
        if (date == null || time == null) return false;

        LocalDateTime appointmentDateTime = LocalDateTime.of(date, time);
        List<LocalDateTime> doctorBookings = bookings.getOrDefault(doctor, new ArrayList<>());

        if (doctorBookings.contains(appointmentDateTime)) {
            System.out.println("Slot is already booked. Please choose another time.");
            return false;
        }

        // Add to bookings and update availability for doctor
        doctorBookings.add(appointmentDateTime);
        bookings.put(doctor, doctorBookings);
        System.out.println("Appointment booked with Dr. " + doctor.getName() + " on " + appointmentDateTime);
        return true;
    }

    private LocalDate parseDate(String dateString) {
        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (Exception e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            return null;
        }
    }

    private LocalTime parseTime(String timeString) {
        try {
            return LocalTime.parse(timeString);
        } catch (Exception e) {
            System.out.println("Invalid time format. Please use HH:mm.");
            return null;
        }
    }

    private List<LocalTime> getBookedTimesForDoctor(Doctor doctor, LocalDate date) {
        List<LocalTime> bookedTimes = new ArrayList<>();
        List<LocalDateTime> doctorBookings = bookings.getOrDefault(doctor, new ArrayList<>());
        for (LocalDateTime booking : doctorBookings) {
            if (booking.toLocalDate().equals(date)) {
                bookedTimes.add(booking.toLocalTime());
            }
        }
        return bookedTimes;
    }
}
