package rs.vakcinacija.imunizacija.authentication.support;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.authentication.dto.GradjaninCreateRequest;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.zajednicko.mapper.AbstractConverter;

@Component
public class GradjaninCreateRequestToGradjanin extends AbstractConverter<GradjaninCreateRequest, Gradjanin> {

    protected GradjaninCreateRequestToGradjanin(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public Gradjanin convert(GradjaninCreateRequest gradjaninCreateRequest) {
        return modelMapper.map(gradjaninCreateRequest, Gradjanin.class);
    }
}
