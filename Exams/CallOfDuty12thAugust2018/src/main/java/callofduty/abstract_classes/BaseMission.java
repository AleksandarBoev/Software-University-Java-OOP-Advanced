package callofduty.abstract_classes;

import callofduty.interfaces.Mission;

public abstract class BaseMission implements Mission {
    private String id;
    private Double rating;
    private Double bounty;
    private boolean completed;

    protected BaseMission(String id, Double rating, Double bounty) {
        this.id = id; //no need for a setter here, because the mission generator will give a valid id
        this.setRating(rating);
        this.setBounty(bounty);
        this.completed = false;
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public Double getRating() {
        return this.rating;
    }

    protected void setRating(Double rating) {
        this.rating = rating;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    protected void setBounty(Double bounty) {
        this.bounty = bounty;
    }

    @Override
    public String toString() {
        //{missionType} Mission – {id}
        //Status: {Open / Completed}
        //Rating: {rating}
        //Bounty: {bounty}
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName().replace("Mission", " Mission")).append(" - ").append(this.id)
                .append(System.lineSeparator());
        sb.append("Status: ").append(this.completed ? "Completed" : "Open").append(System.lineSeparator());
        sb.append(String.format("Rating: %.2f", this.rating)).append(System.lineSeparator());
        sb.append(String.format("Bounty: %.2f", this.bounty));

        return sb.toString();
    }

    private void completeMission() {
        this.completed = true;
    }
}
