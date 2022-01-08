package rs.vakcinacija.zajednicko.model;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PropertyXMLFieldAdapter extends XmlAdapter<Object, PropertyXMLField<?>> {
    @Override
    public PropertyXMLField<?> unmarshal(Object v) throws Exception {
        if (v == null) {
            return null;
        }
        PropertyXMLField<Object> field = new PropertyXMLField<>();
        field.setValue(v);
        return field;
    }

    @Override
    public Object marshal(PropertyXMLField<?> v) throws Exception {
        if (v == null) {
            return null;
        }
        return v.getValue();
    }
}
