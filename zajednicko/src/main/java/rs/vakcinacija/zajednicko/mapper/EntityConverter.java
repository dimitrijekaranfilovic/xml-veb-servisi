package rs.vakcinacija.zajednicko.mapper;

public interface EntityConverter<F, T> {
    T convert(F source);
}