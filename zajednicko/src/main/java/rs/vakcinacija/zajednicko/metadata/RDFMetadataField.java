package rs.vakcinacija.zajednicko.metadata;

import rs.vakcinacija.zajednicko.metadata.builder.RDFMetadataBuilder;

public interface RDFMetadataField {
    void setProperty(String property);

    void setDatatype(String datatype);

    void setAbout(String about);

    void setHref(String href);

    void setRel(String rel);

    void setTypeof(String typeof);

    void setVocab(String vocab);

    RDFMetadataBuilder rdf();
}
