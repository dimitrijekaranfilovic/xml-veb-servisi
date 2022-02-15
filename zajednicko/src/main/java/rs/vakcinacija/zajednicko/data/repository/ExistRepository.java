package rs.vakcinacija.zajednicko.data.repository;

import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.CollectionManagementService;
import org.xmldb.api.modules.XMLResource;
import org.xmldb.api.modules.XPathQueryService;
import rs.vakcinacija.zajednicko.data.connection.ExistConnectionProvider;
import rs.vakcinacija.zajednicko.data.context.JAXBEntityManager;
import rs.vakcinacija.zajednicko.data.context.ManagedCollectionAdapter;
import rs.vakcinacija.zajednicko.data.context.ManagedXMLResourceAdapter;
import rs.vakcinacija.zajednicko.data.query.FieldLevelQueryPredicate;
import rs.vakcinacija.zajednicko.model.BaseDocument;

import javax.xml.transform.OutputKeys;
import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.function.Predicate;

public abstract class ExistRepository<T extends BaseDocument> {
    protected final JAXBEntityManager<T> entityManager;
    protected final String collectionId;
    protected final Class<T> entityClazz;
    protected final ExistConnectionProvider connectionProvider;

    protected ExistRepository(String collectionName, Class<T> clazz, ExistConnectionProvider connectionProvider) {
        this.collectionId = "/db/sample/xml/" + collectionName;
        this.entityClazz = clazz;
        this.connectionProvider = connectionProvider;
        this.entityManager = JAXBEntityManager.forEntity(clazz);
    }

    public List<T> read() throws Exception {
        registerDatabase();
        List<T> result = new ArrayList<>();
        try (var collection = new ManagedCollectionAdapter(getOrCreateCollection(collectionId))) {
            collection.get().setProperty(OutputKeys.INDENT, "yes");
            for (var resourceId: collection.get().listResources()) {
                try (var xmlResource = new ManagedXMLResourceAdapter((XMLResource) collection.get().getResource(resourceId))) {
                    if (xmlResource.hasResource()) {
                        result.add(entityManager.unmarshall(xmlResource.get().getContentAsDOM()));
                    }
                }
            }
        }
        return result;
    }

    public List<T> read(String query) throws Exception {
        return read(FieldLevelQueryPredicate.forDocument(entityClazz, query));
    }

    public List<T> read(Predicate<T> criteria) throws Exception {
        registerDatabase();
        List<T> result = new ArrayList<>();
        try (var collection = new ManagedCollectionAdapter(getOrCreateCollection(collectionId))) {
            collection.get().setProperty(OutputKeys.INDENT, "yes");
            for (var resourceId: collection.get().listResources()) {
                try (var xmlResource = new ManagedXMLResourceAdapter((XMLResource) collection.get().getResource(resourceId))) {
                    if (xmlResource.hasResource()) {
                        var entity = entityManager.unmarshall(xmlResource.get().getContentAsDOM());
                        if (criteria.test(entity)) {
                            result.add(entity);
                        }
                    }
                }
            }
        }
        return result;
    }

    public UUID save(T entity) throws Exception {
        var id = Objects.requireNonNullElse(entity.getId(), UUID.randomUUID());
        var documentId = buildDocumentId(id);
        entity.setId(id);
        registerDatabase();
        try (var collection = new ManagedCollectionAdapter(getOrCreateCollection(collectionId));
             var xmlResource = new ManagedXMLResourceAdapter((XMLResource) collection.get().createResource(documentId, XMLResource.RESOURCE_TYPE));
             var outputStream = new ByteArrayOutputStream()) {
            entityManager.marshall(entity, outputStream);
            xmlResource.get().setContent(outputStream);
            collection.get().storeResource(xmlResource.get());
        }
        return entity.getId();
    }

    public ResourceSet runXPathQuery(String query) throws Exception {
        registerDatabase();
        try (var collection = new ManagedCollectionAdapter(getOrCreateCollection(collectionId))) {
            XPathQueryService xpathService = (XPathQueryService) collection.get().getService("XPathQueryService", "1.0");
            xpathService.setProperty("indent", "yes");

            return xpathService.query(query);
        }
    }

    public Optional<T> read(UUID id) throws Exception {
        String documentId = buildDocumentId(id);
        registerDatabase();
        try (var collection = new ManagedCollectionAdapter(getOrCreateCollection(collectionId));
             var xmlResource = new ManagedXMLResourceAdapter((XMLResource) collection.get().getResource(documentId))) {
            collection.get().setProperty(OutputKeys.INDENT, "yes");
            if (!xmlResource.hasResource()) {
                return Optional.empty();
            }
            return Optional.of(entityManager.unmarshall(xmlResource.get().getContentAsDOM()));
        }
    }

    protected String buildDocumentId(UUID id) {
        return String.format("%s.xml", id);
    }

    protected void registerDatabase() throws Exception {
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
        if (collectionUri.startsWith("/")) {
            collectionUri = collectionUri.substring(1);
        }
        String[] pathSegments = collectionUri.split("/");
        if (pathSegments.length > 0) {
            StringBuilder path = new StringBuilder();
            for (int i = 0; i <= pathSegmentOffset; i++) {
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

    public JAXBEntityManager<T> getEntityManager() {
        return entityManager;
    }
}

