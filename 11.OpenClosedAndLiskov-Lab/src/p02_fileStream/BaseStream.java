package p02_fileStream;

public abstract class BaseStream implements Streamable {
    private int length;
    private int bytesSent;

    public int getLength() {
        return length;
    }

    public int getBytesSent() {
        return bytesSent;
    }
}
