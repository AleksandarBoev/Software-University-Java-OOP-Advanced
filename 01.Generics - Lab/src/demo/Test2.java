package demo;

public class Test2 {
    public static void main(String[] args) {
        Hello hi1 = new Son1();
        Hello hi2 = new Son2();

        printResult(hi1);
        printResult(hi2);
    }

    private static <T extends Hello> void printResult(T input) {
        System.out.println(input.multiply());
    }
}
