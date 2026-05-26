package umc.study.global.apipayload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.study.global.apipayload.code.BaseErrorCode;
import umc.study.global.apipayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
@JsonPropertyOrder({ "isSuccess", "code", "message", "result" })
public class ApiResponse<T> {

    @JsonProperty("isSuccess")
    private final Boolean isSuccess;

    @JsonProperty("code")
    private final String code;

    @JsonProperty("message")
    private final String message;

    @JsonProperty("result")
    private T result;

    // 성공한 경우 (result 포함)

    // 실패한 경우 (result 포함)
    public static <T> ApiResponse<T> onFailure(BaseErrorCode code, T result) {
        return new ApiResponse<>(false, code.getCode(), code.getMessage(), result);
    }

    // GeneralSuccessCode 등을 받기 위한 범용 메서드
    public static <T> ApiResponse<T> onSuccess(BaseSuccessCode code, T result) {
        return new ApiResponse<>(true, code.getCode(), code.getMessage(), result);
    }
}
