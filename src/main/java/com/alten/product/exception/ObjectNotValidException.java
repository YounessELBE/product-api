package com.alten.product.exception;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class ObjectNotValidException extends RuntimeException {
    private final Set<String> errorMessages;

    public ObjectNotValidException(Set<String> errorMessages) {
        this.errorMessages = errorMessages;
    }
}
