package rs.vakcinacija.sluzbenici.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.vakcinacija.zajednicko.connection.ConnectionProvider;

@Component
public class EnvironmentConnectionProvider implements ConnectionProvider {
    @Value("${conn.uri}")
    public String uri;

    @Value("${conn.user}")
    public String user;

    @Value("${conn.password}")
    public String password;

    @Value("${conn.driver}")
    public String driver;

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getDriver() {
        return driver;
    }
}
