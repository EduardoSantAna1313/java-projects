package br.com.edu;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void shouldHaveFail() {
        final var either = Either.fail(ArithmeticException::new);
        assertNull(either.success());

        final var fail = either.fail();
        assertNotNull(fail);
        assertInstanceOf(ArithmeticException.class, fail);
    }

    @Test
    void shouldHaveSuccess() {
        final var either = Either.success(123);
        assertNull(either.fail());

        final var success = either.success();
        assertNotNull(success);
        assertInstanceOf(Integer.class, success);
    }

}