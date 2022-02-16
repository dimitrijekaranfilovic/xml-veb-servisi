package rs.vakcinacija.zajednicko.service;


import net.sf.saxon.TransformerFactoryImpl;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.MimeConstants;
import org.w3c.dom.Document;
import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.metadata.repository.SparqlQueryResult;
import rs.vakcinacija.zajednicko.model.BaseDocument;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.fop.apps.FopFactory;


public abstract class DocumentService<T extends BaseDocument> {
    protected final ExistRepository<T> existRepository;
    protected final FusekiRepository<T> fusekiRepository;

    private DocumentBuilderFactory documentFactory;
    private TransformerFactory transformerFactory;
    private TransformerFactory transformerFopFactory;
    private FopFactory fopFactory;

    protected DocumentService(ExistRepository<T> existRepository, FusekiRepository<T> fusekiRepository) {
        this.existRepository = existRepository;
        this.fusekiRepository = fusekiRepository;

        documentFactory = DocumentBuilderFactory.newInstance();
        documentFactory.setNamespaceAware(true);
        documentFactory.setIgnoringComments(true);
        documentFactory.setIgnoringElementContentWhitespace(true);

        transformerFactory = TransformerFactory.newInstance();
        try {
            fopFactory = FopFactory.newInstance(new File("src/main/resources/fop.xconf"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        transformerFopFactory = new TransformerFactoryImpl();
    }

    public T create(T entity) throws Exception {
        insertRDFMetadata(entity);
        var id = existRepository.save(entity);
        fusekiRepository.save(id, entity);
        return entity;
    }

    public T update(T entity) throws Exception {
        return create(entity);
    }

    public T read(UUID id) throws Exception {
        return existRepository.read(id)
                .orElseThrow(() -> new DocumentNotFoundException(String.format("Ne postoji dokument sa id-ijem: '%s'.", id)));
    }

    public List<T> readForCitizen(String email, String query) throws Exception {
        return existRepository.read(email, query);
    }

    public <S> List<T> readForCitizen(String query, S request) {
        var sparqlResult = fusekiRepository.sparql(request);
        List<T> results = new ArrayList<>();
        var queryPredicate = existRepository.getSearchFilter(query);
        sparqlResult.stream()
                    .map(SparqlQueryResult::getValue)
                    .map(this::extractUUID)
                    .forEach(uuid -> {
                        try {
                            var entity = read(uuid);
                            if (queryPredicate.test(entity)) {
                                results.add(entity);
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    });
        return results;
    }

    public List<T> read(String query) throws Exception {
        return existRepository.read(query);
    }

    public List<T> read() throws Exception {
        return existRepository.read();
    }

    private UUID extractUUID(String identifier) {
        return UUID.fromString(identifier.substring(identifier.lastIndexOf("/") + 1));
    }

    protected abstract void insertRDFMetadata(T entity);

    protected static final String VOCAB = "https://www.vakcinacija.rs/rdf/predicate/";
    protected static final String RDF_LEKAR_BASE = "https://www.vakcinacija.rs/rdf/lekar/";
    protected static final String RDF_PACIJENT_BASE = "https://www.vakcinacija.rs/rdf/pacijent/";
    protected static final String RDF_VAKCINA_BASE = "https://www.vakcinacija.rs/rdf/vakcina/";
    protected static final String RDF_INTERESOVANJE_BASE = "https://www.vakcinacija.rs/rdf/interesovanje/";

    protected static final String PROP_DATUM_IZDAVANJA = "pred:datum_izdavanja";
    protected static final String PROP_JMBG = "pred:jmbg";
    protected static final String PROP_BROJ_FIKSNOG = "pred:broj_fiksnog";
    protected static final String PROP_BROJ_MOBILNOG = "pred:broj_mobilnog";
    protected static final String PROP_EMAIL = "pred:email";
    protected static final String PROP_IME = "pred:ime";
    protected static final String PROP_PREZIME = "pred:prezime";
    protected static final String PROP_POL = "pred:pol";
    protected static final String PROP_IME_RODITELJA = "pred:ime_roditelja";
    protected static final String PROP_DATUM = "pred:datum";
    protected static final String PROP_BROJ_PASOSA = "pred:broj_pasosa";
    protected static final String PROP_MESTO = "pred:mesto";
    protected static final String PROP_QR_KOD = "pred:qr_kod";
    protected static final String PROP_SIFRA_DOKUMENTA = "pred:sifra_dokumenta";
    protected static final String PROP_DAVALAC_KRVI = "pred:davalac_krvi";
    protected static final String PROP_PODNESEN_OD = "pred:podnesenOd";
    protected static final String PROP_DATUM_RODJENJA = "pred:datum_rodjenja";
    protected static final String PROP_STATUS = "pred:status";

    protected static final String T_STRING = "xs:string";
    protected static final String T_DATE = "xs:date";
    protected static final String T_BOOLEAN = "xs:boolean";
    protected static final String T_INTERESOVANJE = "pred:Interesovanje";

    public Document buildDocument(T entity) throws IOException, JAXBException {
        ByteArrayOutputStream documentOutputStream = new ByteArrayOutputStream();
        existRepository.getEntityManager().marshall(entity, documentOutputStream);
        InputStream documentInputStream = new ByteArrayInputStream(documentOutputStream.toByteArray());
        Document document = null;
        try {
            DocumentBuilder builder = documentFactory.newDocumentBuilder();
            document = builder.parse(documentInputStream);

            if (document != null)
                System.out.println("[INFO] File parsed with no errors.");
            else
                System.out.println("[WARN] Document is null.");

        } catch (Exception e) {
            return null;
        }
        return document;
    }

    public String generateHTML(T xmlDocument, String xslPath) throws FileNotFoundException {

        try {
            // Initialize Transformer instance
            StreamSource transformSource = new StreamSource(new File(xslPath));
            Transformer transformer = transformerFactory.newTransformer(transformSource);
            transformer.setOutputProperty("{http://xml.apache.org/xalan}indent-amount", "2");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // Generate XHTML
            transformer.setOutputProperty(OutputKeys.METHOD, "xhtml");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");

            // Transform DOM to HTML
            DOMSource source = new DOMSource(buildDocument(xmlDocument));
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);
            return writer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ByteArrayInputStream generatePDF(T entity, String xslfoPath) throws Exception {
        File xslFile = new File(xslfoPath);

        // Create transformation source
        StreamSource transformSource = new StreamSource(xslFile);

        // Initialize the transformation subject
        ByteArrayOutputStream documentOutputStream = new ByteArrayOutputStream();
        existRepository.getEntityManager().marshall(entity, documentOutputStream);
        StreamSource source = new StreamSource(new StringReader(String.valueOf(documentOutputStream)));

        // Initialize user agent needed for the transformation
        FOUserAgent userAgent = fopFactory.newFOUserAgent();

        // Create the output stream to store the results
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();

        // Initialize the XSL-FO transformer object
        Transformer xslFoTransformer = transformerFopFactory.newTransformer(transformSource);

        // Construct FOP instance with desired output format
        Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, userAgent, outStream);

        // Resulting SAX events
        Result res = new SAXResult(fop.getDefaultHandler());

        // Start XSLT transformation and FOP processing
        xslFoTransformer.transform(source, res);

        return new ByteArrayInputStream(outStream.toByteArray());
    }
}
