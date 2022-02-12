package rs.vakcinacija.imunizacija.saglasnost.support;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.saglasnost.dto.SaglasnostCreateRequest;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.mapper.AbstractConverter;

@Component
public class SaglasnostCreateRequestToSaglasnost extends AbstractConverter<SaglasnostCreateRequest, SaglasnostZaSprovodjenjeImunizacije> {

    protected SaglasnostCreateRequestToSaglasnost(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SaglasnostZaSprovodjenjeImunizacije convert(@NonNull SaglasnostCreateRequest saglasnostCreateRequest) {
        return modelMapper.map(saglasnostCreateRequest, SaglasnostZaSprovodjenjeImunizacije.class);
    }
}
