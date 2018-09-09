package pr0304Barracks.core.factories;

import jdk.jshell.spi.ExecutionControl;
import pr0304Barracks.contracts.Unit;
import pr0304Barracks.contracts.UnitFactory;
import pr0304Barracks.models.units.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class UnitFactoryImpl implements UnitFactory {

    private static final String UNITS_PACKAGE_NAME =
            "pr0304Barracks.models.units.";

    @Override
    public Unit createUnit(String unitType) throws ExecutionControl.NotImplementedException {
        // TODO: implement for problem 3
        try {
            Class unitClass = Class.forName(UNITS_PACKAGE_NAME + unitType);
            Constructor unitConstructor = unitClass.getConstructor();
            return (Unit) unitConstructor.newInstance();
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
            return null;
        }
//        switch (unitType.toUpperCase()) {
//            case "ARCHER":
//                return new Archer();
//
//            case "SWORDSMAN":
//                return new Swordsman();
//
//            case "PIKEMAN":
//                return new Pikeman();
//
//            case "GUNNER":
//                return new Gunner();
//
//            case "HORSEMAN":
//                return new Horseman();
//        }
//
//        throw new ExecutionControl.NotImplementedException("message");
    }
}
