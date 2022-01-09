package rs.vakcinacija.sluzbenici.config;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.riot.web.HttpOp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;

import javax.annotation.PostConstruct;

@Component
public class EnvironmentFusekiConnectionProvider implements FusekiConnectionProvider {
    @Value("${fuseki.conn.user}")
    private String user;

    @Value("${fuseki.conn.password}")
    private String password;

    @Value("${fuseki.conn.url}")
    private String url;

    @PostConstruct
    public void configureSecureFusekiConnection() {
        var credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(getUsername(), getPassword()));
        var httpclient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpOp.setDefaultHttpClient(httpclient);
    }

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
