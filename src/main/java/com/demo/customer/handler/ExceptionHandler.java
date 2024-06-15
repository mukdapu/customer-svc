package com.demo.customer.handler;

import com.demo.customer.exception.SystemException;
import com.demo.customer.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class ExceptionHandler {

    private final MessageSource messageSource;

    public ValidationException getValidationException(String key) {
        return getValidationException(key, null);
    }

    public ValidationException getValidationException(String key, Object[] params) {
        return getValidationException(key, params, LocaleContextHolder.getLocale());
    }

    public ValidationException getValidationException(String key, Object[] params, Locale locale) {
        return new ValidationException(messageSource.getMessage(key, params, locale), key);
    }

    public SystemException getSystemException(String key) {
        return getSystemException(key, null);
    }

    public SystemException getSystemException(String key, Object[] params) {
        return getSystemException(key, params, LocaleContextHolder.getLocale());
    }

    public SystemException getSystemException(String key, Object[] params, Locale locale) {
        return new SystemException(messageSource.getMessage(key, params, locale), key);
    }
}
