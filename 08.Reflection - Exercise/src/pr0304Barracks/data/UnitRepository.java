package pr0304Barracks.data;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.contracts.Repository;
import pr0304Barracks.contracts.Unit;

import java.util.Map;
import java.util.TreeMap;

public class UnitRepository implements Repository {

	private Map<String, Integer> amountOfUnits;

	public UnitRepository() {
		this.amountOfUnits = new TreeMap<>();
	}

	public String addUnit(Unit unit) {
		String unitType = unit.getClass().getSimpleName();
		if (!this.amountOfUnits.containsKey(unitType)) {
			this.amountOfUnits.put(unitType, 0);
		}

		int newAmount = this.amountOfUnits.get(unitType) + 1;
		this.amountOfUnits.put(unitType, newAmount);
		return String.format("%s added!", unitType);
	}

	public String getStatistics() {
		StringBuilder statBuilder = new StringBuilder();
		for (Map.Entry<String, Integer> entry : amountOfUnits.entrySet()) {
			String formatedEntry =
					String.format("%s -> %d%n", entry.getKey(), entry.getValue());
			statBuilder.append(formatedEntry);
		}
		statBuilder.setLength(
				statBuilder.length() - System.lineSeparator().length());

		return statBuilder.toString();
	}

	public String removeUnit(String unitType) throws ExecutionControl.NotImplementedException {
		// TODO: implement for problem 4
		if (!this.amountOfUnits.containsKey(unitType)) {
			throw new ExecutionControl.NotImplementedException("No such units in repository.");
		} else {
			int count = this.amountOfUnits.get(unitType);
			this.amountOfUnits.put(unitType, --count);
			if (count == 0) {
				this.amountOfUnits.remove(unitType);
			}

			return String.format("%s retired!", unitType);
		}
	}
}
