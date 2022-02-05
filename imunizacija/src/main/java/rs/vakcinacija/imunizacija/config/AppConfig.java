package rs.vakcinacija.imunizacija.config;


import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.Jaxb2RootElementHttpMessageConverter;

@Configuration
public class AppConfig {

    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = new Jaxb2RootElementHttpMessageConverter();
        return new HttpMessageConverters(additional);
    }
}
