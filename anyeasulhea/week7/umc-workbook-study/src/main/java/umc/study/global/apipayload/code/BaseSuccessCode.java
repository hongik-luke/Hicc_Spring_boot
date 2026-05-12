package umc.study.global.apipayload.code;

import org.springframework.http.HttpStatus;

public interface BaseSuccessCode {
    HttpStatus getStatus();

    String getCode();

    String getMessage();
}