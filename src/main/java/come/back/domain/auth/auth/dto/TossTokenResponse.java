package come.back.domain.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TossTokenResponse(
        @JsonProperty("resultType") String resultType,
        @JsonProperty("success") TokenSuccess success) {

    public record TokenSuccess(
            @JsonProperty("accessToken") String accessToken,
            @JsonProperty("refreshToken") String refreshToken,
            @JsonProperty("tokenType") String tokenType,
            @JsonProperty("expiresIn") long expiresIn,
            @JsonProperty("scope") String scope) {}

    public String accessToken() {
        return success.accessToken();
    }
}
