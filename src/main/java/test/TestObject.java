package test;

import base.DInput;
import base.DMarshaller;
import base.DOutput;
import base.DSerialize;
import base.anotation.Marshaller;
import com.esotericsoftware.kryo.kryo5.Kryo;
import com.esotericsoftware.kryo.kryo5.io.ByteBufferInput;
import com.esotericsoftware.kryo.kryo5.io.ByteBufferOutput;
import com.esotericsoftware.kryo.kryo5.io.Input;
import com.esotericsoftware.kryo.kryo5.io.Output;
import impl.DByteBufferInput;
import impl.DByteBufferOutput;
import impl.DMarshallerIml;

@Marshaller(name = "test.TestObject")
public class TestObject implements DSerialize<TestObject> {
    private String userName;
    private int age;

    private User someOne;

    public TestObject(String userName, int age, User someOne) {
        this.userName = userName;
        this.age = age;
        this.someOne = someOne;
    }

    public TestObject() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User getSomeOne() {
        return someOne;
    }

    public void setSomeOne(User someOne) {
        this.someOne = someOne;
    }

    public void write(DMarshaller marshaller, DOutput output, TestObject object) {
        output.writeString(object.getUserName());
        output.writeInt(object.getAge());
        marshaller.write(object.getSomeOne(), output);
    }

    public TestObject read(DMarshaller marshaller, DInput input) {
        TestObject object = new TestObject();
        object.setUserName(input.readString());
        object.setAge(input.readInt());
        object.setSomeOne(marshaller.read(input));
        return object;
    }

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
//        DMarshaller marshaller = new DMarshallerIml();
//        marshaller.register(TestObject.class);
//        marshaller.register(User.class);
//
//        User demtv = new User("demtv");
//        TestObject object = new TestObject("phuongmai", 23, demtv);
//
//        DOutput output = new DByteBufferOutput(1000);
//
//        marshaller.write(object, output);
//        TestObject y = marshaller.read(new DByteBufferInput(output.toArrayBytes()));
//        System.out.println(y.userName);
//        System.out.println(y.age);
//        System.out.println(y.someOne.getName());
        Kryo kryo = new Kryo();
        kryo.register(User.class);
        Output output = new ByteBufferOutput(1000);
        User user = new User("demtv");
        kryo.writeObject(output,user);

        Input input = new ByteBufferInput(output.toBytes());
        User demtv = kryo.readObject(input,User.class);
        System.out.println(demtv.getName());
    }
}
