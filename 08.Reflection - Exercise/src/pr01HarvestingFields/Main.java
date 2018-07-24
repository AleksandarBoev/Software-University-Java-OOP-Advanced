package pr01HarvestingFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		Field[] fields = RichSoilLand.class.getDeclaredFields();

		String input;
		while (!"HARVEST".equals(input = reader.readLine())) {
			if ("all".equals(input)) {
				for (Field field : fields) {
					System.out.println(fieldToString(field));
				}
			} else {
				int modifierInt = modifierStringToInt(input);
				Arrays.stream(fields)
						.filter(f -> f.getModifiers() == modifierInt)
						.forEach(f -> System.out.println(fieldToString(f)));
			}

		}
		reader.close();
	}

	private static String modifierIntToString(int modifierInt) {
		if (Modifier.isPrivate(modifierInt)) {
			return "private";
		} else if (Modifier.isPublic(modifierInt)) {
			return "public";
		} else  { //TODO could be more, but this will work for now
			return "protected";
		}
	}

	private static int modifierStringToInt(String modifierName) {
		switch (modifierName) {
			case "private":
				return Modifier.PRIVATE;

			case "public":
				return Modifier.PUBLIC;

			default: //only these three for now
				return Modifier.PROTECTED;
		}
	}

	private static String fieldToString(Field field) {
		return String.format("%s %s %s", modifierIntToString(field.getModifiers()), field.getType().getSimpleName(), field.getName());
	}
}
