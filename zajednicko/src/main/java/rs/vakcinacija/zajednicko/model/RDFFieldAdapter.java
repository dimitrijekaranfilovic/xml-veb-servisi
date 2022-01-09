package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class RDFFieldAdapter extends XmlAdapter<Object, RDFField<?>> {

    @Override
    public RDFField<?> unmarshal(Object v) throws Exception {
        if (v == null) {
            return null;
        }
        RDFField<Object> field = new RDFField<>();
        field.setValue(v);
        return field;
    }

    @Override
    public Object marshal(RDFField<?> v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.getValue();
    }
}
