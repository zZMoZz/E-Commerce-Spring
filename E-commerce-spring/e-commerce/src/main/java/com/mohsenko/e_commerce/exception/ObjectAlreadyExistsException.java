package com.mohsenko.e_commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ObjectAlreadyExistsException extends RuntimeException {
    public ObjectAlreadyExistsException(String objectName, String fieldName, Object fieldValue) {
        super(String.format("%s with %s : '%s' is already exists", objectName, fieldName, fieldValue));
    }
}
