package rs.vakcinacija.zajednicko.data.query;

import lombok.NonNull;
import rs.vakcinacija.zajednicko.model.*;
import rs.vakcinacija.zajednicko.util.ReflectionUtils;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class FieldLevelQueryPredicate<T extends BaseDocument> implements Predicate<T> {
    private final Class<T> clazz;
    private final String query;

    private FieldLevelQueryPredicate(Class<T> clazz, String query) {
        this.clazz = clazz;
        this.query = query.toLowerCase();
    }

    public static <S extends BaseDocument> FieldLevelQueryPredicate<S> forDocument(Class<S> clazz, String query) {
        return new FieldLevelQueryPredicate<>(clazz, query);
    }

    @Override
    public boolean test(T entity) {
        return getFields(clazz).anyMatch(field -> matches(entity, field));
    }

    private boolean matches(Object entity, Field field) {
        if (!field.isAnnotationPresent(XmlElement.class)) {
            return false;
        }
        if (field.getType().isAssignableFrom(String.class)) {
            var string = ReflectionUtils.getFieldValue(entity, field, String.class);
            return string != null && string.toLowerCase().contains(query);
        }
        if (field.getType().isAssignableFrom(Integer.class)) {
            var integer = ReflectionUtils.getFieldValue(entity, field, Integer.class);
            return integer != null && integer.toString().contains(query);
        }
        if (field.getType().isAssignableFrom(Boolean.class)) {
            var b = ReflectionUtils.getFieldValue(entity, field, Boolean.class);
            return b != null && b.toString().toLowerCase().contains(query);
        }
        if (field.getType().isAssignableFrom(UUID.class)) {
            var uuid = ReflectionUtils.getFieldValue(entity, field, UUID.class);
            return uuid != null && uuid.toString().contains(query);
        }
        if (field.getType().isAssignableFrom(RDFString.class)) {
            var rdfString = ReflectionUtils.getFieldValue(entity, field, RDFString.class);
            return rdfString != null && rdfString.getValue() != null && rdfString.getValue().toLowerCase().contains(query);
        }
        if (field.getType().isAssignableFrom(RDFDate.class)) {
            var rdfDate = ReflectionUtils.getFieldValue(entity, field, RDFDate.class);
            Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return rdfDate != null && rdfDate.getValue() != null && formatter.format(rdfDate.getValue()).contains(query);
        }
        if (field.getType().isAssignableFrom(RDFInteger.class)) {
            var rdfInteger = ReflectionUtils.getFieldValue(entity, field, RDFInteger.class);
            return rdfInteger != null && rdfInteger.getValue() != null && rdfInteger.getValue().toString().toLowerCase().contains(query);
        }
        if (field.getType().isAssignableFrom(RDFUUID.class)) {
            var rdfUuid = ReflectionUtils.getFieldValue(entity, field, RDFUUID.class);
            return rdfUuid != null && rdfUuid.getValue() != null && rdfUuid.getValue().toString().contains(query);
        }
        if (field.getType().isAssignableFrom(RDFBoolean.class)) {
            var rdfBoolean = ReflectionUtils.getFieldValue(entity, field, RDFBoolean.class);
            return rdfBoolean != null && rdfBoolean.getValue() != null && rdfBoolean.getValue().toString().toLowerCase().contains(query);
        }
        var subEntity = ReflectionUtils.getFieldValue(entity, field, field.getType());
        return getFields(field.getType()).anyMatch(subField -> matches(subEntity, subField));
    }

    private Stream<Field> getFields(Class<?> c) {
        if (c == null) {
            return Stream.empty();
        }
        var superClassFields = getFields(c.getSuperclass());
        var ownFields = Arrays.stream(c.getDeclaredFields());
        return Stream.concat(superClassFields, ownFields);
    }

    @Override
    public Predicate<T> and(@NonNull Predicate<? super T> other) {
        return Predicate.super.and(other);
    }

    @Override
    public Predicate<T> negate() {
        return Predicate.super.negate();
    }

    @Override
    public Predicate<T> or(@NonNull Predicate<? super T> other) {
        return Predicate.super.or(other);
    }
}
