package rs.vakcinacija.zajednicko.data.connection;

public interface ExistConnectionProvider {

    String getUri();

    String getUser();

    String getPassword();

    String getDriver();

}
