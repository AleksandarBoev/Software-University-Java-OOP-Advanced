package callofduty.core;

import callofduty.interfaces.Mission;
import callofduty.interfaces.MissionControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MissionControlImplTest {
    private static final String MISMATCH_MESSAGE = "Expected %1$s and actual %1$s are different!";

    private static final Integer MISSION_ID_MAXIMUM_LENGTH = 8;
    private static final String MISSION_ID_OVER_MAXIMUM_LENGTH = "1234567890";
    private static final String MISSION_ID_EXPECTED_VALUE = "12345678";

    private static final Double MISSION_RATING_MINIMUM_VALUE = 0D;
    private static final Double MISSION_RATING_UNDER_MINIMUM_VALUE = -1D;

    private static final Double MISSION_RATING_MAXIMUM_VALUE = 100D;
    private static final Double MISSION_RATING_OVER_MAXIMUM_VALUE = 101D;

    private static final String MISSION_ID = "007";
    private static final Double MISSION_RATING = 50D;
    private static final Double MISSION_BOUNTY = 33D;

    private static final Double MISSION_BOUNTY_MINIMUM_VALUE = 0D;
    private static final Double MISSION_BOUNTY_UNDER_MINIMUM_VALUE = MISSION_BOUNTY_MINIMUM_VALUE - 1;

    private static final Double MISSION_BOUNTY_MAXIMUM_VALUE = 100000D;
    private static final Double MISSION_BOUNTY_OVER_MAXIMUM_VALUE = MISSION_BOUNTY_MAXIMUM_VALUE + 1;

    private static final String ESCORT_MISSION_CLASS_NAME = "EscortMission";
    private static final String HUNT_MISSION_CLASS_NAME = "HuntMission";
    private static final String SURVEILLANCE_MISSION_CLASS_NAME= "SurveillanceMission";


    private MissionControl missionControl;

    @Before
    public void init() {
        this.missionControl = new MissionControlImpl();
    }

    @Test
    public void checkAndReformMissionId_overMaximumLength() {
        try {
            Method method = this.missionControl.getClass().getDeclaredMethod("checkAndReformMissionId");
            method.setAccessible(true);

            String actualValue = (String)method.invoke(this.missionControl, MISSION_ID_OVER_MAXIMUM_LENGTH);
            method.setAccessible(false);

            Assert.assertEquals(String.format(MISMATCH_MESSAGE, "id"), MISSION_ID_EXPECTED_VALUE, actualValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }

    @Test
    public void checkAndReformMissionRating_overMaximumValue() {
        //using reflection for the private methods, because the classes change the ratings and bounties
        try {
            Method method = this.missionControl.getClass().getDeclaredMethod("checkAndReformMissionRating");
            method.setAccessible(true);

            Double actualValue = (Double)method.invoke(this.missionControl, MISSION_RATING_OVER_MAXIMUM_VALUE);
            method.setAccessible(false);

            Assert.assertEquals(String.format(MISMATCH_MESSAGE, "rating"), MISSION_RATING_MAXIMUM_VALUE, actualValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }

    @Test
    public void checkAndReformMissionRating_underMinimumValue() {
        try {
            Method method = this.missionControl.getClass().getDeclaredMethod("checkAndReformMissionRating");
            method.setAccessible(true);

            Double actualValue = (Double)method.invoke(this.missionControl, MISSION_RATING_UNDER_MINIMUM_VALUE);
            method.setAccessible(false);

            Assert.assertEquals(String.format(MISMATCH_MESSAGE, "rating"), MISSION_RATING_MINIMUM_VALUE, actualValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }


    @Test
    public void checkAndreformMissionBounty_overMaximumValue() {
        try {
            Method method = this.missionControl.getClass().getDeclaredMethod("checkAndreformMissionBounty");
            method.setAccessible(true);

            Double actualValue = (Double)method.invoke(this.missionControl, MISSION_BOUNTY_OVER_MAXIMUM_VALUE);
            method.setAccessible(false);

            Assert.assertEquals(String.format(MISMATCH_MESSAGE, "rating"), MISSION_BOUNTY_MAXIMUM_VALUE, actualValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }

    @Test
    public void checkAndreformMissionBounty_underMinimumValue() {
        try {
            Method method = this.missionControl.getClass().getDeclaredMethod("checkAndreformMissionBounty");
            method.setAccessible(true);

            Double actualValue = (Double)method.invoke(this.missionControl, MISSION_BOUNTY_UNDER_MINIMUM_VALUE);
            method.setAccessible(false);

            Assert.assertEquals(String.format(MISMATCH_MESSAGE, "rating"), MISSION_BOUNTY_MINIMUM_VALUE, actualValue);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {

        }
    }

    @Test
    public void generatedMissionClasses_test() {
        String mission1Name = this.missionControl.generateMission(MISSION_ID, MISSION_RATING, MISSION_BOUNTY).getClass().getSimpleName();
        String mission2Name = this.missionControl.generateMission(MISSION_ID, MISSION_RATING, MISSION_BOUNTY).getClass().getSimpleName();
        String mission3Name = this.missionControl.generateMission(MISSION_ID, MISSION_RATING, MISSION_BOUNTY).getClass().getSimpleName();
        String mission4Name = this.missionControl.generateMission(MISSION_ID, MISSION_RATING, MISSION_BOUNTY).getClass().getSimpleName();


        Assert.assertEquals(String.format(MISMATCH_MESSAGE, "mission class"),  ESCORT_MISSION_CLASS_NAME, mission1Name);
        Assert.assertEquals(String.format(MISMATCH_MESSAGE, "mission class"),  HUNT_MISSION_CLASS_NAME, mission2Name);
        Assert.assertEquals(String.format(MISMATCH_MESSAGE, "mission class"),  SURVEILLANCE_MISSION_CLASS_NAME, mission3Name);
        Assert.assertEquals(String.format(MISMATCH_MESSAGE, "mission class"),  ESCORT_MISSION_CLASS_NAME, mission4Name);
    }

    @Test
    public void generatedMissionClasses_parametersInRightOrder() {
        Mission mission = this.missionControl.generateMission(MISSION_ID, 0.0, MISSION_BOUNTY);

        Assert.assertEquals(String.format(MISMATCH_MESSAGE, "id"), MISSION_ID, mission.getId());
        Assert.assertEquals(0, (double) mission.getRating(), 0.01); //rating should still be 0, no matter what kind of mission
        Assert.assertTrue(mission.getBounty() > 0.0); //bounty is probably changed by the specific mission, but should still be > 0
    }
}
