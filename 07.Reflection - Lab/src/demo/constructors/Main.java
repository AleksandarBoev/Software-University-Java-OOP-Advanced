package demo.constructors;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
        Constructor[] constructors = Person.class.getConstructors();

        int counter = 1;
        for (Constructor constructor : constructors) {
            Class[] parameters = constructor.getParameterTypes();
            String[] parametersSimpleNames = Arrays.stream(parameters).map(p -> p.getSimpleName()).toArray(n -> new String[n]);
            System.out.printf("Constructor %d works with these parameters: %s%n", counter++, Arrays.asList(parametersSimpleNames));
        }

        System.out.println("With which parameters do you want to make a new instance of the class? [Example: String, int / String / int]");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] tokens = reader.readLine().split(", ");
        Constructor<Person> personConstructor = null;
        Person person = null;
        if (tokens.length == 0) {
            personConstructor = Person.class.getConstructor();
            person = personConstructor.newInstance();
        } else if (tokens.length == 1) {
            personConstructor = Person.class.getConstructor(returnClass(tokens[0]));
            instructionsToConstructor(personConstructor);
            String parameter = reader.readLine();
            try {
                person = personConstructor.newInstance(parameter);
            } catch (Exception e) {
                person = personConstructor.newInstance(Integer.parseInt(parameter));
            }
        } else if (tokens.length == 2) {
            personConstructor = Person.class.getConstructor(returnClass(tokens[0]), returnClass(tokens[1]));
            instructionsToConstructor(personConstructor);
            String name = reader.readLine();
            int age = Integer.parseInt(reader.readLine());
            person = personConstructor.newInstance(name, age);
        }

        System.out.println(person);

        Field field = Person.class.getDeclaredField("name");
        System.out.println(field.getType().getSimpleName());
    }

    private static Class returnClass(String className) {
        Class result = null;
        switch (className) {
            case "String":
                result = String.class;
                break;

            case "int":
                result = int.class;
                break;
        }

        return result;
    }

    private static void instructionsToConstructor(Constructor constructor) {
        Class[] parameters = constructor.getParameterTypes();
        System.out.printf("Please enter these parameters(each on a new line): %s%n",
                Arrays.asList(Arrays.stream(parameters).map(p -> p.getSimpleName()).toArray(n -> new String[n])));
    }

}
