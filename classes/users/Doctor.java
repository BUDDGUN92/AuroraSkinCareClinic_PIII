package classes.users;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Doctor extends Person {
    private Map<DayOfWeek, TimeRange> availability = new HashMap<>();

    public Doctor(int userId, String name, String email, String phone) {
        super(userId, name, email, phone);
        initializeSchedule();
    }

    private void initializeSchedule() {
        availability.put(DayOfWeek.MONDAY, new TimeRange(LocalTime.of(10, 0), LocalTime.of(13, 0)));
        availability.put(DayOfWeek.WEDNESDAY, new TimeRange(LocalTime.of(14, 0), LocalTime.of(17, 0)));
        availability.put(DayOfWeek.FRIDAY, new TimeRange(LocalTime.of(16, 0), LocalTime.of(20, 0)));
        availability.put(DayOfWeek.SATURDAY, new TimeRange(LocalTime.of(9, 0), LocalTime.of(13, 0)));
    }

    public List<LocalTime> getAvailableSlots(DayOfWeek day, List<LocalTime> bookedSlots) {
        List<LocalTime> availableSlots = new ArrayList<>();
        TimeRange range = availability.get(day);
        if (range == null) return availableSlots;

        LocalTime slot = range.start;
        while (slot.isBefore(range.end)) {
            if (!bookedSlots.contains(slot)) {
                availableSlots.add(slot);
            }
            slot = slot.plusMinutes(15);
        }
        return availableSlots;
    }

    private static class TimeRange {
        LocalTime start, end;
        TimeRange(LocalTime start, LocalTime end) {
            this.start = start;
            this.end = end;
        }
    }
}
