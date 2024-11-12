package store.constant.promotion;

public enum PromotionType {
	CARBONATE_DRINK("탄산2+1"),
	MD_RECOMMEND_PRODUCT("MD추천상품"),
	FLASH_DISCOUNT("반짝할인"),
	;

	private final String promotionName;

	PromotionType(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromotionName() {
		return promotionName;
	}
}
