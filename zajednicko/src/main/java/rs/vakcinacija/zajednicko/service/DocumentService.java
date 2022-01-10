package rs.vakcinacija.zajednicko.service;


import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

import java.util.UUID;

public abstract class DocumentService<T> {

    protected final ExistRepository<T> existRepository;
    protected final FusekiRepository<T> fusekiRepository;

    protected static final String VOCAB = "https://www.vakcinacija.rs/rdf/predicate/";
    protected static final String RDF_LEKAR_BASE = "https://www.vakcinacija.rs/rdf/lekar/";
    protected static final String RDF_PACIJENT_BASE = "https://www.vakcinacija.rs/rdf/pacijent/";

    protected static final String PROP_DATUM_IZDAVANJA = "pred:datum_izdavanja";
    protected static final String PROP_JMBG = "pred:jmbg";
    protected static final String PROP_BROJ_FIKSNOG = "pred:broj_fiksnog";
    protected static final String PROP_BROJ_MOBILNOG = "pred:broj_mobilnog";
    protected static final String PROP_EMAIL = "pred:email";
    protected static final String PROP_IME = "pred:ime";
    protected static final String PROP_PREZIME = "pred:prezime";
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

    protected DocumentService(ExistRepository<T> existRepository, FusekiRepository<T> fusekiRepository) {
        this.existRepository = existRepository;
        this.fusekiRepository = fusekiRepository;
    }

    public abstract T create(T entity) throws Exception;

    public abstract T read(UUID id) throws Exception;

}
