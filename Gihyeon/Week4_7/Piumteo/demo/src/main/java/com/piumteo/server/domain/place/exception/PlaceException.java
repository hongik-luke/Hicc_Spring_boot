package com.piumteo.server.domain.place.exception;

import com.piumteo.server.global.exception.BusinessException;

public class PlaceException extends BusinessException {

    public PlaceException(PlaceErrorCode errorCode) {
        super(errorCode);
    }

    public PlaceException(PlaceErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
