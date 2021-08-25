package impl;

import base.DOutput;

import java.nio.ByteBuffer;


public class DByteBufferOutput implements DOutput {
    private final ByteBuffer buffer;

    public DByteBufferOutput(ByteBuffer buffer) {
        this.buffer = buffer;
    }

    public DByteBufferOutput(int size) {
        this.buffer = ByteBuffer.allocate(size);
    }


    public void writeInt(int data) {
        buffer.putInt(data);
    }

    public void writeLong(long data) {
        buffer.putLong(data);
    }

    public void writeShort(short data) {
        buffer.putShort(data);
    }

    public void writeByte(byte data) {
        buffer.put(data);
    }

    public void writeDouble(double data) {
        buffer.putDouble(data);
    }

    public void writeString(String data) {
        int size = data.length();
        buffer.putInt(size);
        for (int i = 0; i < size; i++) {
            buffer.putChar(data.charAt(i));
        }
    }

    public void writeChar(char data) {
        buffer.putChar(data);
    }

    public byte[] toArrayBytes() {
        byte[] a = new byte[buffer.position()];
        buffer.rewind();
        buffer.get(a);
        return a;
    }
}
