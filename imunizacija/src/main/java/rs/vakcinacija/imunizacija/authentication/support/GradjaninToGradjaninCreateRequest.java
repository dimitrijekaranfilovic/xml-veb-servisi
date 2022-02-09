package rs.vakcinacija.imunizacija.authentication.support;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import rs.vakcinacija.imunizacija.authentication.dto.GradjaninCreateRequest;
import rs.vakcinacija.imunizacija.authentication.model.Gradjanin;
import rs.vakcinacija.zajednicko.mapper.AbstractConverter;

@Component
public class GradjaninToGradjaninCreateRequest extends AbstractConverter<Gradjanin, GradjaninCreateRequest> {

    protected GradjaninToGradjaninCreateRequest(ModelMapper modelMapper) {
        super(modelMapper);
    }

    @Override
    public GradjaninCreateRequest convert(@NonNull Gradjanin gradjanin) {
        return modelMapper.map(gradjanin, GradjaninCreateRequest.class);
    }
}
