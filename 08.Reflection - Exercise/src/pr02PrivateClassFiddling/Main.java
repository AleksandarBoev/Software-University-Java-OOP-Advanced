package pr02PrivateClassFiddling;

import pr02PrivateClassFiddling.com.BlackBoxInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, NoSuchFieldException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		//add_999999
		//subtract_19
		//divide_4
		//multiply_2
		//rightShift_1
		//leftShift_3
		//END

		Constructor<BlackBoxInt> blackBoxIntConstructor = BlackBoxInt.class.getDeclaredConstructor(int.class);
		blackBoxIntConstructor.setAccessible(true);
		BlackBoxInt blackBoxInt = blackBoxIntConstructor.newInstance(0);
		blackBoxIntConstructor.setAccessible(false);

		Method[] methods = BlackBoxInt.class.getDeclaredMethods();

		Field innerValueField = BlackBoxInt.class.getDeclaredField("innerValue");
		innerValueField.setAccessible(true);

		String input;
		while(!"END".equals(input = reader.readLine())) {
		    String[] tokens = input.split("_");

		    String methodName = tokens[0];
		    int value = Integer.parseInt(tokens[1]);

		    Method currentMethod = getMethod(methods, methodName);
		    currentMethod.setAccessible(true);
		    currentMethod.invoke(blackBoxInt, value);
		    currentMethod.setAccessible(false);

			System.out.println(innerValueField.get(blackBoxInt));
		}

		innerValueField.setAccessible(false);
		reader.close();
	}

	private static Method getMethod(Method[] methods, String methodName) {
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				return method;
			}
		}

		return null;
	}

}
