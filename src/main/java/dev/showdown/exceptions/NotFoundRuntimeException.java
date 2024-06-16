package dev.showdown.exceptions;

public class NotFoundRuntimeException extends RuntimeException {

    public NotFoundRuntimeException(String messageFormat, Object... args) {
        super(String.format(messageFormat, args));
    }

}