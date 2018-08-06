package demo_interface_segregation;

import demo_interface_segregation.interfaces.Baby;
import demo_interface_segregation.interfaces.Move;
import demo_interface_segregation.interfaces.Talk;
import demo_interface_segregation.models.BabyImpl;

public class Main {
    public static void main(String[] args) {
        Move baby1 = new BabyImpl();
        Talk baby2 = new BabyImpl();
        Baby baby3 = new BabyImpl();

        baby1.move();//cant talk

        System.out.println(baby2.talk());

        baby3.move();
        System.out.println(baby3.talk());
    }
}
