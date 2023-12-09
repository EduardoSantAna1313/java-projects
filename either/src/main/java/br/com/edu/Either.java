package br.com.edu;

import java.util.Objects;
import java.util.function.Supplier;

public record Either <F, S> (F fail, S success) {

    public static <F,S> Either<F,S> success(final S success) {
        Objects.requireNonNull(success);
        return new Either<>(null, success);
    }

    public static <F,S> Either<F,S> success(final Supplier<S> success) {
        Objects.requireNonNull(success);
        Objects.requireNonNull(success.get());
        return new Either<>(null, success.get());
    }

    public static <F,S> Either<F,S> fail(final F fail) {
        Objects.requireNonNull(fail);
        return new Either<>(fail, null);
    }

    public static <F,S> Either<F,S> fail(final Supplier<F> fail) {
        Objects.requireNonNull(fail);
        Objects.requireNonNull(fail.get());
        return new Either<>(fail.get(), null);
    }

}