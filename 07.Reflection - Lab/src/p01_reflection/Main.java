package p01_reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //•	This class type
        //•	Super class type
        //•	All interfaces that are implemented by this class
        //•	Instantiate object using reflection and print it too
        Class<Reflection> aClass = Reflection.class; //for this task to give 100/100, the files must be in the src folder and not in a subfolder
        System.out.println(aClass);
        System.out.println(aClass.getSuperclass());
        Class[] interfaces = aClass.getInterfaces();
        for (Class anInterface : interfaces)
            System.out.println(anInterface);
//p01_reflection.Reflection ref = aClass.newInstance();//Deprecated since Java 9
        Constructor<Reflection> reflectionConstructor = aClass.getDeclaredConstructor();
        Reflection reflection = reflectionConstructor.newInstance();
        System.out.println(reflection);

    }
}
