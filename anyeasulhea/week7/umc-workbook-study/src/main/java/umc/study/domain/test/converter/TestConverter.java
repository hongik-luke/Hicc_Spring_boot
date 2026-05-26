package umc.study.domain.test.converter;

import umc.study.domain.test.dto.res.TestResDTO;

public class TestConverter {

        // 객체 -> DTO
        public static TestResDTO.Testing toTestingDTO(
                        String testing) {
                return TestResDTO.Testing.builder()
                                .testString(testing)
                                .build();
        }

        // 객체 -> DTO
        public static TestResDTO.Exception toExceptionDTO(
                        String testing) {
                return TestResDTO.Exception.builder()
                                .testString(testing)
                                .build();
        }
}