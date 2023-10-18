package com.khazova.velorentclients.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessError {
    CLIENT_NOT_FOUND("12345", "Клиент с id: %s отсутствует в базе", HttpStatus.NOT_FOUND);

    private final String errorCode;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessError(String errorCode, String description, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
