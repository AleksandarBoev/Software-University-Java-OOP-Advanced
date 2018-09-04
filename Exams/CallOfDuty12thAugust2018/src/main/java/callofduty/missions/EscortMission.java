package callofduty.missions;

import callofduty.abstract_classes.BaseMission;

public class EscortMission extends BaseMission {
    public EscortMission(String id, Double rating, Double bounty) {
        super(id, rating, bounty);
    }

    //•	Decreases its given rating by 25%.
    //•	Increases its given bounty by 25%.

    //price + ((price * 30) / 100) - % formula //TODO formula might be wrong

    @Override
    protected void setBounty(Double bounty) {
        bounty += (bounty * 25) / 100;
        super.setBounty(bounty);
    }

    @Override
    protected void setRating(Double rating) {
        rating -= (rating * 25) / 100;
        super.setRating(rating);
    }
}
