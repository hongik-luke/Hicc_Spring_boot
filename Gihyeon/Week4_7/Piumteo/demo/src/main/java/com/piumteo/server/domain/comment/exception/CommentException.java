package com.piumteo.server.domain.comment.exception;

import com.piumteo.server.global.exception.BusinessException;

public class CommentException extends BusinessException {

    public CommentException(CommentCode commentCode) {
        super(commentCode);
    }

    public CommentException(
            CommentCode commentCode,
            String message
    ) {
        super(commentCode, message);
    }
}