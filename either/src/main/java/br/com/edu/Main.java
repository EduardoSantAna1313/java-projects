package br.com.edu;

public class Main {

    public static void main(final String[] args) {
        System.out.println("\nFail: " + parse("abc"));
        System.out.println("Success: " + parse("123"));
        System.out.println("Catches: " + Either.catches(() -> potentialThrowingCode(1)));
        System.out.println("Catches: " + Either.catches(() -> potentialThrowingCode(2)));
    }

    private static Either<NumberFormatException, Integer> parse(final String s ) {
        if (s.matches("\\d*"))
            return Either.success(Integer.parseInt(s));
        return Either.fail(NumberFormatException::new);
    }

    private static String potentialThrowingCode(int n) {
        if (n == 1) {
            return "" + n;
        }
        throw new IllegalArgumentException();
    }

}