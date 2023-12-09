package br.com.edu;

import java.util.function.Supplier;

public record Either <F, S> (F fail, S success) {

    public static <F,S> Either<F,S> success(final S success) {
        return new Either<>(null, success);
    }

    public static <F,S> Either<F,S> success(final Supplier<S> success) {
        return new Either<>(null, success.get());
    }

    public static <F,S> Either<F,S> fail(final F fail) {
        return new Either<>(fail, null);
    }

    public static <F,S> Either<F,S> fail(final Supplier<F> fail) {
        return new Either<>(fail.get(), null);
    }

    public static <F extends Exception,S> Either<F,S> catches(final Supplier<S> success) {
        try {
            return Either.success(success.get());
        } catch (final Exception error) {
            return new Either(error , null);
        }
    }

}