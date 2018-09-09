package callofduty.abstractClasses;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent implements Agent { //TODO potencialno id-to i reting-ut da sa v edin abstracten klas?
    //The Agents are initialized with an id (String), a name (String) and a rating (Double).
    private String id;
    private String name;
    private Double rating; //VERIFICATIONS are made in the MissionControlImpl
    private List<Mission> acceptedMissions; //doesn't give less points in judge (for now)
    private List<Mission> completedMissions;
    //also have two collections of Assigned missions and Completed missions? Or just integer counters...

    protected BaseAgent(String id, String name) {
        this.id = id;
        this.name = name;
        this.rating = 0.0;
        this.acceptedMissions = new ArrayList<>();
        this.completedMissions = new ArrayList<>();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    @Override
    public void acceptMission(Mission mission) {
        this.acceptedMissions.add(mission);
    }

    @Override
    public void completeMissions() { //earns rating AND bounty
        Double totalRating = 0.0;

        for (Mission mission : this.acceptedMissions) { //add all missions to completed ones and sum all their values
            this.completedMissions.add(mission);
            totalRating += mission.getRating();
        }

        this.changeMissionsStatusesToCompleted(this.acceptedMissions);

        this.acceptedMissions.clear();

        this.rating += totalRating;
    }

    @Override
    public String toString() { //TODO master agent will have a bit more
        //{agentType} Agent – {name}
        //Personal Code: {id}
        //Assigned Missions: {assignedMissionsCount}
        //Completed Missions: {completedMissionsCount}
        //Rating: {rating}
        StringBuilder sb = new StringBuilder();
        String agentType = this.getClass().getSimpleName().replace("Agent", "");
        //TODO change if class names are wrong

        sb.append(String.format("%s Agent - %s", agentType, this.name)).append(System.lineSeparator());
        sb.append(String.format("Personal Code: %s", this.id)).append(System.lineSeparator());
        sb.append(String.format("Assigned Missions: %d", this.acceptedMissions.size())).append(System.lineSeparator());
        sb.append(String.format("Completed Missions: %d", this.completedMissions.size())).append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f", this.rating));

        return sb.toString();
    }

    protected List<Mission> getAcceptedMissions() {
        return this.acceptedMissions;
    }

    protected List<Mission> getCompletedMissions() {
        return this.completedMissions;
    }

    protected void increaseRating(Double amount) {
        this.rating += amount;
    }

    protected void changeMissionsStatusesToCompleted(List<Mission> missions) {
        try {
            Field completedField = BaseMission.class.getDeclaredField("completed");
            completedField.setAccessible(true);

            for (Mission mission : missions) {
                completedField.set(mission, true);
            }

            completedField.setAccessible(false);
        } catch (Exception e) {
            System.out.println("You didn't extract the completed field of the mission correctly!");
        }
    }

    private int getCompletedMissionsCount() {
        return this.completedMissions.size();
    }

    protected void setCompletedMissions(List<Mission> completedMissions) {
        this.completedMissions = completedMissions;
    }
}
