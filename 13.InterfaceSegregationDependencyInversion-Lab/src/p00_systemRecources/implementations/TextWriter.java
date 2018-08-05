package p00_systemRecources.implementations;

import p00_systemRecources.interfaces.Writer;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class TextWriter implements Writer {
    private String textFilePath;
    private PrintStream fileWriter;

    public TextWriter(String textFilePath) throws FileNotFoundException {
        this.textFilePath = textFilePath;
        this.fileWriter = new PrintStream(textFilePath);
    }

    @Override
    public void writeLine(String text) {
        this.fileWriter.println(text);
    }

    @Override
    public void close() {
        this.fileWriter.close();
    }
}
