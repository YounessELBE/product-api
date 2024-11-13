package com.alten.product.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {

    @JsonProperty(value = "timestamp")
    private Date timestamp;

    @JsonProperty(value = "code")
    private String code;

    @JsonProperty(value = "message")
    private String message;
}
