package callofduty.agents;

import callofduty.abstract_classes.BaseAgent;
import callofduty.interfaces.BountyAgent;
import callofduty.interfaces.Mission;

import java.util.List;

public class MasterAgent extends BaseAgent implements BountyAgent {
    private Double bounty;

    public MasterAgent(String id, String name, Double rating) {
        super(id, name);
        super.setRating(rating);
        this.bounty = 0.0;
    }

    @Override
    public Double getBounty() {
        return this.bounty;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.append(System.lineSeparator());
        sb.append(String.format("Bounty Earned: $%.2f", this.bounty));
        return sb.toString();
    }

    @Override
    protected void receiveReward() {
        super.receiveReward();
        this.bounty += super.getAcceptedMissions().stream()
                .mapToDouble(b -> b.getBounty())
                .sum();
    }
}
