package rs.vakcinacija.zajednicko.util;

import java.lang.reflect.Field;

public class ReflectionUtils {
    @SuppressWarnings({"unchecked", "unused"})
    public static <S> S getFieldValue(Object entity, Field field, Class<S> fieldType) {
        var methodName = "get" + capitalize(field);
        try {
            var method = field.getDeclaringClass().getDeclaredMethod(methodName);
            return (S) method.invoke(entity);
        } catch (Exception exception) {
            System.out.println("GOT ERROR ON FIELD: " + field.getName());
            System.out.println(exception.getMessage());
            exception.printStackTrace();
            return null;
        }
    }

    public static String capitalize(Field field) {
        var str = field.getName();
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
