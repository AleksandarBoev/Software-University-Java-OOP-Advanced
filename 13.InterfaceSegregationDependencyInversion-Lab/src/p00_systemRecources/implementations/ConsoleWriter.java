package p00_systemRecources.implementations;

import p00_systemRecources.interfaces.Writer;

public class ConsoleWriter implements Writer {
    @Override
    public void writeLine(String text) {
        System.out.println(text);
    }

    @Override
    public void close() {
        //typically writers should be able to be closed. I think
    }
}
