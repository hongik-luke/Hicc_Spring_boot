package com.piumteo.server.domain.reaction.exception;

import com.piumteo.server.global.exception.BusinessException;

public class ReactionException extends BusinessException {

    public ReactionException(ReactionErrorCode errorCode) {
        super(errorCode);
    }

    public ReactionException(ReactionErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
