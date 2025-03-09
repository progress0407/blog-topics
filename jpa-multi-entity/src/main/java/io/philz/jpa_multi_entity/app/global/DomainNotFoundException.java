package io.philz.jpa_multi_entity.app.global;

import static org.springframework.http.HttpStatus.*;

import org.springframework.http.HttpStatus;

public class DomainNotFoundException extends RuntimeException {

    private final static HttpStatus httpStatus = NOT_FOUND;

    public DomainNotFoundException() {
        super("[404] 찾을 수 없습니다.");
    }

    public DomainNotFoundException(String message) {
        super(message);
    }
}
