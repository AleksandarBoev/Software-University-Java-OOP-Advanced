package demo.methods;

public class SomeClass {
    private String word;

    public SomeClass(String word) {
        this.word = word;
    }

    public void someMethod(String someWord) {
        System.out.println(this.word + "<--->" + someWord);
    }

    public void someMethod(String someWord, String anotherWord) {
        System.out.println(this.word + "<--->" + someWord + "<--->" + anotherWord);
    }

    public void sayHello() {
        System.out.println("Hello " + this.word);
    }

    public int sumAsciiLetters() {
        int sum = 0;
        for (int i = 0; i < this.word.length(); i++) {
            sum += this.word.charAt(i);
        }

        return sum;
    }
}
