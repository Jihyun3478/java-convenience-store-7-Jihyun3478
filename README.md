# 편의점 프로젝트

---

## 목차

1. 프로젝트 소개
2. 프로젝트 주요 기능 목록

---

## 1. 프로젝트 소개
편의점 프로젝트는 구매자의 할인 혜택과 재고 상황을 고려하여, 최종 결제 금액을 계산하고 안내하는 결제 시스템입니다.


---

## 2. 프로젝트 주요 기능 목록
- [재고 관리] 파일에서 상품 재고와 프로모션 정보를 읽어온다.
    * [예외 상황] 존재하지 않는 파일인 경우


- [재고 관리] 현재 편의점의 재고를 출력하는 기능


- [구매] 고객으로부터 구매할 상품명과 수량을 입력받는 기능
    * [예외 상황] 입력 받은 수량이 재고 수량보다 많을 경우
    * [예외 상황] 존재하지 않는 상품명일 경우
    * [예외 상황] 잘못된 입력일 경우
    * 각 상품의 재고 수량을 고려해 결제 가능 여부를 확인한다.
    * 결제된 수량만큼 해당 상품의 재고에서 차감한다.


- [구매] 해당 상품이 프로모션 할인이 적용되는지 확인하는 기능
    * 오늘 날짜가 프로모션 기간 내에 포함된 경우에만 할인을 적용한다.
    * 프로모션은 Buy N Get 1 Free 형태로 진행된다.
    * 동일 삼품에 여러 프로모션이 적용되지 않는다.
    * 프로모션 혜택은 프로모션 재고 내에서만 적용할 수 있다.
    * 프로모션 재고를 우선적으로 차감하고, 재고가 부족할 경우 일반 재고를 사용한다.


- [구매] 프로모션 할인 적용 전, 구매 상품 개수를 확인하는 기능


- [프로모션 할인] 고객이 프로모션 적용이 가능한 상품의 수량을 적게 가져온 경우, 수량 추가 여부를 입력받는 기능
    * Y : 증정 받을 수 있는 상품을 추가한다.
    * N : 증정 받을 수 있는 상품을 추가하지 않는다.
    * 고객이 해당 수량보다 적게 가져온 경우, 필요한 수량을 추가로 가져오면 혜택을 받을 수 있음을 안내한다.
    * 재고가 부족해 일부 수량을 프로모션 혜택 없이 결제해야 하는 경우, 정가로 결제하게 됨을 안내한다.


- [멤버십 할인] 고객으로부터 멤버십 할인 여부를 입력받는 기능
    * Y : 멤버십 할인을 적용한다.
    * N : 멤버십 할인을 적용하지 않는다.
    * 프로모션 미적용 금액의 30%를 할인한다.
    * 프로모션 적용 후 남은 금액에 멤버십 할인을 적용한다.
    * 멤버십 할인의 최대 한도는 8,000원이다.


- [구매] 고객으로부터 구매 진행 여부를 입력받는 기능


- [결제] 최종 금액을 계산하는 기능
    * [예외 상황] 모든 할인이 적용된 최종 금액을 계산한다.


- [영수증 출력] 구매 내역과 최종 금액을 영수증으로 출력하는 기능
    * 구매 상품 내역(구매한 상품명, 수량, 가격)을 출력한다.
    * 증정 상품 내역(프로모션에 따라 무료로 제공된 증정 상품의 목록)을 출력한다.
    * 금액 정보(총 구매액, 행사 할인, 멤버십 할인, 내실 돈)을 출력한다.
    * 영수증의 구성 요소를 보기 좋게 정렬한다.
