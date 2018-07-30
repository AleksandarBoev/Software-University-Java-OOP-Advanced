package practise.contracts;

public interface CommandInterpreter {
    //should there be one CommandInterpreter for the repository and one for the Car or one for both because ALL commands implement "Executable"?
    Executable interpretCommand(String... data);
}
