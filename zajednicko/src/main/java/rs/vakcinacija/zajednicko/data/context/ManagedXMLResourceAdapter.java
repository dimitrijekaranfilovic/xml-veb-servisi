package rs.vakcinacija.zajednicko.data.context;

import org.exist.xmldb.EXistResource;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

public class ManagedXMLResourceAdapter extends ManagedAdapterBase<XMLResource> implements AutoCloseable {

    public ManagedXMLResourceAdapter(XMLResource resource) {
        super(resource);
    }

    @Override
    public void close() {
        log.debug("Closing XMLResource resource");
        if (resource != null) {
            try {
                ((EXistResource) resource).freeResources();
            } catch (XMLDBException xe) {
                xe.printStackTrace();
            }
        }
    }
}
