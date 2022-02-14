package rs.vakcinacija.zajednicko.model;

import java.util.UUID;

public class ReferenceBuilder {
    private final Reference reference;
    private RDFUUID rdfuuid;

    private ReferenceBuilder(Reference reference, String name) {
        this.reference = reference;
        this.rdfuuid = new RDFUUID();
        this.rdfuuid.setProperty(name);
        this.rdfuuid.setDatatype("xs:string");
    }

    public static ReferenceBuilder of(Reference reference, String name) {
        return new ReferenceBuilder(reference, name);
    }

    public ReferenceBuilder uuid(UUID id) {
        rdfuuid.setValue(id);
        return this;
    }

    public <T extends BaseDocument> ReferenceBuilder entity(T doc) {
        rdfuuid.setValue(doc.getId());
        return this;
    }

    public <T extends BaseDocument> ReferenceBuilder type(Class<T> clazz) {
        rdfuuid.setTypeof(clazz.getSimpleName());
        return this;
    }

    public ReferenceBuilder type(String type) {
        rdfuuid.setTypeof(type);
        return this;
    }

    public void configure() {
        reference.getReference().add(rdfuuid);
    }
}
