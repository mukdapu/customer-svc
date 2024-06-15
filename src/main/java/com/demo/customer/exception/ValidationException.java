package com.demo.customer.exception;

import java.io.Serial;

public class ValidationException extends SystemException {

    @Serial
    private static final long serialVersionUID = 8521967549317944163L;

    public ValidationException(String messageKey) {
        super(null, messageKey);
    }

    public ValidationException(String message, String messageKey) {
        super(message, messageKey);
    }

    public ValidationException(String messageKey, Object... params) {
        super(messageKey, params);
    }

    public ValidationException(String message, Throwable e) {
        super(message, e);
    }

}
