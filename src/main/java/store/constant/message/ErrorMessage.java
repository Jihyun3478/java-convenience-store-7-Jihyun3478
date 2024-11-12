package store.constant.message;

public enum ErrorMessage {
	NON_EXIST_FILE("[ERROR] 존재하지 않는 파일입니다."),
	INVALID_FORMAT("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요."),
	NON_EXIST_PRODUCT("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."),
	OVER_STOCK_QUANTITY("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");

	private final String message;

	ErrorMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
