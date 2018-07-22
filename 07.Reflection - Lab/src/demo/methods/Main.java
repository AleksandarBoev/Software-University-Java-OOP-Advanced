package demo.methods;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        Method sayHelloMethod = SomeClass.class.getMethod("sayHello", null); //null the method has no parameters

        System.out.print("Enter a word: ");
        SomeClass someClass = new SomeClass(reader.readLine());
        sayHelloMethod.invoke(someClass);

        Method someMethodWith1Param = SomeClass.class.getMethod("someMethod", String.class);
        Method someMethodWith2Params = SomeClass.class.getMethod("someMethod", String.class, String.class); //overloaded version of the method

        someMethodWith1Param.invoke(someClass, "With one param");
        someMethodWith2Params.invoke(someClass, "With two", "params");

        Method sumAsciiLettersMethod = SomeClass.class.getMethod("sumAsciiLetters", null);
        int result = (int)sumAsciiLettersMethod.invoke(someClass); //use the method from this object.
        System.out.println(result);
    }
}
