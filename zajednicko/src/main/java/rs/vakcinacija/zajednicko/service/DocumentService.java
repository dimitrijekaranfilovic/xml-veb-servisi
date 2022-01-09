package rs.vakcinacija.zajednicko.service;


import rs.vakcinacija.zajednicko.data.repository.ExistRepository;
import rs.vakcinacija.zajednicko.metadata.repository.FusekiRepository;

import java.util.UUID;

public abstract class DocumentService<T> {

    protected final ExistRepository<T> existRepository;
    protected final FusekiRepository<T> fusekiRepository;

    protected DocumentService(ExistRepository<T> existRepository, FusekiRepository<T> fusekiRepository) {
        this.existRepository = existRepository;
        this.fusekiRepository = fusekiRepository;
    }

    public abstract T create(T entity) throws Exception;

    public abstract T read(UUID id) throws Exception;

}
