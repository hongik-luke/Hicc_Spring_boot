package kr.stockwaifu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity // Spring Security 설정 활성화 어노케이션
@Configuration
public class SecurityConfig {

        private final String[] allowUris = {
                        // // Swagger 허용
                        "/sign-up",
                        "/swagger-ui/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
        };

        // HttpSecurity를 통해 다양한 보안 설정을 함
        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http
                                .authorizeHttpRequests(requests -> requests
                                                .requestMatchers(allowUris) // 특정 url에 대한 접근 설정 ,
                                                .permitAll() // 인증 필요 없이 접근 가능한 경로
                                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                                .anyRequest().authenticated())
                                .formLogin(form -> form // 폼 기반 로그인 설정
                                                .defaultSuccessUrl("/swagger-ui/index.html", true)
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutUrl("/logout") // 로그아웃 경로
                                                .logoutSuccessUrl("/login?logout") // 로그아웃 성공시 이 경로로 redirect
                                                .permitAll());

                return http.build();
        }

        // 비밀번호 솔트를 위한 BCrypt를 PAsswordEncoder로 설정
        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }

        /*
         * @Bean
         * public JwtAuthFilter jwtAuthFilter(){
         * return new JwtAuthFilter(jwtUtil,customUserDetailsService);
         * }
         */
}
