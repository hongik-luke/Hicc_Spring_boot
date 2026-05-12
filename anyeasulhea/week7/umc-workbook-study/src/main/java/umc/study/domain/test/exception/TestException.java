package umc.study.domain.test.exception;

import umc.study.global.apipayload.code.BaseErrorCode;
import umc.study.global.apipayload.exception.GeneralException;

public class TestException extends GeneralException {
    public TestException(BaseErrorCode code) {
        super(code);
    }
}