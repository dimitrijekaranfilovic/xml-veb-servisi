package rs.vakcinacija.zajednicko.data.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public class JAXBEntityManager<T> {
    protected final JAXBContext context;
    protected  Marshaller marshaller;
    protected  Unmarshaller unmarshaller;
    protected final Class<T> entityClazz;

    protected static final Logger log = LoggerFactory.getLogger(JAXBEntityManager.class);

    private JAXBEntityManager(JAXBContext context, Class<T> entityClazz) throws JAXBException, SAXException {
        this.context = context;
        this.marshaller = context.createMarshaller();
        this.unmarshaller = context.createUnmarshaller();
        this.marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        var schemaFile = new File(String.format("src/main/resources/schemas/%s", getSchemaName(entityClazz)));
        if (schemaFile.exists()) {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(schemaFile);
            this.unmarshaller.setSchema(schema);
            this.marshaller.setSchema(schema);
            var eventHandler = new SchemaValidationEventHandler();
            this.unmarshaller.setEventHandler(eventHandler);
            this.marshaller.setEventHandler(eventHandler);
        }

        this.entityClazz = entityClazz;
    }

    public String getSchemaName(Class<T> clazz) {
        String str = clazz.getSimpleName();
        StringBuilder result = new StringBuilder();
        char c = str.charAt(0);
        result.append(Character.toLowerCase(c));
        for (int i = 1; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                result.append("_").append(Character.toLowerCase(ch));
            } else {
                result.append(ch);
            }
        }
        result.append(".xsd");
        return result.toString();
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

    @SuppressWarnings("unchecked")
    public T unmarshall(InputStream resource) throws JAXBException {
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
