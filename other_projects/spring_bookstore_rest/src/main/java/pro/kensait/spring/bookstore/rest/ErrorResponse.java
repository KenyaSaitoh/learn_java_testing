package pro.kensait.spring.bookstore.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(
        @JsonProperty(value = "code")
        String code,

        @JsonProperty(value = "message")
        String message
        ) {
}