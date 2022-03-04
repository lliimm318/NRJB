package com.nrjb.exception.exceptions;

import com.nrjb.exception.BaseException;
import com.nrjb.exception.ErrorCode;

public class UserAlreadyException extends BaseException {
    public UserAlreadyException() {
        super(ErrorCode.USER_ALREADY);
    }
}
