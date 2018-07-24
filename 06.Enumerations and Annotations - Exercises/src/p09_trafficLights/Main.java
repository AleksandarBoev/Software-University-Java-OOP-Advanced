package p09_trafficLights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        //GREEN RED YELLOW
        //4
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] tokens = reader.readLine().split(" ");
        int numberOfChanges = Integer.parseInt(reader.readLine());
        reader.close();
        TrafficLightColors[] trafficLightColors = new TrafficLightColors[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            trafficLightColors[i] = TrafficLightColors.valueOf(TrafficLightColors.class, tokens[i].toUpperCase());
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numberOfChanges; i++) {
            for (int j = 0; j < trafficLightColors.length; j++) {
                trafficLightColors[j] = changeColor(trafficLightColors[j]);
                result.append(trafficLightColors[j].name()).append(" ");
            }
            result.append(System.lineSeparator());
        }

        System.out.println(result);
    }

    private static TrafficLightColors changeColor(TrafficLightColors trafficLightColor) {
        int ordinalNum = trafficLightColor.ordinal();
        if (ordinalNum == TrafficLightColors.values().length - 1) {
            return TrafficLightColors.values()[0];
        } else {
            return TrafficLightColors.values()[++ordinalNum];
        }
    }

}
