package rs.vakcinacija.imunizacija.saglasnost.repository;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
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

import javax.xml.transform.TransformerException;
import java.io.*;

@Component
public class SaglasnostRepository extends ExistRepository<SaglasnostZaSprovodjenjeImunizacije> {

    @Autowired
    public SaglasnostRepository(ConnectionProvider connectionProvider) {
        super("saglasnost", SaglasnostZaSprovodjenjeImunizacije.class, connectionProvider);
    }

    public void run() throws IOException, SAXException, TransformerException {

        // Referencing XML file with RDF data in attributes
        String xmlFilePath = "src/main/resources/static/primer.xml";

        String rdfFilePath = "src/main/resources/static/primer.rdf";

        // Automatic extraction of RDF triples from XML file
        MetadataExtractor metadataExtractor = new MetadataExtractor();

        System.out.println("[INFO] Extracting metadata from RDFa attributes...");
        metadataExtractor.extractMetadata(
                new FileInputStream(new File(xmlFilePath)),
                new FileOutputStream(new File(rdfFilePath)));



        // Loading a default model with extracted metadata
        Model model = ModelFactory.createDefaultModel();
        model.read(rdfFilePath);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        model.write(out, SparqlUtil.NTRIPLES);

        System.out.println("[INFO] Extracted metadata as RDF/XML...");
        model.write(System.out, SparqlUtil.RDF_XML);

        String updateEndpoint = "http://localhost:8083/fuseki/VakcinacijaDataset/update";
        String dataEndpoint = "http://localhost:8083/fuseki/VakcinacijaDataset/data";
        String queryEndpoint = "http://localhost:8083/fuseki/VakcinacijaDataset/query";

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
