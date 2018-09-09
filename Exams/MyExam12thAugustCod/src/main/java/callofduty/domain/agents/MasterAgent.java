package callofduty.domain.agents;

import callofduty.abstractClasses.BaseMasterAgent;
import callofduty.interfaces.Mission;

import java.util.List;

public class MasterAgent extends BaseMasterAgent {
    public MasterAgent(String id, String name, List<Mission> missions) {
        super(id, name, missions);
    }
}
