package pr0304Barracks.contracts;

public interface CommandInterpreter { // will decide, based on the input parameters, which implementation of the Executable interface it will return.

	Executable interpretCommand(); //deleted "String[] data" and "String commandName" parameters
	//which is better? To pass the parameters via this method or via the implementation Classes constructors, as I have done now?
}
