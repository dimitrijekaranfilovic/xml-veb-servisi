package rs.vakcinacija.zajednicko.data.context;


import org.xmldb.api.base.Collection;
import org.xmldb.api.base.XMLDBException;

public class ManagedCollectionAdapter extends ManagedAdapterBase<Collection> implements AutoCloseable {

    public ManagedCollectionAdapter(Collection resource) {
        super(resource);
    }

    @Override
    public void close() {
        log.debug("Closing Collection resource");
        if (resource != null) {
            try {
                resource.close();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
