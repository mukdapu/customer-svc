package com.demo.customer.controller;

import com.demo.customer.dto.ApiErrorDto;
import com.demo.customer.exception.SystemException;
import com.demo.customer.exception.ValidationException;
import com.demo.customer.util.AppConstants;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.Locale;


@ControllerAdvice
@RestController
@CommonsLog
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;

    @Value("#{'${spring.application.name:}'}")
    private String appName;

    public ExceptionHandlerController(MessageSource messageSource){
        this.messageSource = messageSource;
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorDto> handleGeneralException(Exception ex) {
        String serverErrorKey = "cust-100";
        log.error("Exception occurred: " + getErrorMessage(ex), ex);
        String message = getMessage(serverErrorKey);
        ApiErrorDto error = getApiError(ex, serverErrorKey, message, HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(SystemException.class)
    public ResponseEntity<ApiErrorDto> handleSystemException(Exception ex) {
        String messageKey = ((SystemException) ex).getMessageKey();
        Object[] params = ((SystemException) ex).getParams();
        String message = ex.getLocalizedMessage();
        String logMessage = getLogMessage(messageKey, params, message);
        if (StringUtils.isEmpty(message) && StringUtils.isNotEmpty(messageKey)) {
            message = getMessage(messageKey, params);
            logMessage = StringUtils.isEmpty(logMessage) ? message : logMessage;
        }
        SystemException logException = new SystemException(logMessage, ex);
        log.error("SystemException occurred: " + getErrorMessage(logException), logException);
        ApiErrorDto error = getApiError(ex, messageKey, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, error.getStatus());
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiErrorDto> handleApplicationValidationException(Exception ex) {
        String messageKey = ((ValidationException) ex).getMessageKey();
        Object[] params = ((ValidationException) ex).getParams();
        String message = ex.getLocalizedMessage();
        String logMessage = getLogMessage(messageKey, params, message);
        if (StringUtils.isEmpty(message) && StringUtils.isNotEmpty(messageKey)) {
            message = getMessage(messageKey, params);
            logMessage = StringUtils.isEmpty(logMessage) ? message : logMessage;
        }
        ValidationException logException = new ValidationException(logMessage, ex);
        log.warn("ValidationException occurred: " + getErrorMessage(logException), logException);
        ApiErrorDto error = getApiError(ex, messageKey, message, HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(error, error.getStatus());
    }

    /**
     * @param ex
     * @return
     */
    private String getErrorMessage(Exception ex) {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("svc: ").append(appName);
            sb.append(",msg: ").append(ex.getLocalizedMessage());
        } catch (Exception e) {
            sb.append(e.getLocalizedMessage());
        }
        return sb.toString();

    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    private String getMessage(String key, Object[] params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }

    private String getLogMessage(String key, Object[] params, String message) {
        Locale locale = LocaleContextHolder.getLocale();
        return locale.equals(AppConstants.LOG_LOCALE) ? message
                : messageSource.getMessage(key, params, AppConstants.LOG_LOCALE);
    }

    private String getCause(Exception ex) {
        return ex.getCause() != null ? ex.getCause().getLocalizedMessage() : null;
    }

    private ApiErrorDto getApiError(Exception ex, String messageKey, String message, HttpStatus status) {
        return ApiErrorDto.builder().status(status).dateTime(Instant.now()).messageKey(messageKey).message(message)
                .cause(getCause(ex)).build();
    }

}
