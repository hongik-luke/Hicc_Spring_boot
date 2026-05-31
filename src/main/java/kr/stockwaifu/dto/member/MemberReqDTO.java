package kr.stockwaifu.dto.member;

import java.time.LocalDate;
import java.util.List;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MemberReqDTO {

    public record JoinDTO(

            @NotBlank String name,
            @Email String email, // 추가된 속성
            @NotBlank String password, // 추가된 속성

            @NotNull LocalDate birth,
            @NotNull Address address,
            @NotNull String specAddress

    // @ExistFoods
    // List<Long> preferCategory
    ) {
    }
}