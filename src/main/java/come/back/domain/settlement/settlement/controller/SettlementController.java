package come.back.domain.settlement.settlement.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import come.back.global.resultData.ResultData;

@RestController
@RequestMapping("/api/v1/rooms/{roomId}/settlements")
public class SettlementController {

    @PostMapping
    public ResultData<String> executeSettlement(@PathVariable Long roomId) {
        // TODO: 정산 실행 서비스 연결 (결제내역 집계 후 송금 트랜잭션 생성) [NENA-3]
        return new ResultData<>("200-1", "정산 실행 API 스켈레톤", "roomId=" + roomId);
    }

    @GetMapping
    public ResultData<String> getSettlements(@PathVariable Long roomId) {
        // TODO: 방 기준 정산 목록 조회 서비스 연결 [NENA-11]
        return new ResultData<>("200-1", "정산 목록 조회 API 스켈레톤", "roomId=" + roomId);
    }

    @GetMapping("/{settlementId}/transactions/{transactionId}")
    public ResultData<String> getSettlementTransaction(
            @PathVariable Long roomId, @PathVariable Long settlementId, @PathVariable Long transactionId) {
        // TODO: 정산 거래 상세 조회 서비스 연결 (roomId-settlementId-transactionId 정합성 검증 포함) [NENA-12]
        return new ResultData<>("200-1", "정산 상세 조회 API 스켈레톤", "transactionId=" + transactionId);
    }

    @PatchMapping("/{settlementId}/transactions/{transactionId}")
    public ResultData<String> completeSettlementTransaction(
            @PathVariable Long roomId, @PathVariable Long settlementId, @PathVariable Long transactionId) {
        // TODO: 송금 완료 처리 서비스 연결 (상태 변경 + 완료 시각 기록) [NENA-13]
        return new ResultData<>("200-1", "송금 완료 처리 API 스켈레톤", "transactionId=" + transactionId);
    }

    @GetMapping("/results")
    public ResultData<String> getSettlementResults(@PathVariable Long roomId) {
        // TODO: 정산 결과 목록 조회 서비스 연결 (완료/미완료 집계) [NENA-14]
        return new ResultData<>("200-1", "정산 결과 목록 조회 API 스켈레톤", "roomId=" + roomId);
    }
}
