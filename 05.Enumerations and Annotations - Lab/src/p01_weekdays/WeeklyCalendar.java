package p01_weekdays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeeklyCalendar {
    private List<WeeklyEntry> weeklyCalendar;

    public WeeklyCalendar() {
        this.weeklyCalendar = new ArrayList<>();
    }

    public void addEntry(String weekday, String notes) {
        WeeklyEntry weeklyEntry = new WeeklyEntry(weekday, notes);
        this.weeklyCalendar.add(weeklyEntry);
    }

    public Iterable<WeeklyEntry> getWeeklySchedule() {
        this.weeklyCalendar.sort(new EntriesComparator());
        return this.weeklyCalendar; //return the list in the form of "Iterable", which means elements can't be changed, just iterated (I think)
    }

    private static final class EntriesComparator implements Comparator<WeeklyEntry> {

        @Override
        public int compare(WeeklyEntry entry1, WeeklyEntry entry2) {
            return Integer.compare(entry1.getWeekday().ordinal(), entry2.getWeekday().ordinal());
        }
    }

}
