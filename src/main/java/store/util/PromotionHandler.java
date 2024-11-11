package store.util;

import static store.constant.PromotionType.*;

import camp.nextstep.edu.missionutils.DateTimes;
import store.model.domain.customer.Customer;
import store.model.domain.product.Product;
import store.model.domain.product.Products;
import store.model.domain.product.Promotion;
import store.model.domain.product.Promotions;
import store.view.InputView;
import store.view.OutputView;

public class PromotionHandler {
	public static void handlePromotionMessages(Customer customer, Products products, Promotions promotions) {
		customer.getBuyingProduct().forEach((productName, quantity) -> {
			Product product = products.findProductByName(productName);
			Promotion promotion = (product != null) ? promotions.findPromotionByName(product.getPromotion()) : null;

			if (promotion != null && promotion.isApplyPromotion(quantity, DateTimes.now())) {
				handlePromotion(customer, product, quantity, promotion);
			}
		});
	}

	private static void handlePromotion(Customer customer, Product product, int quantity, Promotion promotion) {
		if (promotion.getName().equals(CARBONATE_DRINK.getPromotionName())) {
			handleCarbonatePromotion(customer, product, quantity, promotion);
		} else {
			handleOtherPromotions(customer, product, quantity, promotion);
		}
	}

	private static void handleCarbonatePromotion(Customer customer, Product product, int quantity,
		Promotion promotion) {
		int buyQuantity = promotion.getBuy();
		int getQuantity = promotion.getGet();

		int applicablePromotionCount = quantity / buyQuantity;
		int applicableQuantity = applicablePromotionCount * getQuantity;

		int nonApplicableQuantity = quantity % buyQuantity;

		if (nonApplicableQuantity > 0 && applicablePromotionCount == 0) {
			OutputView.promptPromotionExceedsMessage(product.getName(), nonApplicableQuantity);
		}
		if (applicablePromotionCount > 0) {
			OutputView.promptPromotionApplicableMessage(product.getName(), applicableQuantity);
		}
		if (InputView.checkProceedPurchase().equalsIgnoreCase("Y")) {
			if (applicableQuantity > 0) {
				customer.addGiftProduct(product.getName(), applicableQuantity);
			}
			if (nonApplicableQuantity > 0) {
				customer.addAdditionalProduct(product.getName(), nonApplicableQuantity);
			}
		}
	}

	private static void handleOtherPromotions(Customer customer, Product product, int quantity, Promotion promotion) {
		int additionalQuantity = (quantity / promotion.getBuy()) * promotion.getGet();

		if (additionalQuantity > 0) {
			OutputView.promptPromotionApplicableMessage(product.getName(), additionalQuantity);
			if (InputView.checkProceedPurchase().equalsIgnoreCase("Y")) {
				customer.addAdditionalProduct(product.getName(), additionalQuantity);
				customer.addGiftProduct(product.getName(), additionalQuantity);
			}
		}
	}
}
