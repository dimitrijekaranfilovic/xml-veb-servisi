package rs.vakcinacija.zajednicko.data.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.OutputStream;

public class JAXBEntityManager<T> {
    protected final JAXBContext context;
    protected final Marshaller marshaller;
    protected final Unmarshaller unmarshaller;
    protected final Class<T> entityClazz;

    protected static final Logger log = LoggerFactory.getLogger(JAXBEntityManager.class);

    private JAXBEntityManager(JAXBContext context, Class<T> entityClazz) throws JAXBException {
        this.context = context;
        this.marshaller = context.createMarshaller();
        this.unmarshaller = context.createUnmarshaller();
        this.entityClazz = entityClazz;
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
    }

    public static <T> JAXBEntityManager<T> forEntity(Class<T> entityClazz) {
        try {
            return new JAXBEntityManager<>(JAXBContext.newInstance(entityClazz), entityClazz);
        } catch (Exception exception) {
            log.error("Cannot create an instance of JAXBEntityManager for class " + entityClazz.getName());
            return null;
        }
    }

    public void marshall(T resource, OutputStream outputStream) throws JAXBException {
        marshaller.marshal(resource, outputStream);
    }

    @SuppressWarnings("unchecked")
    public T unmarshall(Node resource) throws JAXBException {
        return (T) unmarshaller.unmarshal(resource);
    }

    public JAXBContext getContext() {
        return context;
    }

    public Marshaller getMarshaller() {
        return marshaller;
    }

    public Unmarshaller getUnmarshaller() {
        return unmarshaller;
    }
}
