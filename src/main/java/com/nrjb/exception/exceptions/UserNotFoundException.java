package com.nrjb.exception.exceptions;

import com.nrjb.exception.BaseException;
import com.nrjb.exception.ErrorCode;

public class UserNotFoundException extends BaseException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
