package rs.vakcinacija.zajednicko.service;


import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.exception.DocumentNotFoundException;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;
import rs.vakcinacija.zajednicko.model.BaseDocument;

import java.util.List;
import java.util.UUID;

public abstract class DocumentService<T extends BaseDocument> {
    protected final ExistRepository<T> existRepository;
    protected final FusekiRepository<T> fusekiRepository;

    protected DocumentService(ExistRepository<T> existRepository, FusekiRepository<T> fusekiRepository) {
        this.existRepository = existRepository;
        this.fusekiRepository = fusekiRepository;
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
                .orElseThrow(() -> new DocumentNotFoundException(String.format("Cannot find document with id: '%s'.", id)));
    }

    public List<T> read() throws Exception {
        return existRepository.read();
    }

    protected abstract void insertRDFMetadata(T entity);

    protected static final String VOCAB = "https://www.vakcinacija.rs/rdf/predicate/";
    protected static final String RDF_LEKAR_BASE = "https://www.vakcinacija.rs/rdf/lekar/";
    protected static final String RDF_PACIJENT_BASE = "https://www.vakcinacija.rs/rdf/pacijent/";
    protected static final String RDF_VAKCINA_BASE = "https://www.vakcinacija.rs/rdf/vakcina/";

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

    protected static final String T_STRING = "xs:string";
    protected static final String T_DATE = "xs:date";
    protected static final String T_BOOLEAN = "xs:boolean";
}
