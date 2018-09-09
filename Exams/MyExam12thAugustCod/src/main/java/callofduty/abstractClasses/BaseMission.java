package callofduty.abstractClasses;

import callofduty.interfaces.Mission;

public abstract class BaseMission implements Mission {
    private String id; //TODO
    private Double rating;
    private Double bounty;
    private boolean completed;

    protected BaseMission(String id, Double rating, Double bounty) {
        this.id = id;
        this.rating = rating;
        this.bounty = bounty;
        this.completed = false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    //{missionType} Mission – {id}
    //Status: {Open / Completed}
    //Rating: {rating}
    //Bounty: {bounty}
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String missionType = this.getClass().getSimpleName().replace("Mission", "");

        sb.append(String.format("%s Mission - %s", missionType, this.id)).append(System.lineSeparator()); //TODO proveri tiretata!

        String status;
        if (this.completed)
            status = "Completed";
        else
            status = "Open";

        sb.append(String.format("Status: %s", status)).append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f", this.rating)).append(System.lineSeparator());
        sb.append(String.format("Bounty: %.2f", this.bounty));

        return sb.toString();
    }

    protected double getRatingValue() {
        return this.rating;
    }

    protected double getBountyValue() {
        return this.bounty;
    }
}
