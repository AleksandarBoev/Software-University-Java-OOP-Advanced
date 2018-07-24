package p13_createCustomClassAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Engine {
    private Map<String, Weapon> nameWeapon;

    public Engine() {
        this.nameWeapon = new HashMap<>();
    }

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while(!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(";");
            String command = tokens[0];

            switch (command) {
                case "Create":
                    String weaponType = tokens[1];
                    String weaponName = tokens[2];

                    Weapon weapon = new Weapon(weaponName, weaponType);
                    this.nameWeapon.put(weaponName, weapon);
                    break;

                case "Add":
                    weaponName = tokens[1];
                    int socketIndex = Integer.parseInt(tokens[2]);
                    String gemType = tokens[3];

                    this.nameWeapon.get(weaponName).addGem(socketIndex, gemType);
                    break;

                case "Remove":
                    weaponName = tokens[1];
                    socketIndex = Integer.parseInt(tokens[2]);

                    this.nameWeapon.get(weaponName).removeGem(socketIndex);
                    break;

                case "Print":
                    weaponName = tokens[1];
                    System.out.println(this.nameWeapon.get(weaponName));
                    break;

                case "Compare":
                    weaponName = tokens[1];
                    String weapon2Name = tokens[2];

                    weapon = nameWeapon.get(weaponName);
                    Weapon weapon2 = nameWeapon.get(weapon2Name);

                    if (weapon.compareTo(weapon2) >= 0) {
                        System.out.println(weapon.toStringAfterComparison());
                    } else {
                        System.out.println(weapon2.toStringAfterComparison());
                    }
                    break;

                case "Author":
                    System.out.printf("Author: %s%n", Weapon.class.getAnnotation(CustomAnnotation.class).author());
                    break;

                case "Revision":
                    System.out.printf("Revision: %d%n", Weapon.class.getAnnotation(CustomAnnotation.class).revision());
                    break;

                case "Description":
                    System.out.printf("Class description: %s%n", Weapon.class.getAnnotation(CustomAnnotation.class).description());
                    break;

                case "Reviewers":
                    System.out.printf("Reviewers: %s%n", String.join(", ", Arrays.asList(Weapon.class.getAnnotation(CustomAnnotation.class).reviewers())));
                    break;
            }
        }

        reader.close();
    }
}
