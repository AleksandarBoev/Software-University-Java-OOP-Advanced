package demo.methods_fields_annotation_interface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, ClassNotFoundException, IOException, NoSuchMethodException, InvocationTargetException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String classInfo = "demo.methods_fields_annotation_interface.Student"; //write or use reader.readLine(); Or right-click --> copy reference --> paste --> add class name
        Class myStudentClass = Class.forName(classInfo); // how to get the name: write myStudent = Student.class; and then myStudentClass.getName()
        Method printAllFieldsMethod = Main.class.getMethod("printAllFields", Class.class);
        String info = printAllFieldsMethod.getAnnotation(Info.class).description();
        System.out.println(info);
        printAllFields(myStudentClass);

        Field[] fields = myStudentClass.getDeclaredFields();

        System.out.println("Fields:");
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println(field.getName());
            field.setAccessible(false);
        }
        System.out.println();

        Method[] methods = myStudentClass.getDeclaredMethods(); //does not take constructors, but takes methods with @Override annotation
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
        System.out.println();

        Constructor[] constructors = myStudentClass.getConstructors();
        System.out.println("Constructors:");
        int counter = 1;
        for (Constructor constructor : constructors) {
            Class[] parameters = constructor.getParameterTypes();
            String[] parametersSimpleNames = Arrays.stream(parameters).map(p -> p.getSimpleName()).toArray(n -> new String[n]);
            System.out.printf("Constructor %d has these parameters: %s%n",
                    counter++,
                    Arrays.asList(parametersSimpleNames).toString().replaceAll("[\\[\\]]", ""));
        }
        System.out.println();

        Class superClass = myStudentClass.getSuperclass();
        Field[] superClassFields = superClass.getDeclaredFields();
        System.out.println("Super class fields:");
        for (Field field : superClassFields) {
            field.setAccessible(true);
            System.out.println(field.getName());
            field.setAccessible(false);
        }
        System.out.println();


    }

    @Info(description = "Prints fields of the class and every super class after that.")
    public static void printAllFields(Class yourClass) {
        while (true) {
            try {
                Field[] currentFields = yourClass.getDeclaredFields();
                System.out.printf("The fields of class %s are:%n", yourClass.getSimpleName());
                for (Field field : currentFields) {
                    field.setAccessible(true);
                    System.out.println(field.getName());
                    System.out.println(field.get(yourClass));
                    field.setAccessible(false);
                }

                yourClass = yourClass.getSuperclass();
                System.out.println();
            } catch (Exception e) {
                break;
            }
        }
    }

}
