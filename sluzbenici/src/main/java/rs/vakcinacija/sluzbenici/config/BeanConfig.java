package rs.vakcinacija.sluzbenici.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rs.vakcinacija.zajednicko.mapper.ModelMapperFactory;
import rs.vakcinacija.zajednicko.service.QRCodeService;

@Configuration
public class BeanConfig {
    @Bean
    public ModelMapper modelMapper() {
        var factory = new ModelMapperFactory();
        return factory.buildModelMapper();
    }

    @Bean
    public QRCodeService qrCodeService() {
        return new QRCodeService();
    }
}
