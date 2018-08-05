package p00_systemRecources;

import p00_systemRecources.implementations.ConsoleWriter;
import p00_systemRecources.implementations.GreetingClock;
import p00_systemRecources.implementations.TextWriter;
import p00_systemRecources.implementations.TimeProviderImpl;
import p00_systemRecources.interfaces.Greeting;
import p00_systemRecources.interfaces.TimeProvider;
import p00_systemRecources.interfaces.Writer;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        TimeProvider timeProvider = new TimeProviderImpl();

        Writer consoleWriter = new ConsoleWriter();
        Greeting greetingClock = new GreetingClock(timeProvider, consoleWriter);
        greetingClock.greet();

        Writer textWriter = new TextWriter("src/p00_systemRecources/textFiles/someText.txt");
        Greeting greetingClock2 = new GreetingClock(timeProvider, textWriter);

        greetingClock2.greet(); //TODO when and how is it correct to close the writer
    }
}
