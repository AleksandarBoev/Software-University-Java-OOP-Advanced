package pr0304Barracks.contracts;

import jdk.jshell.spi.ExecutionControl;

public interface Repository {

	String addUnit(Unit unit);

	String getStatistics();

	String removeUnit(String unitType) throws ExecutionControl.NotImplementedException;
}
