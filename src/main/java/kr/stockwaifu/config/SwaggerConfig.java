package kr.stockwaifu.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

        @Bean
        public OpenAPI stockWaifuAPI() {
                // 1. 문서 기본 정보 세팅
                Info info = new Info()
                                .title("StockWaifu API 명세서")
                                .description("회원가입 및 에러 처리 규격 테스트용 Swagger입니다.")
                                .version("v1.0.0");

                // 2. JWT 토큰 헤더 인증 방식 설정
                String securityScheme = "JWT TOKEN";
                SecurityRequirement securityRequirement = new SecurityRequirement().addList(securityScheme);

                Components components = new Components()
                                .addSecuritySchemes(securityScheme, new SecurityScheme()
                                                .name(securityScheme)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("Bearer")
                                                .bearerFormat("JWT"));

                // 3. OpenAPI 객체 조립하여 반환
                return new OpenAPI()
                                .info(info)
                                .addServersItem(new Server().url("/"))
                                .addSecurityItem(securityRequirement)
                                .components(components);
        }

}