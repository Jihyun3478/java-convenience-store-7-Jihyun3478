package store.constant;

public enum OutputMessage {
	PROMPT_CURRENT_STOCK("안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다."),
	PROMPT_BUY_PRODUCT("%n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])"),
	PROMPT_MEMBERSHIP_PROMOTION("%n멤버십 할인을 받으시겠습니까? (Y/N)%n"),
	PROMPT_BUYING_PROCEED("%n감사합니다. 구매하고 싶은 다른 상품이 있나요? (Y/N)"),
	;

	private final String message;

	OutputMessage(String message) {
		this.message = message;
	}

	public String getMessage(Object... args) {
		return String.format(message, args);
	}
}
