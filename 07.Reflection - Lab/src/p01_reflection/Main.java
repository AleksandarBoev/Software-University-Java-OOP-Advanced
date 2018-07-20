package p01_reflection;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //•	This class type
        //•	Super class type
        //•	All interfaces that are implemented by this class
        //•	Instantiate object using reflection and print it too
        Class<Reflection> aClass = Reflection.class;
        System.out.println(aClass);
        System.out.println(aClass.getSuperclass());
        Class[] interfaces = aClass.getInterfaces();
        for (Class anInterface : interfaces)
            System.out.println(anInterface);
//Reflection ref = aClass.newInstance();//Deprecated since Java 9
        Reflection ref = aClass.getDeclaredConstructor().newInstance(); //wouldn't work without adding <Reflection> in the beginning
        System.out.println(ref);


    }
}
