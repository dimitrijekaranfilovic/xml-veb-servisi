package rs.vakcinacija.imunizacija.saglasnost.support;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.saglasnost.dto.SaglasnostCreateRequest;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.mapper.AbstractConverter;

@Component
public class SaglasnostToSaglasnostCreateRequest extends AbstractConverter<SaglasnostZaSprovodjenjeImunizacije, SaglasnostCreateRequest> {

    protected SaglasnostToSaglasnostCreateRequest(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public SaglasnostCreateRequest convert(SaglasnostZaSprovodjenjeImunizacije saglasnostZaSprovodjenjeImunizacije) {
        return modelMapper.map(saglasnostZaSprovodjenjeImunizacije, SaglasnostCreateRequest.class);
    }
}
