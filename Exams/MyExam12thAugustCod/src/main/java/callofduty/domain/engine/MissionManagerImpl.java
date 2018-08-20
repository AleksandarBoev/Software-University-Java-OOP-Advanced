package callofduty.domain.engine;

import callofduty.abstractClasses.BaseAgent;
import callofduty.abstractClasses.BaseMission;
import callofduty.core.MissionControlImpl;
import callofduty.domain.agents.MasterAgent;
import callofduty.domain.agents.NoviceAgent;
import callofduty.interfaces.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MissionManagerImpl implements MissionManager {
    //TODO create fields of type collections and also the reader, writer and so on... Basically most of the things
    //BUT some of them have to be injected from the Main class!
    private Map<String, Mission> idMission; //is this necessary?
    private Map<String, Agent> idNovice; //every new agent is a novice!
    private Map<String, BountyAgent> idMaster; //when a new master appears, a novice dissapears!
    private int totalAssignedMissions; //for the over command

    private MissionControl missionControl;

    public MissionManagerImpl() {
        this.idMission = new HashMap<>();
        this.idNovice = new HashMap<>();
        this.idMaster = new HashMap<>();
        this.missionControl = new MissionControlImpl();

        this.totalAssignedMissions = 0;
    }

    @Override
    public String agent(List<String> arguments) { //TODO create new Agent
        //•	Agent {id} {name}
        String id = arguments.get(1);
        String name = arguments.get(2);
        Agent noviceAgent = new NoviceAgent(id, name);

        this.idNovice.put(id, noviceAgent);
        return String.format("Registered Agent - %s:%s", name, id);
    }

    @Override
    public String request(List<String> arguments) { //TODO creates and adds the mission to an agent
        //•	Request {agentId} {missionId} {missionRating} {missionBounty}
        String agentId = arguments.get(1);
        String missionId = arguments.get(2);
        Double missionRating = Double.parseDouble(arguments.get(3));
        Double missionBounty = Double.parseDouble(arguments.get(4));

        Agent agent = null;

        if (this.idNovice.containsKey(agentId)) {
            agent = this.idNovice.get(agentId);
        } else {
            agent = this.idMaster.get(agentId);
        }

        Mission newMission = this.missionControl.generateMission(missionId, missionRating, missionBounty);
        agent.acceptMission(newMission);
        this.idMission.put(missionId, newMission);
        this.totalAssignedMissions++;

        String missionType = newMission.getClass().getSimpleName().replace("Mission", "");

        return String.format("Assigned %s Mission - %s to Agent - %s",
                missionType, missionId, agentId);
    }

    @Override
    public String complete(List<String> arguments) {
        //TODO complete all the missions of an agent and potentially turn him into a Master! But a master should stay the same!
        //•	Complete {agentId}

        String agentId = arguments.get(1);
        Agent agent = null;

        if (this.idNovice.containsKey(agentId)) {
            agent = this.idNovice.get(agentId);
        } else {
            agent = this.idMaster.get(agentId);
        }

        agent.completeMissions(); //TODO the master doesn't get the ratings? So the BaseAgent doesn't get the ratings?

        Method completedMissions = null;
        int completedMissionsCount = 0;
        try {
            completedMissions = BaseAgent.class.getDeclaredMethod("getCompletedMissionsCount", null);
            completedMissions.setAccessible(true);
            completedMissionsCount = (int)completedMissions.invoke(agent, null);
        } catch (Exception e) {
            System.out.println("Something went wrong with extracting the completed missions!");
        }

        if (completedMissionsCount >= 3 && !(agent instanceof MasterAgent)) {
            //    private List<Mission> completedMissions;
            List<Mission> extractedCompletedMissions = null;
            try {
                Field completedMissionsField = BaseAgent.class.getDeclaredField("completedMissions");
                completedMissionsField.setAccessible(true);
                extractedCompletedMissions = (List<Mission>)completedMissionsField.get(agent);

            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }

            this.idNovice.remove(agentId);
            BountyAgent newMaster = new MasterAgent(agent.getId(), agent.getName(), extractedCompletedMissions);
            this.idMaster.put(agentId, newMaster);
        }
        return String.format("Agent - %s:%s has completed all assigned missions.", agent.getName(), agentId);
    }

    @Override
    public String status(List<String> arguments) {
        //•	Status {id}
        String id = arguments.get(1);

        Identifiable identifiable = null;
        if (this.idMission.containsKey(id)) {
            identifiable = this.idMission.get(id);
        } else if (this.idNovice.containsKey(id)) {
            identifiable = this.idNovice.get(id);
        } else if (this.idMaster.containsKey(id)) {
            identifiable = this.idMaster.get(id);
        }

        return identifiable.toString();
    }

    @Override
    public String over(List<String> arguments) {
        //Novice Agents: 1
        //Master Agents: 1
        //Assigned Missions: 3
        //Completed Missions: 3
        //Total Rating Given: 112,50
        //Total Bounty Given: $0,00
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Novice Agents: %d", this.idNovice.size())).append(System.lineSeparator());
        sb.append(String.format("Master Agents: %d", this.idMaster.size())).append(System.lineSeparator());
        sb.append(String.format("Assigned Missions: %d", this.totalAssignedMissions)).append(System.lineSeparator());

        int totalCompletedMissions = this.getCompletedMissionsCount();

        sb.append(String.format("Completed Missions: %d", totalCompletedMissions)).append(System.lineSeparator());

        double totalRatingGiven = this.getTotalRating();
        double totalBountyGiven = this.getTotalBounty(); //need to be summed from all agents. If a novice agent completed a mission, then he doesn't get bounty and that bounty value is lost

        sb.append(String.format("Total Rating Given: %.2f", totalRatingGiven)).append(System.lineSeparator());
        sb.append(String.format("Total Bounty Given: $%.2f", totalBountyGiven));

        return sb.toString();
    }



    private int getCompletedMissionsCount() {
        Method completedMissions = null;
        try {
            completedMissions = BaseAgent.class.getDeclaredMethod("getCompletedMissionsCount", null);
            completedMissions.setAccessible(true);
        } catch (Exception e) {
            System.out.println("Something went wrong with extracting the completed missions!");
        }

        int totalCompletedMissions = 0;
        try {
            for (Agent agent : this.idNovice.values()) {
                totalCompletedMissions += (int)completedMissions.invoke(agent, null);
            }

            for (Agent agent : this.idMaster.values()) {
                totalCompletedMissions += (int)completedMissions.invoke(agent, null);
            }
        } catch (Exception e) {
            System.out.println("invoking the completed missions method failed!");
        }

        return totalCompletedMissions;
    }

    private Double getTotalBounty() {
        Double totalBounty = 0.0;

        for (BountyAgent bountyAgent : this.idMaster.values()) {
            totalBounty += bountyAgent.getBounty();
        }

        return totalBounty;
    }

    private Double getTotalRating() {
        Double totalRating = 0.0;

        for (Agent agent : this.idNovice.values()) {
            totalRating += agent.getRating();
        }

        for (BountyAgent bountyAgent : this.idMaster.values()) {
            totalRating += bountyAgent.getRating();
        }

        return totalRating;
    }
}
