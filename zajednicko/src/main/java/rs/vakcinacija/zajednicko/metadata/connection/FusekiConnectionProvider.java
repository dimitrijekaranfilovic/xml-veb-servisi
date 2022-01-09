package rs.vakcinacija.zajednicko.metadata.connection;

public interface FusekiConnectionProvider {
    String getUsername();

    String getPassword();

    String getUpdateEndpoint();

    String getDataEndpoint();

    String getQueryEndpoint();
}
