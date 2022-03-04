package com.nrjb.exception.exceptions;

import com.nrjb.exception.BaseException;
import com.nrjb.exception.ErrorCode;

public class InvalidTokenException extends BaseException {
    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
