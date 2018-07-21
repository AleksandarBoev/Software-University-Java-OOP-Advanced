package p10_infernoInfinity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //•	Create;{weapon type};{weapon name}
        //•	Add;{weapon name};{socket index};{gem type}
        //•	Remove;{weapon name};{socket index}
        //•	Print;{weapon name}
        Map<String, Weapon> nameWeapon = new LinkedHashMap<>();

        String input;
        while (!"END".equals(input = reader.readLine())) {
            String[] tokens = input.split(";");
            String command = tokens[0];

            switch (command) {
                case "Create":
                    String weaponType = tokens[1];
                    String weaponName = tokens[2];
                    Weapon weapon = Weapon.valueOf(Weapon.class, weaponType.toUpperCase());
                    weapon.setName(weaponName);

                    nameWeapon.put(weaponName, weapon);
                    break;

                case "Add":
                    weaponName = tokens[1];
                    int socketIndex = Integer.parseInt(tokens[2]);
                    String gemType = tokens[3];
                    Gem gem = Gem.valueOf(Gem.class, gemType.toUpperCase());

                    nameWeapon.get(weaponName).addGem(socketIndex, gem);
                    break;

                case "Remove":
                    weaponName = tokens[1];
                    socketIndex = Integer.parseInt(tokens[2]);
                    nameWeapon.get(weaponName).removeGem(socketIndex);
                    break;

                case "Print":
                    weaponName = tokens[1];
                    nameWeapon.get(weaponName).print();
                    break;

            }
        }

        reader.close();

//        for (Weapon weapon : nameWeapon.values()) {
//            weapon.print();
//        }
    }
}
