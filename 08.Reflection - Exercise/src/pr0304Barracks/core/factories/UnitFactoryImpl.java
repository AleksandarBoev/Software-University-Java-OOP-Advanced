package pr0304Barracks.core.factories;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;
import pr0304Barracks.models.units.*;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "pr0304Barracks.models.units.";

    @Override
    public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
        // TODO: implement for problem 3

        switch (unitType.toUpperCase()) { //TODO possible problem
            case "ARCHER":
                return new Archer();

            case "SWORDSMAN":
                return new Swordsman();

            case "PIKEMAN":
                return new Pikeman();

            case "GUNNER":
                return new Gunner();

            case "HORSEMAN":
                return new Horseman();
        }

        throw new ExecutionControl.NotImplementedException("message");
    }
}
