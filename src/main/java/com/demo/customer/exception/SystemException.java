package com.demo.customer.exception;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

@Getter
public class SystemException extends RuntimeException implements Serializable {

    @Serial
    private static final long serialVersionUID = -2351508053441907928L;
    private final String messageKey;
    private final String[] params;

    public SystemException(String message, String messageKey) {
        super(message);
        this.messageKey = messageKey;
        this.params = null;
    }

    public SystemException(String messageKey, Object... objectParams) {
        this.messageKey = messageKey;
        this.params = objectParams != null ? Arrays.stream(objectParams).map(Object::toString).toArray(String[]::new)
                : null;
    }

    public SystemException(String message) {
        super(message);
        this.messageKey = null;
        this.params = null;
    }

    public SystemException(Throwable e) {
        super(e);
        this.messageKey = null;
        this.params = null;
    }

    public SystemException(String message, Throwable e) {
        super(message, e);
        setStackTrace(e.getStackTrace());
        this.messageKey = null;
        this.params = null;
    }
}