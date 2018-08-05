package p00_systemRecources.implementations;

import p00_systemRecources.interfaces.Greeting;
import p00_systemRecources.interfaces.TimeProvider;
import p00_systemRecources.interfaces.Writer;

public class GreetingClock implements Greeting {
    private static final String MORNING_GREETING = "Good morning…";
    private static final String AFTERNOON_GREETING = "Good afternoon…";
    private static final String EVENING_GREETING = "Good evening…";

    private TimeProvider timeProvider;
    private Writer writer;

    public GreetingClock(TimeProvider timeProvider, Writer writer) {
        this.timeProvider = timeProvider;
        this.writer =  writer;
    }

    @Override
    public void greet() {
        int hour = this.timeProvider.getHour();

        if (hour < 12)
            this.writer.writeLine(MORNING_GREETING);
        else if (hour < 18)
            this.writer.writeLine(AFTERNOON_GREETING);
        else
            this.writer.writeLine(EVENING_GREETING);
    }
}
