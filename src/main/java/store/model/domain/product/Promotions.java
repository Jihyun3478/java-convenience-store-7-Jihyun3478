package store.model.domain.product;

import java.util.List;

public class Promotions {
	private final List<Promotion> promotions;

	public Promotions(List<Promotion> promotions) {
		this.promotions = promotions;
	}

	public Promotion findPromotionByName(String promotionName) {
		return promotions.stream()
			.filter(promotion -> promotion.getName().equals(promotionName))
			.findFirst()
			.orElse(null);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Promotion promotion : promotions) {
			sb.append(promotion.toString()).append("\n");
		}
		return sb.toString();
	}
}

