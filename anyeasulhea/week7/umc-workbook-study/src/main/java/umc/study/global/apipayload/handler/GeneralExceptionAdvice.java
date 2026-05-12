package umc.study.global.apipayload.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import umc.study.global.apipayload.ApiResponse;
import umc.study.global.apipayload.code.BaseErrorCode;
import umc.study.global.apipayload.code.GeneralErrorCode;
import umc.study.global.apipayload.exception.GeneralException;

@RestControllerAdvice
public class GeneralExceptionAdvice {

        // 애플리케이션에서 발생하는 커스텀 예외를 처리
        @ExceptionHandler(GeneralException.class)
        public ResponseEntity<ApiResponse<Void>> handleException(
                        GeneralException ex) {

                return ResponseEntity.status(ex.getCode().getStatus())
                                .body(ApiResponse.onFailure(
                                                ex.getCode(),
                                                null));
        }

        // 그 외의 정의되지 않은 모든 예외 처리
        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<String>> handleException(
                        Exception ex) {

                BaseErrorCode code = GeneralErrorCode.INTERNAL_SERVER_ERROR;
                return ResponseEntity.status(code.getStatus())
                                .body(ApiResponse.onFailure(
                                                code,
                                                ex.getMessage()));
        }
}