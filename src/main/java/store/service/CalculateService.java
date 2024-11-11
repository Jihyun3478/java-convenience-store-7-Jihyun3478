package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import store.model.domain.customer.Customer;
import store.model.domain.product.Product;
import store.model.domain.product.Products;
import store.model.domain.product.Promotion;
import store.model.domain.product.Promotions;

public class CalculateService {
	public int calculateBuyProductTotalPrice(Customer customer, Products products) {
		return customer.getBuyingProduct().entrySet().stream()
			.mapToInt(entry -> products.getProductPrice(entry.getKey()) * entry.getValue())
			.sum();
	}

	public int calculateGiftProductTotalPrice(Customer customer, Products products) {
		return customer.getGiftProduct().entrySet().stream()
			.mapToInt(entry -> products.getProductPrice(entry.getKey()) * entry.getValue())
			.sum();
	}

	public int calculatePromotionDiscount(Customer customer, Products products, Promotions promotions) {
		return customer.getBuyingProduct().entrySet().stream()
			.mapToInt(entry -> applyPromotion(entry.getKey(), entry.getValue(), products, promotions))
			.sum();
	}

	private int applyPromotion(String productName, int quantity,
		Products products, Promotions promotions) {
		Product product = products.findProductByName(productName);
		Promotion promotion = (product != null) ? promotions.findPromotionByName(product.getPromotion()) : null;

		if (promotion != null && promotion.isApplyPromotion(quantity, DateTimes.now())) {
			int additionalQuantity = 0;
			additionalQuantity = (quantity / promotion.getBuy()) * promotion.getGet();

			if (additionalQuantity > 0) {
				return product.getPrice() * additionalQuantity;
			}
		}
		return 0;
	}
}
