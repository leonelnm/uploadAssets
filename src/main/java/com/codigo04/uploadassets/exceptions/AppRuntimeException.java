package com.codigo04.uploadassets.exceptions;

public class AppRuntimeException extends RuntimeException{

    public AppRuntimeException(String message) {
        super(message);
    }

    public AppRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
