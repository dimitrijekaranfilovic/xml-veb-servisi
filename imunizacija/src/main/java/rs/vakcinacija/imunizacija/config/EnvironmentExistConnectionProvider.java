package rs.vakcinacija.imunizacija.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;

@Component
public class EnvironmentExistConnectionProvider implements ExistConnectionProvider {
    @Value("${exist.conn.uri}")
    public String uri;

    @Value("${exist.conn.user}")
    public String user;

    @Value("${exist.conn.password}")
    public String password;

    @Value("${exist.conn.driver}")
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
