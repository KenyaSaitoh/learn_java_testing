package pro.kensait.spring.bookstore.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ErrorResponse(
        @JsonProperty(value = "code")
        String code,

        @JsonProperty(value = "message")
        String message
        ) {
}