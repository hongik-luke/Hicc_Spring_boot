package umc.study.global.apipayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.global.apipayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException {

    private final BaseErrorCode code;
}
