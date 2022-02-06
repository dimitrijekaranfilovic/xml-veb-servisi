package rs.vakcinacija.zajednicko.mapper;

import org.modelmapper.ModelMapper;

public abstract class AbstractConverter<F, T> implements EntityConverter<F, T> {
    protected final ModelMapper modelMapper;

    protected AbstractConverter(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
