package p10_infernoInfinity2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
            }
        }

        reader.close();
    }
}
