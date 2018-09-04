package callofduty.domain.missions;

import callofduty.abstractClasses.BaseMission;

public class HuntMission extends BaseMission {
    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    //•	Increases its given rating by 50%.
    //•	Increases its given bounty by 100%.

    @Override
    public Double getRating() {
//        return super.getRatingValue() + super.getRatingValue() * 0.5;
        return null;
    }

    @Override
    public Double getBounty() {
//        return super.getBountyValue() * 2.0;
        return null;
    }
}
