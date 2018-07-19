package demo.annotations;

public class Test {
    public static void main(String[] args) {
        sayHello();
        @MyTypeAnnotation(randomMethod = "Hello")
        String word = "pizza";
    }

    @Deprecated
    @SuppressWarnings(value = "unchecked")
    @MyMethodAnnotation(basicInfo = "Does stuff...", price = 10000)
    private static void sayHello() {
        System.out.println("Hello");
    }

    @Override
    public String toString() {
        return "Test{}";
    }
}
