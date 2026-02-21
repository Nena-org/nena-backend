package come.back.domain.member.member.controller;

import come.back.domain.member.member.dto.SettlementListResponseDto;
import come.back.domain.member.member.service.MemberService;
import come.back.domain.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/settlement/list")
    public ResponseEntity<SettlementListResponseDto> settlementList(@AuthenticationPrincipal CustomUserDetails user) {
        SettlementListResponseDto res = null;
        return ResponseEntity.ok(res);
    }
}
