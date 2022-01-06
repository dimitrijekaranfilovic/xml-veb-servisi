package rs.vakcinacija.zajednicko.data.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ManagedAdapterBase<T> {
    protected final T resource;
    protected final Logger log = LoggerFactory.getLogger(ManagedAdapterBase.class);

    protected ManagedAdapterBase(T resource) {
        this.resource = resource;
    }

    public T get() {
        return resource;
    }

    public boolean hasResource() {
        return resource != null;
    }
}
