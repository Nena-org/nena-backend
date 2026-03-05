package come.back.domain.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
<<<<<<< Updated upstream

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                // 🔥 CSRF 비활성 (REST API 개발 시 일반적)
                .csrf(csrf -> csrf.disable())

                // 🔥 기본 로그인 UI 제거
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())

                // 🔥 세션 사용 안함 (JWT 대비)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 🔥 모든 요청 허용
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
=======
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import come.back.domain.security.filter.JwtAuthenticationFilter;
import come.back.domain.security.util.JwtProvider;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth.requestMatchers(
                                        "api/v1/auth/**", "/swagger-ui/**", "/v3/api-docs/**")
                                .permitAll()
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);
>>>>>>> Stashed changes

        return http.build();
    }
}
