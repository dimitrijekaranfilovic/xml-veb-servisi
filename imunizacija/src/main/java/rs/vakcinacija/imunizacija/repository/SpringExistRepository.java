package rs.vakcinacija.imunizacija.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import rs.vakcinacija.zajednicko.repository.ExistRepository;


public class SpringExistRepository<T> extends ExistRepository<T> {


    protected SpringExistRepository(String collectionName, Class<T> clazz)  {
        super(collectionName, clazz);
    }

    @Autowired
    protected void setAutowiredDriver(@Value("${conn.driver}") String driver){
        super.setDriver(driver);
    }

    @Autowired
    protected void setAutowiredPassword(@Value("${conn.password}") String password) {
        super.setPassword(password);
    }

    @Autowired
    protected void setAutowiredUser(@Value("${conn.user}") String user){
        super.setUser(user);
    }

    @Autowired
    protected void setAutowiredUri(@Value("${conn.uri}") String uri){
        super.setUri(uri);
    }




}
