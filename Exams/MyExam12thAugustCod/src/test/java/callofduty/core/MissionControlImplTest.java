package callofduty.core;

import org.junit.Test;

public class MissionControlImplTest { //Mission Control is like a missionFactory with a bit more functionality


    @Test
    public void generateMission() {
    }

    //Things to be tested:
    //1 - reform private methods. Check if the reforming is done properly:
    //•	If the id has a higher length than the maximum allowed, it is substringed to the maximum allowed length.

    //•	If the rating has a value lower than the minimum allowed, it is set to the minimum allowed value.
    //•	If the rating has a value higher than the maximum allowed, it is set to the maximum allowed value.

    //•	If the bounty has a value lower than the minimum allowed, it is set to the minimum allowed value.
    //•	If the bounty has a value higher than the maximum allowed, it is set to the maximum allowed value.

    //NOTE: The evalutation of the minimum and maximum values is done BEFORE they are passed to the Mission object.


}
