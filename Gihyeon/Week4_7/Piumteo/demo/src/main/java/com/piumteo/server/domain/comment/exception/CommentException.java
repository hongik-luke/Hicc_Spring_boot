package com.piumteo.server.domain.comment.exception;

import com.piumteo.server.global.exception.BusinessException;

public class CommentException extends BusinessException {

    public CommentException(CommentErrorCode errorCode) {
        super(errorCode);
    }

    public CommentException(CommentErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
