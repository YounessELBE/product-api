package com.alten.product.exception.handlers;

import com.alten.product.dto.response.ErrorResponse;
import com.alten.product.exception.BusinessException;
import com.alten.product.exception.ObjectNotValidException;
import com.alten.product.messages.ProductMessages;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@RequiredArgsConstructor
public class ProductExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger(ProductExceptionHandler.class);

    private final ProductMessages messages;

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(final EntityNotFoundException e) {
        return ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.NOT_FOUND.name())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(ObjectNotValidException.class)
    public ResponseEntity<ErrorResponse> handleObjectNotValidException(final ObjectNotValidException exception) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.name())
                .message(String.join(",", exception.getErrorMessages()))
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
        LOGGER.error("Validation error: {}", exception.getMessage());

        String errorMessage;

        var missingParams =
                exception.getBindingResult().getFieldErrors().stream()
                        .filter(fieldError -> fieldError.getRejectedValue() == null || emptyList(fieldError.getRejectedValue()))
                        .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(missingParams)) {
            // Incorrect params
            List<String> incorrectParams = new ArrayList<>();
            exception.getBindingResult().getFieldErrors().forEach(error -> incorrectParams.add(error.getField()));
            errorMessage = incorrectParams.stream().collect(Collectors.joining(","));
            errorMessage = messages.getMessage("INCORRECT_PARAMETERS", errorMessage);

        } else {
            // Mandatory params missing
            errorMessage = missingParams.stream().map(FieldError::getField).collect(Collectors.joining(","));
            errorMessage = messages.getMessage("MANDATORY_PARAMETERS_MISSING", errorMessage);
        }

        ErrorResponse error = ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.name())
                .message(errorMessage)
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(new Date())
                .code(ex.getStatus().name())
                .message(ex.getMessage())
                .build();
        return ResponseEntity.status(ex.getStatus()).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(final Exception exception) {
        LOGGER.error(exception);
        ErrorResponse error = ErrorResponse.builder()
                .timestamp(new Date())
                .code(HttpStatus.BAD_REQUEST.name())
                .message(messages.getMessage("UNIDENTIFIED_ERROR_ENCOUNTERED"))
                .build();
        return ResponseEntity.badRequest().body(error);
    }

    protected boolean emptyList(final Object obj) {
        if (!(obj instanceof ArrayList)) {
            return false;
        }
        return CollectionUtils.isEmpty((ArrayList) obj);
    }
}
