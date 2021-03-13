package Wallet;

public class NegativeCurrencyValueException extends Exception {
    NegativeCurrencyValueException(String exceptionErrorMessage) {
        super(exceptionErrorMessage);
    }
}