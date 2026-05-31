package kr.stockwaifu.domain.memeber.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import kr.stockwaifu.global.apipayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾지 못했습니다."),
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST, "MEMBER400_1", "이미 존재하는 이메일입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}