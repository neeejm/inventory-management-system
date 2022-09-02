package com.neeejm.inventory.common.exceptions;

import java.util.Date;

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

    private String message;

    private String stackTrace;

    private String path;

    public ApiError(
            HttpStatus httpStatus,
            String message,
            String stackTrace,
            String path) {

        this.timestamp = new Date();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.stackTrace = stackTrace;
        this.path = path;
    }

    public ApiError(
            HttpStatus httpStatus,
            String message,
            String path) {

        this.timestamp = new Date();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.message = message;
        this.stackTrace = null;
        this.path = path;
    }
}