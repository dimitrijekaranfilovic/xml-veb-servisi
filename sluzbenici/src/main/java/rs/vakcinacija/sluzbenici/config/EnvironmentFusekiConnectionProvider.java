package rs.vakcinacija.sluzbenici.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;

@Component
public class EnvironmentFusekiConnectionProvider implements FusekiConnectionProvider {
    @Value("${fuseki.conn.user}")
    private String user;

    @Value("${fuseki.conn.password}")
    private String password;

    @Value("${fuseki.conn.url}")
    private String url;

    @Override
    public String getUsername() {
        return user;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUpdateEndpoint() {
        return String.format("%s/update", url);
    }

    @Override
    public String getDataEndpoint() {
        return String.format("%s/data", url);
    }

    @Override
    public String getQueryEndpoint() {
        return String.format("%s/query", url);
    }
}
