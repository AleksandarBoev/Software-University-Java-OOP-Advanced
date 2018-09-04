package callofduty.manager;

import callofduty.abstract_classes.BaseAgent;
import callofduty.abstract_classes.BaseMission;
import callofduty.agents.MasterAgent;
import callofduty.agents.NoviceAgent;
import callofduty.core.MissionControlImpl;
import callofduty.interfaces.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class MissionManagerImpl implements MissionManager {
    private Map<String, Agent> idAgent;
    private Map<String, BountyAgent> idMasterAgent;
    private Map<String, Mission> idMission;
    private Integer totalAssignedMissionsCount;
    private MissionControl missionFactory;

    private Field acceptedMissionsField;
    private Field completedMissionsField;

    public MissionManagerImpl() {
        this.idAgent = new HashMap<>();
        this.idMasterAgent = new HashMap<>();
        this.idMission = new HashMap<>();
        this.totalAssignedMissionsCount = 0;
        this.missionFactory = new MissionControlImpl();

        try {
            this.acceptedMissionsField = BaseAgent.class.getDeclaredField("acceptedMissions");
            this.acceptedMissionsField.setAccessible(true);
            this.completedMissionsField = BaseAgent.class.getDeclaredField("completedMissions");
            this.completedMissionsField.setAccessible(true);
        } catch (Exception e) {

        }
    }

    //Agent 007b Donald
//•	Agent {id} {name}
    @Override
    public String agent(List<String> arguments) {
        String id = arguments.get(1);
        String name = arguments.get(2);
        Agent noviceAgent = new NoviceAgent(id, name);
        this.idAgent.put(id, noviceAgent);
        return String.format("Registered Agent - %s:%s", name, id);
    }

    //Request 007b 1x1 25 5000
//•	Request {agentId} {missionId} {missionRating} {missionBounty}
    @Override
    public String request(List<String> arguments) {
        String agentId = arguments.get(1);
        String missionId = arguments.get(2);
        Double missionRating = Double.parseDouble(arguments.get(3));
        Double missionBounty = Double.parseDouble(arguments.get(4));
        this.totalAssignedMissionsCount++;

        Mission newMission = this.missionFactory.generateMission(missionId, missionRating, missionBounty);
        this.idMission.put(missionId, newMission);
        Agent currentAgent = null;

        if (this.idAgent.containsKey(agentId)) {
            currentAgent = this.idAgent.get(agentId);
        } else {
            currentAgent = this.idMasterAgent.get(agentId);
        }
        currentAgent.acceptMission(newMission);

        return String.format("Assigned %s - %s to Agent - %s",
                newMission.getClass().getSimpleName().replace("Mission", " Mission"), missionId, currentAgent.getName());
    }

    //Complete 007b
    //•	Complete {agentId}
    @Override
    public String complete(List<String> arguments) {
        String agentId = arguments.get(1);
        Agent currentAgent = null;
        if (this.idAgent.containsKey(agentId)) {
            currentAgent = this.idAgent.get(agentId);
        } else {
            currentAgent = this.idMasterAgent.get(agentId);
        }
        currentAgent.completeMissions();

        if (currentAgent instanceof NoviceAgent) {
            try {
                List<Mission> completedMissions = (ArrayList<Mission>)this.completedMissionsField.get(currentAgent);
                int completedMissionsCount = completedMissions.size();
                if (completedMissionsCount >= 3) {
                    BountyAgent masterAgent = new MasterAgent(currentAgent.getId(), currentAgent.getName(), currentAgent.getRating());
                    List<Mission> masterAgentCompletedMissions = (ArrayList<Mission>)this.completedMissionsField.get(masterAgent);
                    masterAgentCompletedMissions.addAll(completedMissions);

                    this.idAgent.remove(agentId);
                    this.idMasterAgent.put(agentId, masterAgent);
                }
            } catch (IllegalAccessException e) {

            }
        }

        return String.format("Agent - %s:%s has completed all assigned missions.", currentAgent.getName(), agentId);
    }

    //Status 007b
    //•	Status {id}
    @Override
    public String status(List<String> arguments) {
        String id = arguments.get(1);

        if (this.idAgent.containsKey(id)) {
            return this.idAgent.get(id).toString();
        } else if (this.idMasterAgent.containsKey(id)) {
            return this.idMasterAgent.get(id).toString();
        } else {
            return this.idMission.get(id).toString();
        }
    }

    //Over
    @Override
    public String over(List<String> arguments) {
        int totalCompletedMissions = 0;
        try {
            Field completedField = BaseMission.class.getDeclaredField("completed");
            completedField.setAccessible(true);

            for (Mission mission : idMission.values()) {
                if ((boolean)completedField.get(mission)) {
                    totalCompletedMissions++;

                }
            }

            completedField.setAccessible(false);
        } catch (NoSuchFieldException | IllegalAccessException e) {

        }

        Double totalRating = this.idAgent.values().stream()
                .mapToDouble(r -> r.getRating())
                .sum();

        totalRating += this.idMasterAgent.values().stream()
                .mapToDouble(r -> r.getRating())
                .sum();

        Double totalBounty = this.idMasterAgent.values().stream()
                .mapToDouble(b -> b.getBounty())
                .sum();

        StringBuilder sb = new StringBuilder();
        sb.append("Novice Agents: ").append(this.idAgent.size()).append(System.lineSeparator());
        sb.append("Master Agents: ").append(this.idMasterAgent.size()).append(System.lineSeparator());
        sb.append("Assigned Missions: ").append(this.totalAssignedMissionsCount).append(System.lineSeparator());
        sb.append("Completed Missions: ").append(totalCompletedMissions).append(System.lineSeparator());
        sb.append(String.format("Total Rating Given: %.2f", totalRating)).append(System.lineSeparator());
        sb.append(String.format("Total Bounty Given: $%.2f",totalBounty));

        return sb.toString();
    }

    //Novice Agents: {noviceAgentsCount}
    //Master Agents: {masterAgentsCount}
    //Assigned Missions: {totalAssignedMissionsCount} --> including the ones that later got completed!
    //Completed Missions: {totalCompletedMissionsCount}
    //Total Rating Given: {totalRatingEarned}
    //Total Bounty Given: ${totalBountyEarned} --> bounty lost to novice agents is NOT considered bounty given.
}
