package come.back.domain.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TossUserInfoResponse(
        @JsonProperty("userKey") String userKey,
        @JsonProperty("scope") String scope,
        @JsonProperty("name") String name,
        @JsonProperty("phone") String phone,
        @JsonProperty("email") String email) {}
