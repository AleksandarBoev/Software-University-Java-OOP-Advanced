package callofduty.domain.missions;

import callofduty.abstractClasses.BaseMission;

public class EscortMission extends BaseMission {
    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    //•	Decreases its given rating by 25%.
    //•	Increases its given bounty by 25%.

    @Override
    public Double getRating() {
//        return super.getRatingValue() - super.getRatingValue() * 0.25;
        return null;
    }

    @Override
    public Double getBounty() {
//        return super.getBountyValue() + super.getBountyValue() * 0.25;
        return null;
    }
}
