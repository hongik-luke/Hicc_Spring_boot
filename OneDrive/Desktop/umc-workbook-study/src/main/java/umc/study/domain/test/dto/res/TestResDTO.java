package umc.study.domain.test.dto.res;

import lombok.Builder;
import lombok.Getter;

public class TestResDTO {

    @Builder
    @Getter
    public static class Testing {
        private String testString;
    }

    @Builder
    @Getter
    public static class Exception {
        private String testString;
    }
}