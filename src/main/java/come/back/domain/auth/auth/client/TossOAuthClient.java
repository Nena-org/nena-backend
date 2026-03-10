package come.back.domain.auth.auth.client;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import come.back.domain.auth.auth.dto.TossTokenResponse;
import come.back.domain.auth.auth.dto.TossUserInfoResponse;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TossOAuthClient {

    private final RestTemplate restTemplate;

    @Value("${toss.base-url}")
    private String baseUrl;

    @Value("${toss.client-id}")
    private String clientId;

    @Value("${toss.client-secret}")
    private String clientSecret;

    private static final String TOKEN_PATH = "/api-partner/v1/apps-in-toss/user/oauth2/generate-token";
    private static final String USER_INFO_PATH = "/api-partner/v1/apps-in-toss/user/oauth2/login-me";

    public TossTokenResponse generateToken(String authorizationCode, String referrer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.set("Content-Type", "application/json");

        Map<String, String> body = Map.of(
                "authorizationCode", authorizationCode,
                "referrer", referrer);

        return restTemplate
                .exchange(
                        baseUrl + TOKEN_PATH, HttpMethod.POST, new HttpEntity<>(body, headers), TossTokenResponse.class)
                .getBody();
    }

    public TossUserInfoResponse getUserInfo(String tossAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(tossAccessToken);

        return restTemplate
                .exchange(
                        baseUrl + USER_INFO_PATH, HttpMethod.GET, new HttpEntity<>(headers), TossUserInfoResponse.class)
                .getBody();
    }
}
