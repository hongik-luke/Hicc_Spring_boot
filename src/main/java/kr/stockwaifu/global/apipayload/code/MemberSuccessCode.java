package kr.stockwaifu.global.apipayload.code; // 본인 프로젝트의 패키지 경로에 맞게 수정하세요!

import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    // 가이드북 예시에 있던 성공 코드를 그대로 정의합니다.
    FOUND(HttpStatus.OK, "MEMBER200_1", "성공적으로 사용자를 조회했습니다."),
    SIGNUP_SUCCESS(HttpStatus.OK, "MEMBER200_2", "회원가입이 성공적으로 완료되었습니다.") // 회원가입용 코드 추가
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}