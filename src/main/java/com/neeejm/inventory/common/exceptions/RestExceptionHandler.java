package com.neeejm.inventory.common.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.util.StringUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.neeejm.inventory.common.errors.ApiError;
import com.neeejm.inventory.common.errors.ValidationError;
import com.neeejm.inventory.common.util.Urls;
import com.neeejm.inventory.role.ReadOnlyRoleException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @Value("#{new Boolean('${app.api-error.include-stacktrace:false}')}")
    private Boolean includeStackTrace;

    @ExceptionHandler({
            EntityNotFoundException.class,
            ResourceNotFoundException.class
    })
    private ResponseEntity<ApiError<String>> handlNotFoundException(Exception exception) {

        log.error("Entity not found!", exception);

        if (exception instanceof ResourceNotFoundException) {
            return handleErrorResponse(HttpStatus.NOT_FOUND,
                    Set.of("Ressource Not found!"),exception);
        }

        return handleErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({
            EntityExistsException.class
    })
    private ResponseEntity<ApiError<String>> handlExsitsException(Exception exception) {

        log.error("Entity exists!", exception);

        return handleErrorResponse(HttpStatus.CONFLICT, exception);
    }

    @ExceptionHandler({
            MutliEntityException.class,
            DataIntegrityViolationException.class,
            ReadOnlyRoleException.class
    })
    private ResponseEntity<ApiError<String>> handlBadRequestException(Exception exception) {

        log.error("Bad request!", exception);

        return handleErrorResponse(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({
            TransactionSystemException.class
    })
    private ResponseEntity<ApiError<ValidationError>> handlValidationException(Exception exception) throws Exception {

        ConstraintViolationException e = (ConstraintViolationException) exception.getCause().getCause();

        log.error("Validation Error!", e);

        Set<ValidationError> validationErrors = new HashSet<>();
        e.getConstraintViolations().forEach(v -> {
            log.info("validator {} / {}", v.getPropertyPath(), v.getMessageTemplate());
            validationErrors.add(new ValidationError(
                    v.getPropertyPath().toString(),
                    v.getMessageTemplate()));
        });
        return handleErrorResponse(
                HttpStatus.UNPROCESSABLE_ENTITY,
                validationErrors,
                exception);
    }

    @ExceptionHandler({
        HttpRequestMethodNotSupportedException.class
    })
    public final ResponseEntity<ApiError<String>> handleUnsuppertedMethoException(Exception exception) {
        
        log.error("Not Supported HTTP Method!", exception);
        return handleErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, exception);
    }

    @ExceptionHandler({
            Exception.class
    })
    public final ResponseEntity<ApiError<String>> handleException(Exception exception) {

        log.error("Ops! (server error)", exception);
        return handleErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                new Exception("Unexpected error :("));
    }

    private ResponseEntity<ApiError<String>> handleErrorResponse(
            HttpStatus status,
            Exception exception) {

        if (Boolean.TRUE.equals(includeStackTrace)) {
            return ResponseEntity.status(status).body(
                    new ApiError<String>(
                            status,
                            getErrorMessages(exception),
                            getStackTrace(exception),
                            Urls.getRequestUrl()));
        }

        return ResponseEntity.status(status).body(
                new ApiError<String>(
                        status,
                        getErrorMessages(exception),
                        Urls.getRequestUrl()));
    }

    private <T> ResponseEntity<ApiError<T>> handleErrorResponse(
            HttpStatus status,
            Set<T> msgs,
            Exception exception) {

        if (Boolean.TRUE.equals(includeStackTrace)) {
            return ResponseEntity.status(status).body(
                    new ApiError<>(
                            status,
                            msgs,
                            getStackTrace(exception),
                            Urls.getRequestUrl()));
        }

        return ResponseEntity.status(status).body(
                new ApiError<>(
                        status,
                        msgs,
                        Urls.getRequestUrl()));
    }

    private String getStackTrace(Exception exception) {
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    private Set<String> getErrorMessages(Exception exception) {
        if (exception.getMessage().contains(",")) {
            return Set.of(StringUtils.split(exception.getMessage(), ","));
        }
        return Set.of(exception.getMessage());
    }
}
