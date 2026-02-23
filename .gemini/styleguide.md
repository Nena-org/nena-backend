# Team Code Review Style Guide

## Language
- 모든 PR 요약/리뷰/댓글은 **한국어로만** 작성한다.
- 영어 용어가 필요하면 (괄호)로 짧게 병기한다. 예: 의존성 주입(DI)
- 답변은 짧은 결론 → 근거 → 수정 예시 순서로 작성한다.
- 섹션 제목/헤더도 한국어로 작성한다.
- `Summary of Changes`, `Key Findings` 같은 영어 제목은 사용하지 않는다.

## Review format
- ✅ Good / ⚠️ Suggestion / ❌ Issue로 구분
- 가능한 경우 개선 코드 예시를 포함한다.

## Backend focus (Java/Spring)
- 비즈니스 로직 정확성, 트랜잭션 경계, 예외 처리, 성능(N+1/쿼리 수)을 우선 점검한다.
- 변경 제안 시 테스트 관점(단위/통합)의 누락 가능성을 함께 제시한다.
