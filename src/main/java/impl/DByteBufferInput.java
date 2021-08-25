package impl;

import base.DInput;

import java.nio.ByteBuffer;

public class DByteBufferInput implements DInput {
    private final ByteBuffer buffer;

    public DByteBufferInput(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public DByteBufferInput(byte[] bytes) {
        this.buffer = ByteBuffer.wrap(bytes);
    }


    public int readInt() {
        return buffer.getInt();
    }

    public long readLong() {
        return buffer.getLong();
    }

    public short readShort() {
        return buffer.getShort();
    }

    public byte readByte() {
        return buffer.get();
    }

    public double readDouble() {
        return buffer.getDouble();
    }

    public String readString() {
        StringBuilder sb = new StringBuilder();
        int size = buffer.getInt();
        for (int i = 0; i < size; i++) {
            sb.append(buffer.getChar());
        }
        return sb.toString();
    }

    public char readChar() {
        return buffer.getChar();
    }
}
