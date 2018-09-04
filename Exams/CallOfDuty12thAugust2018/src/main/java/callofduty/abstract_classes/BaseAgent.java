package callofduty.abstract_classes;

import callofduty.interfaces.Agent;
import callofduty.interfaces.Mission;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseAgent implements Agent {
    private String id;
    private String name;
    private Double rating;
    private Method completeMissionMethod;

    private List<Mission> acceptedMissions;
    private List<Mission> completedMissions;

    protected BaseAgent(String id, String name) {
        this.id = id;
        this.name = name;
        this.rating = 0.0; //rating is lost if MasterAgent is made!
        this.acceptedMissions = new ArrayList<>();
        this.completedMissions = new ArrayList<>();
        try {
            this.completeMissionMethod = BaseMission.class.getDeclaredMethod("completeMission", null);
            this.completeMissionMethod.setAccessible(true);
        } catch (Exception e) {

        }
    }

    protected void setRating(Double rating) {
        this.rating = rating;
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

    protected List<Mission> getAcceptedMissions() {
        return this.acceptedMissions;
    }

//    protected List<Mission> getCompletedMissions() {
//        return this.completedMissions;
//    }

    @Override
    public void acceptMission(Mission mission) {
        this.acceptedMissions.add(mission);
    }

    @Override
    public void completeMissions() {
        this.completedMissions.addAll(this.acceptedMissions);

        for (Mission acceptedMission : this.acceptedMissions) {
            try {
                this.completeMissionMethod.invoke(acceptedMission);
            } catch (IllegalAccessException | InvocationTargetException iaeite) {

            }
        }

        this.receiveReward();
        this.acceptedMissions.clear();
    }

    protected void receiveReward() {
        this.rating += this.acceptedMissions.stream()
                .mapToDouble(r -> r.getRating())
                .sum();
    }

    @Override //TODO toString
    public String toString() {
        //{agentType} Agent – {name}
        //Personal Code: {id}
        //Assigned Missions: {assignedMissionsCount}
        //Completed Missions: {completedMissionsCount}
        //Rating: {rating}
        StringBuilder sb = new StringBuilder(); //some problems...
        sb.append(this.getClass().getSimpleName().replace("Agent", " Agent")).append(" - ").append(this.name)
                .append(System.lineSeparator());
        sb.append("Personal Code: ").append(this.id).append(System.lineSeparator());
        sb.append("Assigned Missions: ").append(this.acceptedMissions.size()).append(System.lineSeparator());
        sb.append("Completed Missions: ").append(this.completedMissions.size()).append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f", this.rating));

        return sb.toString();
    }
}
