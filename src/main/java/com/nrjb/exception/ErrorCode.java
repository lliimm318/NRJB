package com.nrjb.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    INVALID_TOKEN(401, "invalid token"),
    USER_NOT_FOUND(404,"user not found"),
    USER_ALREADY(409, "user already");

    private final int status;

    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

}
