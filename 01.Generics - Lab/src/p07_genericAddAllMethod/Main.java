package p07_genericAddAllMethod;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<? super Number> numbers = new ArrayList<>();
        List<Integer> integerList = new ArrayList<>(){{
            add(3);
            add(4);
            add(5);
        }};

        List<Double> doubleList = new ArrayList<>() {{
            add(3.5);
            add(4.5);
            add(5.5);
        }};

        ListUtils.addAll(numbers, integerList);
        ListUtils.addAll(numbers, doubleList);

        System.out.println(numbers.size());

        List<? super Number> numbers1 = new ArrayList<>();
        numbers1.add(10);
    }
}
