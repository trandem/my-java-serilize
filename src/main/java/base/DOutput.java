package base;

public interface DOutput {
    void writeInt(int data);

    void writeLong(long data);

    void writeShort(short data);

    void writeByte(byte data);

    void writeDouble(double data);


    void writeString(String data);

    void writeChar(char data);

    byte[] toArrayBytes();
}
