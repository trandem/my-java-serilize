package impl;

import base.DInput;
import base.DMarshaller;
import base.DOutput;
import base.DSerialize;

import java.util.HashMap;
import java.util.Map;

public class DMarshallerIml implements DMarshaller {
    private final Map<String, DSerialize<?>> register;

    public DMarshallerIml() {
        this.register = new HashMap<>();
    }

    public void register(DSerialize<?> x) {
        register.put(x.getClass().getName(), x);
    }

    @Override
    public void register(Class<?> x) throws IllegalAccessException, InstantiationException {
        Object y = x.newInstance();
        if (y instanceof DSerialize<?>) {
            register.put(y.getClass().getName(), cast(y));
        }
    }

    public void write(Object x, DOutput output) {
        String className = x.getClass().getName();
        output.writeString(className);
        if (register.containsKey(className)) {
            register.get(className).write(this, output, cast(x));
        } else {
            throw new RuntimeException("object need to register first");
        }
    }

    public static <T> T cast(Object x) {
        return (T) x;
    }

    public <T> T read(DInput input) {
        String className = input.readString();
        if (register.containsKey(className)) {
            return cast(register.get(className).read(this, input));
        } else {
            throw new RuntimeException("object need to register first");
        }
    }
}
