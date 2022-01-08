package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClients;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.web.HttpOp;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.update.UpdateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;
import rs.vakcinacija.imunizacija.saglasnost.model.SaglasnostZaSprovodjenjeImunizacije;
import rs.vakcinacija.zajednicko.data.connection.ConnectionProvider;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.MetadataExtractor;
import rs.vakcinacija.zajednicko.metadata.SparqlUtil;

import javax.xml.bind.JAXBException;
import javax.xml.transform.TransformerException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Component
public class SaglasnostRepository extends ExistRepository<SaglasnostZaSprovodjenjeImunizacije> {

    @Autowired
    public SaglasnostRepository(ConnectionProvider connectionProvider) {
        super("saglasnost", SaglasnostZaSprovodjenjeImunizacije.class, connectionProvider);
        var credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("admin", "password"));
        var httpclient = HttpClients.custom().setDefaultCredentialsProvider(credentialsProvider).build();
        HttpOp.setDefaultHttpClient(httpclient);
    }

    public void run(SaglasnostZaSprovodjenjeImunizacije saglasnost) throws IOException, SAXException, TransformerException, JAXBException {

        // Automatic extraction of RDF triples from XML file
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        entityManager.marshall(saglasnost, os);
        ByteArrayOutputStream os1 = new ByteArrayOutputStream();

        System.out.println("[INFO] Extracting metadata from RDFa attributes...");
        metadataExtractor.extractMetadata(
                new ByteArrayInputStream(os.toByteArray()),
                os1);

        // Loading a default model with extracted metadata
        Model model = ModelFactory.createDefaultModel();
//        model.read(rdfFilePath);
        System.out.println("======================================");
        System.out.println(os1.toString());
        System.out.println("======================================");
        model.read(new ByteArrayInputStream(os1.toByteArray()), "AAAAA");

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Extracted metadata as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        String updateEndpoint = "http://localhost:8085/VakcinacijaDataset/update";
        String dataEndpoint = "http://localhost:8085/VakcinacijaDataset/data";
        String queryEndpoint = "http://localhost:8085/VakcinacijaDataset/query";

        // Writing the named graph
        System.out.println("[INFO] Populating named graph \"" + "/vakcinacija/saglasnost/metadata" + "\" with extracted metadata.");
        String sparqlUpdate = SparqlUtil.insertData(dataEndpoint + "/vakcinacija/saglasnost/metadata", new String(out.toByteArray()));
        System.out.println(sparqlUpdate);

        // UpdateRequest represents a unit of execution
        UpdateRequest update = UpdateFactory.create(sparqlUpdate);

        UpdateProcessor processor = UpdateExecutionFactory.createRemote(update, updateEndpoint);
        processor.execute();



        // Read the triples from the named graph
        String sparqlQuery = SparqlUtil.selectData(dataEndpoint + "/vakcinacija/saglasnost/metadata", "?s ?p ?o");

        // Create a QueryExecution that will access a SPARQL service over HTTP
        QueryExecution query = QueryExecutionFactory.sparqlService(queryEndpoint, sparqlQuery);


        // Query the collection, dump output response as XML
        ResultSet results = query.execSelect();

        ResultSetFormatter.out(System.out, results);

        query.close() ;

        System.out.println("[INFO] End.");
    }
}
