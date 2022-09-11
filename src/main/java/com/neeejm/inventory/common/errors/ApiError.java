package com.neeejm.inventory.common.errors;

import java.util.Date;
import java.util.Set;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class ApiError<T> {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;

    private int code;

    private String status;

    private Set<T> errors;

    private String stackTrace;

    private String path;

    public ApiError(
            HttpStatus httpStatus,
            Set<T> errors,
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
            Set<T> errors,
            String path) {

        this.timestamp = new Date();
        this.code = httpStatus.value();
        this.status = httpStatus.name();
        this.errors = errors;
        this.stackTrace = null;
        this.path = path;
    }
}