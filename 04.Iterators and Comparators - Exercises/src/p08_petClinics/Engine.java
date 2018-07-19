package p08_petClinics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Engine {
    private Map<String, Clinic> clinics;
    private Map<String, Pet> pets;

    public Engine() {
        this.clinics = new HashMap<>();
        this.pets = new HashMap<>();
    }

    //Create Pet {name} {age} {kind}
    //Create Clinic {name} {rooms}
    //Add {pet's name} {clinic's name}
    //Release {clinic's name}
    //HasEmptyRooms {clinic’s name}
    //Print {clinic's name}
    //Print {clinic's name} {room}

    public void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int numberOfInputs = Integer.parseInt(reader.readLine());
        for (int i = 0; i < numberOfInputs; i++) {
            String[] tokens = reader.readLine().split(" ");
            String command = tokens[0];

            switch (command) {
                case "Create":
                    if ("Pet".equals(tokens[1])) {
                        Pet currentPet = new Pet(tokens[2], Integer.parseInt(tokens[3]), tokens[4]);
                        this.pets.put(tokens[2], currentPet);
                    } else {
                        try {
                            Clinic currentClinic = new Clinic(tokens[2], Integer.parseInt(tokens[3]));
                            this.clinics.put(tokens[2], currentClinic);
                        } catch (IllegalArgumentException iae) {
                            System.out.println(iae.getMessage());
                        }
                    }
                    break;

                case "Add":
                    String petsName = tokens[1];
                    Pet currentPet = this.pets.get(petsName);

                    String clinicsName = tokens[2];
                    Clinic currentClinic = this.clinics.get(clinicsName);

                    System.out.println(currentClinic.addPet(currentPet));
                    break;

                case "Release":
                    clinicsName = tokens[1];
                    currentClinic = this.clinics.get(clinicsName);

                    System.out.println(currentClinic.releasePet());
                    break;

                case "HasEmptyRooms":
                    System.out.println(this.clinics.get(tokens[1]).hasEmptyRooms());
                    break;

                case "Print":
                    clinicsName = tokens[1];
                    if (tokens.length == 2) {
                        System.out.println(this.clinics.get(clinicsName));
                    } else {
                        System.out.println(this.clinics.get(clinicsName).getRooms().get(Integer.parseInt(tokens[2]) - 1)); //-1 because the user starts counting from 1 and not 0
                    }
                    break;
            }
        }
    }
}
