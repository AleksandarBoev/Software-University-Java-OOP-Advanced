package callofduty.domain.agents;

import callofduty.abstractClasses.BaseAgent;
import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

import java.util.List;

public class MasterAgent extends BaseAgent implements BountyAgent {
    private Double bounty;//o	The bounty is always 0 upon initialization.

    public MasterAgent(String id, String name, List<Mission> missions) {
        super(id, name);
        this.bounty = 0.0;
        super.setCompletedMissions(missions);
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public void completeMissions() { //earns rating AND bounty
        Double totalBounty = 0.0;
        Double totalRating = 0.0;

        for (Mission mission : super.getAcceptedMissions()) { //add all missions to completed ones and sum all their values
            super.getCompletedMissions().add(mission);
            totalBounty += mission.getBounty();
            totalRating += mission.getRating();
        }

        super.changeMissionsStatusesToCompleted(super.getAcceptedMissions());
        super.getAcceptedMissions().clear();

        this.bounty += totalBounty;
        super.increaseRating(totalRating);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(System.lineSeparator());
        sb.append(String.format("Bounty Earned: $%.2f", this.bounty));

        return sb.toString();
    }

    protected void increaseBounty(Double amount) {
        this.bounty += amount;
    }
}
