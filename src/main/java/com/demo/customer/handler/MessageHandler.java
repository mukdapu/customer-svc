package com.demo.customer.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * Handler class to manage messages from messages.properties .
 */

@Component
@RequiredArgsConstructor
public class MessageHandler {

    private final MessageSource messageSource;

    public String getMessage(String key) {
        return getMessage(key, null);
    }

    public String getMessage(String key, Object[] params) {
        return getMessage(key, params, LocaleContextHolder.getLocale());
    }

    public String getMessage(String key, Object[] params, Locale locale) {
        return messageSource.getMessage(key, params, locale);
    }

    public String getMessage(String key, Object[] params, String langCode) {
        return messageSource.getMessage(key, params, Locale.forLanguageTag(langCode));
    }
}
