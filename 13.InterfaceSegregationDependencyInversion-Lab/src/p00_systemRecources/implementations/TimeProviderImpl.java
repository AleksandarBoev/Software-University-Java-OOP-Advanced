package p00_systemRecources.implementations;

import p00_systemRecources.interfaces.TimeProvider;

import java.time.LocalTime;

public class TimeProviderImpl implements TimeProvider {
    private LocalTime localTime;

    public TimeProviderImpl() {
        this.localTime = LocalTime.now();
    }

    @Override
    public int getHour() {
        return this.localTime.getHour();
    }
}
