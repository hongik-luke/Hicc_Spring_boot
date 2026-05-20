package com.piumteo.server.global.security.exception;

import com.piumteo.server.global.exception.BusinessException;

public class SecurityBusinessException extends BusinessException {

    public SecurityBusinessException(SecurityErrorCode errorCode) {
        super(errorCode);
    }

    public SecurityBusinessException(SecurityErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
