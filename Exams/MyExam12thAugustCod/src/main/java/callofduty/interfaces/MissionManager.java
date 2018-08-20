package callofduty.interfaces;

import java.util.List;

public interface MissionManager {
    //TODO this is like an engine. It is going to be the repository (holding all the data in collections).
    //TODO also it is going to execute the commands AND print the String results from the commands!
    String agent(List<String> arguments);

    String request(List<String> arguments);

    String complete(List<String> arguments);

    String status(List<String> arguments);

    String over(List<String> arguments);
}
