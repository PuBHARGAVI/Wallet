package Wallet;

public class ZeroCurrencyValueException extends Exception {
    ZeroCurrencyValueException(String exceptionErrorMessage) {
        super(exceptionErrorMessage);
    }
}