package p04_listUtilities;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();

        System.out.println(ListUtils.getMax(integers));
        System.out.println(ListUtils.getMin(integers));
    }
}
