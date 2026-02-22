package come.back.domain.member.member.controller;

import come.back.domain.member.member.dto.SettlementListResponseDto;
import come.back.domain.member.member.service.MemberService;
import come.back.domain.security.auth.CustomUserDetails;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/me/settlements")
    public ResponseEntity<List<SettlementListResponseDto>> settlementList(@AuthenticationPrincipal CustomUserDetails user) {
        List<SettlementListResponseDto> settlments = memberService.getMySettlements(user.getUserId());
        return ResponseEntity.ok(settlments);
    }
}
