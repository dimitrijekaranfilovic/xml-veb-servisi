package rs.vakcinacija.zajednicko.data.query;

import lombok.NonNull;
import rs.vakcinacija.zajednicko.model.BaseDocument;

import java.util.function.Predicate;

public class CitizenSpecificDocumentQueryPredidcate<T extends BaseDocument> implements Predicate<T> {
    private final Class<T> clazz;
    private final String email;

    private CitizenSpecificDocumentQueryPredidcate(Class<T> clazz, String email) {
        this.clazz = clazz;
        this.email = email;
    }

    public static <S extends BaseDocument> CitizenSpecificDocumentQueryPredidcate<S> forDocument(Class<S> clazz, String email) {
        return new CitizenSpecificDocumentQueryPredidcate<>(clazz, email);
    }

    @Override
    public boolean test(T entity) {
        return entity.provideEmail().equals(email);
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
