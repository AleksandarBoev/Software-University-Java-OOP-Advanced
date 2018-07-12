package p03_genericScale;

public class Main {
    public static void main(String[] args) {
        Scale<Integer> integerScale = new Scale<>(12, 24);
        System.out.println(integerScale.getHeavier());

        Scale<String> stringScale = new Scale<>("A is the first letter", "B is the second one");
        System.out.println(stringScale.getHeavier());
    }
}
