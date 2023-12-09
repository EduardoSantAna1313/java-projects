package br.com.edu;

public class Main {

    public static void main(String[] args) {
        final var valueFail = parse("abc");
        System.out.println(valueFail);

        final var valueSuccess = parse("123");
        System.out.println(valueSuccess);
    }

    private static Either<NumberFormatException, Integer> parse(final String s ) {
        if (s.matches("\\d*"))
            return Either.success(Integer.parseInt(s));
        return Either.fail(NumberFormatException::new);
    }

}