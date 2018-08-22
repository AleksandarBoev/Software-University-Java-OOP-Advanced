package panzer.contracts;

import java.util.List;

public interface Factory<T> {
    T create(List<String> arguments);
}
