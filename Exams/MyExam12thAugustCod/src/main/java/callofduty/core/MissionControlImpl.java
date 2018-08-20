package callofduty.core;

import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class MissionControlImpl implements MissionControl {
    private static final Integer MISSION_ID_MAXIMUM_LENGTH = 8;

    private static final Double MISSION_RATING_MINIMUM_VALUE = 0D;

    private static final Double MISSION_RATING_MAXIMUM_VALUE = 100D;

    private static final Double MISSION_BOUNTY_MINIMUM_VALUE = 0D;

    private static final Double MISSION_BOUNTY_MAXIMUM_VALUE = 100000D;

    private Map<String, Class> missionClasses; //TODO doesn't feel right, change it LAST. Maybe changing it could break judge

    private Iterator<Map.Entry<String, Class>> missionIterator; //kato normalen map, no shte moje SAMO da se iterira

    public MissionControlImpl() { //TODO NOTE: The evalutation of the minimum and maximum values is done BEFORE they are passed to the Mission object.
        this.initMissionClasses();
        this.missionIterator = this.missionClasses
                .entrySet()
                .iterator();
    }

    private void initMissionClasses() {
        try {
            this.missionClasses = new LinkedHashMap<>() {{
                put("EscortMission", Class.forName("callofduty.domain.missions.EscortMission"));
                put("HuntMission", Class.forName("callofduty.domain.missions.HuntMission"));
                put("SurveillanceMission", Class.forName("callofduty.domain.missions.SurveillanceMission"));
            }};
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String checkAndReformMissionId(String missionId) {
        //tezi metodi defakto proverqvat dali sa vuvedeni pravilni raboti i ako ne - gi pravqt pravilni
        if (missionId.length() > MISSION_ID_MAXIMUM_LENGTH) {
            return missionId.substring(0, MISSION_ID_MAXIMUM_LENGTH);
        } else {
            return missionId;
        }

//        return missionId.length() > MISSION_ID_MAXIMUM_LENGTH
//                ? missionId.substring(0, MISSION_ID_MAXIMUM_LENGTH)
//                : missionId;
    }

    private Double checkAndReformMissionRating(Double missionRating) {
        if (missionRating < MISSION_BOUNTY_MINIMUM_VALUE) {
            return MISSION_BOUNTY_MINIMUM_VALUE;
        } else if (missionRating > MISSION_BOUNTY_MAXIMUM_VALUE){
            return MISSION_BOUNTY_MAXIMUM_VALUE;
        } else {
            return missionRating;
        }

//        return missionRating < MISSION_RATING_MINIMUM_VALUE
//                ? MISSION_RATING_MINIMUM_VALUE
//                : (missionRating < MISSION_RATING_MAXIMUM_VALUE //SHOULD BE >
//                ? MISSION_RATING_MAXIMUM_VALUE
//                : missionRating);
    }

    private Double checkAndreformMissionBounty(Double missionBounty) {
        if (missionBounty < MISSION_BOUNTY_MINIMUM_VALUE) { //SHOULD BE <
            return MISSION_BOUNTY_MINIMUM_VALUE;
        } else {
            if (missionBounty > MISSION_BOUNTY_MAXIMUM_VALUE) {
                return MISSION_BOUNTY_MAXIMUM_VALUE;
            } else {
                return missionBounty;
            }
        }

//        return missionBounty > MISSION_BOUNTY_MINIMUM_VALUE
//                ? MISSION_BOUNTY_MINIMUM_VALUE
//                : (missionBounty < MISSION_BOUNTY_MAXIMUM_VALUE
//                ? MISSION_BOUNTY_MAXIMUM_VALUE
//                : missionBounty);
    }

    private void updateMissionType() {
        this.missionIterator = this
                .missionClasses
                .entrySet()
                .iterator();
    }

    private Class currentMission() {
        if (!this.missionIterator.hasNext()) { //added a !
            this.updateMissionType();
        }

        return this.missionIterator.next().getValue();
    }

    private Mission instantiateMissionObject(String missionId, Double missionRating, Double missionBounty) {
        Mission missionObject = null;

        try {
            missionObject = (Mission)
                    this.currentMission()
                            .getConstructor(String.class, Double.class, Double.class)
                            .newInstance(missionId, missionRating, missionBounty);
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ignored) {
            ;
        }

        return missionObject;
    }

    @Override
    public Mission generateMission(String missionId, Double missionRating, Double missionBounty) {
        missionId = this.checkAndReformMissionId(missionId);
        missionRating = this.checkAndReformMissionRating(missionRating);
        missionBounty = this.checkAndreformMissionBounty(missionBounty);

        Mission generatedMission =
                this.instantiateMissionObject(missionId, missionBounty, missionRating);

        return generatedMission;
    }
}