package com.khazova.velorentclients.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BusinessError {
    CLIENT_NOT_FOUND("12345", "Клиент с id: %s отсутствует в базе", HttpStatus.NOT_FOUND),
    SOURCE_NOT_FOUND("456789", "Ресурс отсутствует в базе", HttpStatus.NOT_FOUND),
    EMAIL_IS_PRESENT("010101", "Email %s зарегистрирован в базе", HttpStatus.UNAUTHORIZED),
    NOTHING_ALL("010101", "По запросу '%s' ничего не найдено", HttpStatus.NOT_FOUND);

    private final String errorCode;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessError(String errorCode, String description, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
