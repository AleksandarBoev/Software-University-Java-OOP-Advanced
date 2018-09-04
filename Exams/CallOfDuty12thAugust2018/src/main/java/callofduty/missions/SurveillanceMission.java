package callofduty.missions;

import callofduty.abstract_classes.BaseMission;

public class SurveillanceMission extends BaseMission {
    public SurveillanceMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    //•	Decreases its given rating by 75%.
    //•	Increases its given bounty by 50%.

    @Override
    protected void setBounty(Double bounty) {
        bounty += (bounty * 50) / 100;
        super.setBounty(bounty);
    }

    @Override
    protected void setRating(Double rating) {
        rating -= (rating * 75) / 100;
        super.setRating(rating);
    }

}
