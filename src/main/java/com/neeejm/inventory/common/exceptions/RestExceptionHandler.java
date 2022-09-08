package com.neeejm.inventory.common.exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
    private ResponseEntity<ApiError> handlNotFoundException(Exception exception) {

        log.error("Entity not found!", exception);

        if (exception instanceof ResourceNotFoundException) {
            return handleErrorResponse(HttpStatus.NOT_FOUND, "Entity not found!", exception);
        }

        return handleErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    @ExceptionHandler({
            EntityExistsException.class
    })
    private ResponseEntity<ApiError> handlExsitsException(Exception exception) {

        log.error("Entity exists!", exception);

        return handleErrorResponse(HttpStatus.FOUND, exception);
    }

    @ExceptionHandler({
            MutliEntityException.class,
            DataIntegrityViolationException.class,
            ReadOnlyRoleException.class
    })
    private ResponseEntity<ApiError> handlBasRequestException(Exception exception) {

        log.error("Bad request!", exception);

        return handleErrorResponse(HttpStatus.BAD_REQUEST, exception);
    }

    @ExceptionHandler({
            Exception.class
    })
    public final ResponseEntity<ApiError> handleException(Exception exception) {

        log.error("Ops! (server error)", exception);
        return handleErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                new Exception("Unexpected error :("));
    }

    private ResponseEntity<ApiError> handleErrorResponse(
            HttpStatus status,
            Exception exception) {

        if (includeStackTrace) {
            return ResponseEntity.status(status).body(
                    new ApiError(
                            status,
                            getErrorMessages(exception),
                            getStackTrace(exception),
                            Urls.getRequestUrl()));
        }

        return ResponseEntity.status(status).body(
                new ApiError(
                        status,
                        getErrorMessages(exception),
                        Urls.getRequestUrl()));
    }

    private ResponseEntity<ApiError> handleErrorResponse(
            HttpStatus status,
            String msg,
            Exception exception) {

        if (includeStackTrace) {
            return ResponseEntity.status(status).body(
                    new ApiError(
                            status,
                            Set.of(msg),
                            getStackTrace(exception),
                            Urls.getRequestUrl()));
        }

        return ResponseEntity.status(status).body(
                new ApiError(
                        status,
                        Set.of(msg),
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
