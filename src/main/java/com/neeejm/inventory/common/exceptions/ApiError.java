package com.neeejm.inventory.common.exceptions;

import java.util.Date;
import java.util.Set;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class ApiError {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private Set<String> errors;

    private String stackTrace;

    private String path;

    public ApiError(
            HttpStatus httpStatus,
            Set<String> errors,
            String stackTrace,
            String path) {

        this.timestamp = new Date();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.errors = errors;
        this.stackTrace = stackTrace;
        this.path = path;
    }

    public ApiError(
            HttpStatus httpStatus,
            Set<String> errors,
            String path) {

        this.timestamp = new Date();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.errors = errors;
        this.stackTrace = null;
        this.path = path;
    }
}