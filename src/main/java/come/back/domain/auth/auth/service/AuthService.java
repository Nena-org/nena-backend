package come.back.domain.auth.auth.service;

import org.springframework.stereotype.Service;

import come.back.domain.auth.auth.client.TossOAuthClient;
import come.back.domain.auth.auth.dto.TossLoginRequest;
import come.back.domain.auth.auth.dto.TossTokenResponse;
import come.back.domain.auth.auth.dto.TossUserInfoResponse;
import come.back.domain.member.member.entity.Member;
import come.back.domain.member.member.repository.MemberRepository;
import come.back.domain.security.util.JwtProvider;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final TossOAuthClient tossOAuthClient;
    private final MemberRepository memberRepository;
    private final JwtProvider jwtProvider;

    public void tossLogin(TossLoginRequest request, HttpServletResponse response) {
        // 1. 토스 API로 액세스 토큰 발급
        TossTokenResponse tossToken =
                tossOAuthClient.generateToken(request.authorizationCode(), request.referrer());

        // 2. 토스 유저 정보 조회
        TossUserInfoResponse userInfo = tossOAuthClient.getUserInfo(tossToken.accessToken());

        // 3. 회원 조회 또는 신규 생성
        Member member = memberRepository
                .findByTossUserKey(userInfo.userKey())
                .orElseGet(() -> memberRepository.save(new Member(userInfo.userKey())));

        // 4. JWT 발급 후 HttpOnly 쿠키 설정
        String jwt = jwtProvider.generateAccessToken(member.getId());
        setAccessTokenCookie(response, jwt);
    }

    private void setAccessTokenCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(3600); // 1시간
        response.addCookie(cookie);
    }
}
