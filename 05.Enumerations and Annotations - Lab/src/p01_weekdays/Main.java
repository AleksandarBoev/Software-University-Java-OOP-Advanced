package p01_weekdays;

public class Main {
    public static void main(String[] args) {
        WeeklyCalendar wc = new WeeklyCalendar();
        wc.addEntry("Monday", "sport");
        wc.addEntry("Friday", "party");
        wc.addEntry("Saturday", "hangover");
        wc.addEntry("Sunday", "sport");
        Iterable<WeeklyEntry> schedule = wc.getWeeklySchedule();

        for (WeeklyEntry weeklyEntry : schedule) {
            System.out.println(weeklyEntry);
        }
    }
}
