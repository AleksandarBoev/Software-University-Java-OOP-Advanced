package callofduty.domain.missions;

import callofduty.abstractClasses.BaseMission;

public class SurveillanceMission extends BaseMission {
    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    //•	Decreases its given rating by 75%.
    //•	Increases its given bounty by 50%.

    @Override
    public Double getRating() {
        return super.getRatingValue() - super.getRatingValue() * 0.75;
    }

    @Override
    public Double getBounty() {
        return super.getBountyValue() + super.getBountyValue() * 0.5;
    }
}
