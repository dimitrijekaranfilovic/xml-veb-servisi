package rs.vakcinacija.zajednicko.metadata.query;

import rs.vakcinacija.zajednicko.util.ReflectionUtils;

import java.util.Arrays;

public class SparqlWhereQueryBuilder {

    public static <T> String whereQueryForRequest(T request) {
        StringBuilder stringBuilder = new StringBuilder(10000);
        Arrays.stream(request.getClass().getDeclaredFields()).forEach(field -> {
            if (!field.isAnnotationPresent(SparqlParameter.class)) {
                return;
            }
            var annotation = field.getAnnotation(SparqlParameter.class);
            var fieldValue = ReflectionUtils.getFieldValue(request, field, field.getType());
            if (fieldValue == null) {
                return;
            }
            stringBuilder.append(String.format("pred:%s \"%s\"^^xsd:%s", annotation.name(), fieldValue, annotation.type()));
        });
        return stringBuilder.toString();
    }
}
