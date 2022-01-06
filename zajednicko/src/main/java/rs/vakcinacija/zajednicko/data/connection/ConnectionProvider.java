package rs.vakcinacija.zajednicko.data.connection;

public interface ConnectionProvider {

    String getUri();

    String getUser();

    String getPassword();

    String getDriver();

}
