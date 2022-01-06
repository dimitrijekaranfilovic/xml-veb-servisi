package rs.vakcinacija.zajednicko.repository;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import rs.vakcinacija.zajednicko.connection.ConnectionProvider;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Optional;
import java.util.UUID;

public class ExistRepository<T> {
    protected final String collectionId;
    protected final Class<T> entityClazz;
    protected final ConnectionProvider connectionProvider;

    protected ExistRepository(String collectionName, Class<T> clazz, ConnectionProvider connectionProvider) {
        this.collectionId = "/db/sample/xml/" + collectionName;
        this.entityClazz = clazz;
        this.connectionProvider = connectionProvider;
    }

    public UUID save(T entity) throws Exception {
        var id = UUID.randomUUID();
        String documentId = id + ".xml";
        registerDatabase();
        Collection collection = null;
        XMLResource xmlResource = null;
        OutputStream outputStream = new ByteArrayOutputStream();
        try {
            collection = getOrCreateCollection(collectionId);
            xmlResource = (XMLResource) collection.createResource(documentId, XMLResource.RESOURCE_TYPE);
            JAXBContext context = JAXBContext.newInstance(entityClazz);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(entity, outputStream);
            xmlResource.setContent(outputStream);
            collection.storeResource(xmlResource);
        } finally {
            cleanup(collection, xmlResource);
        }
        return id;
    }

    @SuppressWarnings("unchecked")
    public Optional<T> read(UUID id) throws Exception {
        String documentId = id.toString() + ".xml";
        registerDatabase();
        Collection collection = null;
        XMLResource xmlResource = null;
        try {
            collection = DatabaseManager.getCollection(connectionProvider.getUri() + collectionId);
            collection.setProperty(OutputKeys.INDENT, "yes");
            xmlResource = (XMLResource) collection.getResource(documentId);
            if (xmlResource == null) {
                return Optional.empty();
            }
            JAXBContext context = JAXBContext.newInstance(entityClazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            T entity = (T) unmarshaller.unmarshal(xmlResource.getContentAsDOM());
            return Optional.of(entity);
        } finally {
            cleanup(collection, xmlResource);
        }
    }

    private void cleanup(Collection collection, XMLResource xmlResource) {
        if (xmlResource != null) {
            try {
                ((EXistResource) xmlResource).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
        if (collection != null) {
            try {
                collection.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }

    private void registerDatabase() throws Exception {
        Database database = (Database) Class.forName(connectionProvider.getDriver()).getDeclaredConstructor().newInstance();
        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
    }

    private Collection getOrCreateCollection(String collectionUri) throws XMLDBException {
        return getOrCreateCollection(collectionUri, 0);
    }

    private Collection getOrCreateCollection(String collectionUri, int pathSegmentOffset) throws XMLDBException {
        Collection collection = DatabaseManager.getCollection(connectionProvider.getUri() + collectionUri, connectionProvider.getUser(), connectionProvider.getPassword());
        if (collection != null) {
            return collection;
        }
        if(collectionUri.startsWith("/")) {
            collectionUri = collectionUri.substring(1);
        }
        String[] pathSegments = collectionUri.split("/");
        if(pathSegments.length > 0) {
            StringBuilder path = new StringBuilder();
            for(int i = 0; i <= pathSegmentOffset; i++) {
                path.append("/").append(pathSegments[i]);
            }
            Collection startCollection = DatabaseManager.getCollection(connectionProvider.getUri() + path, connectionProvider.getUser(), connectionProvider.getPassword());
            if (startCollection == null) {
                String parentPath = path.substring(0, path.lastIndexOf("/"));
                Collection parentCollection = DatabaseManager.getCollection(connectionProvider.getUri() + parentPath, connectionProvider.getUser(), connectionProvider.getPassword());
                CollectionManagementService managementService =
                        (CollectionManagementService) parentCollection.getService("CollectionManagementService", "1.0");
                collection = managementService.createCollection(pathSegments[pathSegmentOffset]);
                collection.close();
                parentCollection.close();
            } else {
                startCollection.close();
            }
        }
        return getOrCreateCollection(collectionUri, ++pathSegmentOffset);
    }
}

