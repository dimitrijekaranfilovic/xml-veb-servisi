package rs.vakcinacija.zajednicko.connection;

public interface ConnectionProvider {

    String getUri();

    String getUser();

    String getPassword();

    String getDriver();

}
