package store.util;

import static store.constant.AnswerConstant.*;
import static store.constant.message.ErrorMessage.NON_EXIST_PRODUCT;
import static store.constant.promotion.PromotionType.CARBONATE_DRINK;

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
			handleSingleProductPromotion(customer, products, promotions, productName, quantity);
		});
	}

	private static void handleSingleProductPromotion(Customer customer, Products products, Promotions promotions,
		String productName, int quantity) {
		Product product = products.findProductByName(productName);
		Promotion promotion = findPromotion(promotions, product);

		if (promotion != null && promotion.isApplyPromotion(quantity, DateTimes.now())) {
			processPromotion(customer, product, quantity, promotion);
		}
	}

	private static Promotion findPromotion(Promotions promotions, Product product) {
		if (product == null) {
			throw new IllegalArgumentException(NON_EXIST_PRODUCT.getMessage());
		}
		return promotions.findPromotionByName(product.getPromotion());
	}

	private static void processPromotion(Customer customer, Product product, int quantity, Promotion promotion) {
		if (CARBONATE_DRINK.getPromotionName().equals(promotion.getName())) {
			handleCarbonatePromotion(customer, product, quantity, promotion);
		}
		if (!CARBONATE_DRINK.getPromotionName().equals(promotion.getName())) {
			handleOtherPromotions(customer, product, quantity, promotion);
		}
	}

	private static void handleCarbonatePromotion(Customer customer, Product product, int quantity,
		Promotion promotion) {
		int applicableQuantity = calculateApplicableQuantity(promotion, quantity);
		int nonApplicableQuantity = calculateNonApplicableQuantity(promotion, quantity);

		displayPromotionMessages(product, applicableQuantity, nonApplicableQuantity);

		if (InputView.checkProceedPurchase().equalsIgnoreCase(ANSWER_YES)) {
			addGiftAndAdditionalProducts(customer, product, applicableQuantity, nonApplicableQuantity);
		}
	}

	private static void handleOtherPromotions(Customer customer, Product product, int quantity, Promotion promotion) {
		int additionalQuantity = (quantity / promotion.getBuy()) * promotion.getGet();

		if (additionalQuantity > 0) {
			OutputView.promptPromotionApplicableMessage(product.getName(), additionalQuantity);
			if (InputView.checkProceedPurchase().equalsIgnoreCase(ANSWER_YES)) {
				customer.addAdditionalProduct(product.getName(), additionalQuantity);
				customer.addGiftProduct(product.getName(), additionalQuantity);
			}
		}
	}

	private static int calculateApplicableQuantity(Promotion promotion, int quantity) {
		return (quantity / promotion.getBuy()) * promotion.getGet();
	}

	private static int calculateNonApplicableQuantity(Promotion promotion, int quantity) {
		return quantity % promotion.getBuy();
	}

	private static void displayPromotionMessages(Product product, int applicableQuantity, int nonApplicableQuantity) {
		if (nonApplicableQuantity > 0 && applicableQuantity == 0) {
			OutputView.promptPromotionExceedsMessage(product.getName(), nonApplicableQuantity);
		}
		if (applicableQuantity > 0) {
			OutputView.promptPromotionApplicableMessage(product.getName(), applicableQuantity);
		}
	}

	private static void addGiftAndAdditionalProducts(Customer customer, Product product, int applicableQuantity,
		int nonApplicableQuantity) {
		if (applicableQuantity > 0) {
			customer.addGiftProduct(product.getName(), applicableQuantity);
		}
		if (nonApplicableQuantity > 0) {
			customer.addAdditionalProduct(product.getName(), nonApplicableQuantity);
		}
	}
}
