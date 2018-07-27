package P02_FileStream;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Field;

public class TestStreamProgressInfo {
    BaseStream baseStream;

    @Test
    public void testCalculateCurrentPercentMusic() throws NoSuchFieldException, IllegalAccessException {
        //(this.file.getBytesSent() * 100) / this.file.getLength()
        this.baseStream = new Music();
        Field length = this.baseStream.getClass().getSuperclass().getDeclaredField("length");
        Field bytesSent = this.baseStream.getClass().getSuperclass().getDeclaredField("bytesSent");

        length.setAccessible(true);
        length.set(this.baseStream, 10);
        length.setAccessible(false);

        bytesSent.setAccessible(true);
        bytesSent.set(this.baseStream, 10);
        bytesSent.setAccessible(false);

        int expectedResult = 100;
        StreamProgressInfo streamProgressInfo = new StreamProgressInfo(this.baseStream);
        int actualResult = streamProgressInfo.calculateCurrentPercent();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void testCalculateCurrentPercentFile() throws NoSuchFieldException, IllegalAccessException {
        this.baseStream = new File();
        Field length = this.baseStream.getClass().getSuperclass().getDeclaredField("length");
        Field bytesSent = this.baseStream.getClass().getSuperclass().getDeclaredField("bytesSent");

        length.setAccessible(true);
        length.set(this.baseStream, 10);
        length.setAccessible(false);

        bytesSent.setAccessible(true);
        bytesSent.set(this.baseStream, 10);
        bytesSent.setAccessible(false);

        int expectedResult = 100;
        StreamProgressInfo streamProgressInfo = new StreamProgressInfo(this.baseStream);
        int actualResult = streamProgressInfo.calculateCurrentPercent();

        Assert.assertEquals(expectedResult, actualResult);
    }
}
