package rs.vakcinacija.zajednicko.metadata.repository;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import rs.vakcinacija.zajednicko.data.context.JAXBEntityManager;
import rs.vakcinacija.zajednicko.metadata.MetadataExtractor;
import rs.vakcinacija.zajednicko.metadata.SparqlUtil;
import rs.vakcinacija.zajednicko.metadata.connection.FusekiConnectionProvider;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public abstract class FusekiRepository<T> {
    protected final FusekiConnectionProvider connectionProvider;
    protected final JAXBEntityManager<T> entityManager;
    protected final String collectionName;
    protected final Class<T> entityClazz;
    protected final String baseUrl = "https://www.vakcinacija.rs";

    public FusekiRepository(String collectionName, Class<T> entityClazz, FusekiConnectionProvider connectionProvider) {
        this.connectionProvider = connectionProvider;
        this.entityClazz = entityClazz;
        this.collectionName = collectionName;
        this.entityManager = JAXBEntityManager.forEntity(this.entityClazz);
    }

    public void save(UUID id, T entity) throws TransformerException, JAXBException {
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

    public List<SparqlQueryResult<String>> sparql(String sparqlQuery) {
        QueryExecution query = QueryExecutionFactory.sparqlService(connectionProvider.getQueryEndpoint(), sparqlQuery);
        ResultSet results = query.execSelect();
        String varName;
        RDFNode varValue;
        var resultList = new ArrayList<SparqlQueryResult<String>>();
        while (results.hasNext()) {
            QuerySolution querySolution = results.next();
            Iterator<String> variableBindings = querySolution.varNames();
            while (variableBindings.hasNext()) {
                varName = variableBindings.next();
                varValue = querySolution.get(varName);
                resultList.add(new SparqlQueryResult<>(varName, varValue.toString()));
            }
        }
        query.close();
        return resultList;
    }

    protected String buildSparqlUrl() {
        return String.format("%s/vakcinacija/%s/metadata", connectionProvider.getDataEndpoint(), collectionName);
    }

    protected String buildSparqlQueryUrl() {
        return String.format("%s/vakcinacija/%s/metadata", connectionProvider.getQueryEndpoint(), collectionName);
    }

    protected String buildModelDataUrl(UUID id) {
        return String.format("%s/%s/%s", baseUrl, collectionName, id);
    }

    protected ByteArrayOutputStream extractMetadata(T entity) throws JAXBException, TransformerException {
        MetadataExtractor metadataExtractor = new MetadataExtractor();
        ByteArrayOutputStream marshallOutputStream = new ByteArrayOutputStream();
        entityManager.marshall(entity, marshallOutputStream);
        ByteArrayOutputStream metadataOutputStream = new ByteArrayOutputStream();
        metadataExtractor.extractMetadata(new ByteArrayInputStream(marshallOutputStream.toByteArray()), metadataOutputStream);
        return metadataOutputStream;
    }
}
