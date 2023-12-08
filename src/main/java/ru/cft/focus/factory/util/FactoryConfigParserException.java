package ru.cft.focus.factory.util;

public class FactoryConfigParserException extends Exception {

    private static final String ERROR_MSG = "Error while parsing config: ";
    private final String message;

    public FactoryConfigParserException(String message) {
        this.message = ERROR_MSG + message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
