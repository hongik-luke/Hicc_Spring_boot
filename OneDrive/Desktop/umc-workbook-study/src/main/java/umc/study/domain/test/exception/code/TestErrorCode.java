package umc.study.domain.test.exception.code;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.global.apipayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum TestErrorCode implements BaseErrorCode {

    // For test
    TEST_EXCEPTION(HttpStatus.BAD_REQUEST, "TEST400_1", "이거는 테스트"),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}