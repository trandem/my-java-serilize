package base;

public interface DSerialize<T> {

    void write(DMarshaller marshaller, DOutput output, T x);

    T read(DMarshaller marshaller, DInput input);
}
