package com.alten.product.validator;


import com.alten.product.exception.ObjectNotValidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ObjectValidator<T> {

    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    /**
     * Validates the provided object using Java Bean Validation.
     *
     * @param objToValidate The object to be validated.
     * @throws ObjectNotValidException If the object is not valid, containing error messages.
     */
    public void validate(T objToValidate) {
        // Perform validation using Java Bean Validation
        Set<ConstraintViolation<T>> violations = validator.validate(objToValidate);

        // If there are validation violations, collect error messages and throw an exception
        if (!violations.isEmpty()) {
            var errorMessages = violations
                    .stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.toSet());
            throw new ObjectNotValidException(errorMessages);
        }
    }
}
