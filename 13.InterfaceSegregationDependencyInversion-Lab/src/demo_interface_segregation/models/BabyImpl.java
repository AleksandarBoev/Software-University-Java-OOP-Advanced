package demo_interface_segregation.models;

import demo_interface_segregation.interfaces.Baby;

public class BabyImpl implements Baby {
    @Override
    public void move() {

    }

    @Override
    public String talk() {
        return null;
    }
}
