package rs.vakcinacija.zajednicko.metadata.builder;

import rs.vakcinacija.zajednicko.metadata.RDFMetadataField;

public class RDFMetadataBuilder {
    private final RDFMetadataField rdfMetadataField;

    private RDFMetadataBuilder(RDFMetadataField rdfMetadataField) {
        this.rdfMetadataField = rdfMetadataField;
    }

    public static RDFMetadataBuilder of(RDFMetadataField rdfMetadataField) {
        return new RDFMetadataBuilder(rdfMetadataField);
    }

    public RDFMetadataBuilder property(String property) {
        rdfMetadataField.setProperty(property);
        return this;
    }

    public RDFMetadataBuilder datatype(String datatype) {
        rdfMetadataField.setDatatype(datatype);
        return this;
    }

    public RDFMetadataBuilder about(String about) {
        rdfMetadataField.setAbout(about);
        return this;
    }

    public RDFMetadataBuilder vocab(String vocab) {
        rdfMetadataField.setVocab(vocab);
        return this;
    }

    public RDFMetadataBuilder href(String href) {
        rdfMetadataField.setHref(href);
        return this;
    }

    public RDFMetadataBuilder rel(String rel) {
        rdfMetadataField.setRel(rel);
        return this;
    }

    public RDFMetadataBuilder typeof(String typeof) {
        rdfMetadataField.setTypeof(typeof);
        return this;
    }
}
