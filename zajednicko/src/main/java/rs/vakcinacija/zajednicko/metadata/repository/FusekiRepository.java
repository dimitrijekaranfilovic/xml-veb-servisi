package rs.vakcinacija.zajednicko.metadata.repository;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.web.HttpOp;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.xml.sax.SAXException;
import rs.vakcinacija.zajednicko.data.context.JAXBEntityManager;
import rs.vakcinacija.zajednicko.metadata.MetadataExtractor;
import rs.vakcinacija.zajednicko.metadata.SparqlUtil;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.UUID;

public abstract class FusekiRepository<T> {
    protected final FusekiConnectionProvider connectionProvider;
    protected final JAXBEntityManager<T> entityManager;
    protected final String collectionName;
    protected final Class<T> entityClazz;
    protected final String baseUrl = "https://www.vakcinacija.rs/";

    public FusekiRepository(String collectionName, Class<T> entityClazz, FusekiConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.entityClazz = entityClazz;
        this.collectionName = collectionName;
        this.entityManager = JAXBEntityManager.forEntity(this.entityClazz);
        this.configureSecureFusekiConnection();
    }

    public void save(UUID id, T entity) throws IOException, SAXException, TransformerException, JAXBException {
        var metadataOutputStream = extractMetadata(entity);
        var model = ModelFactory.createDefaultModel();
        model.read(new ByteArrayInputStream(metadataOutputStream.toByteArray()), buildModelDataUrl(id));
        var fusekiOutputStream = new ByteArrayOutputStream();
        model.write(fusekiOutputStream, SparqlUtil.NTRIPLES);
        var sparqlUpdate = SparqlUtil.insertData(buildSparqlUrl(), fusekiOutputStream.toString());
        var update = UpdateFactory.create(sparqlUpdate);
        var processor = UpdateExecutionFactory.createRemote(update, connectionProvider.getUpdateEndpoint());
        processor.execute();
    }

    protected String buildSparqlUrl() {
        return String.format("%s/vakcinacija/%s/metadata", connectionProvider.getDataEndpoint(), collectionName);
    }

    protected String buildModelDataUrl(UUID id) {
        return String.format("%s/%s/%s", baseUrl, collectionName, id);
    }

    protected ByteArrayOutputStream extractMetadata(T entity) throws JAXBException, IOException, TransformerException, SAXException {
        MetadataExtractor metadataExtractor = new MetadataExtractor();
        ByteArrayOutputStream marshallOutputStream = new ByteArrayOutputStream();
        entityManager.marshall(entity, marshallOutputStream);
        ByteArrayOutputStream metadataOutputStream = new ByteArrayOutputStream();
        metadataExtractor.extractMetadata(new ByteArrayInputStream(marshallOutputStream.toByteArray()), metadataOutputStream);
        return metadataOutputStream;
    }

    private void configureSecureFusekiConnection() {
        var credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(connectionProvider.getUsername(), connectionProvider.getPassword()));
        var httpclient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpOp.setDefaultHttpClient(httpclient);
    }
}
