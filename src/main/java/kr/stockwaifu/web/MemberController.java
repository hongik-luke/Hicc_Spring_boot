/*package kr.stockwaifu.web;

import kr.stockwaifu.dto.member.MemberReqDTO;
import kr.stockwaifu.dto.member.MemberResDTO;
import kr.stockwaifu.global.apipayload.ApiResponse;
import kr.stockwaifu.global.apipayload.code.MemberSuccessCode;
import kr.stockwaifu.service.MemberCommandService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    public ApiResponse<MemberResDTO.JoinDTO> signUp(@RequestBody MemberReqDTO.JoinDTO dto) {
        MemberResDTO.JoinDTO result = memberCommandService.signup(dto);

        // 공통 성공 응답 반환
        return ApiResponse.onSuccess(MemberSuccessCode.SIGNUP_SUCCESS, result);
    }
}*/
package kr.stockwaifu.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    // 💡 주입받던 서비스도 잠시 주석 처리하거나 지워줍니다. (의존성 전파 차단)
    // private final MemberCommandService memberCommandService;

    @PostMapping("/sign-up")
    // 💡 DTO와 ApiResponse 객체 대신 Map과 String을 사용하여 복잡한 클래스 분석을 완전히 우회합니다.
    public Object signUp(@RequestBody Map<String, Object> dto) {

        // 스웨거 화면 예쁘게 나오는지 확인용 샘플 응답 데이터 가짜로 전달
        return Map.of(
                "isSuccess", true,
                "code", "MEMBER200_2",
                "message", "회원가입이 성공적으로 완료되었습니다.",
                "result", Map.of(
                        "memberId", 1,
                        "createdAt", "2026-05-19T15:30:00"));
    }
}