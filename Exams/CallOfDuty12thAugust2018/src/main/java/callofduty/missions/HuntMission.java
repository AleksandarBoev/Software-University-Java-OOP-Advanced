package callofduty.missions;

import callofduty.abstract_classes.BaseMission;

public class HuntMission extends BaseMission {
    public HuntMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    //•	Increases its given rating by 50%.
    //•	Increases its given bounty by 100%.

    @Override
    protected void setBounty(Double bounty) {
        bounty += (bounty * 100) / 100;
        super.setBounty(bounty);
    }

    @Override
    protected void setRating(Double rating) {
        rating += (rating * 50) / 100;
        super.setRating(rating);
    }

}
