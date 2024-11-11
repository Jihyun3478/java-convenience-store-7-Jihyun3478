package store.view;

import static store.constant.OutputMessage.*;

import store.model.domain.product.Products;

public class OutputView {
	private static final String NEW_LINE = "\n";

	public static void promptCurrentStock(Products products) {
		System.out.println(PROMPT_CURRENT_STOCK.getMessage());
		System.out.println(products.toString());
	}

	public static void promptBuyProductMessage() {
		System.out.println(PROMPT_BUY_PRODUCT.getMessage());
	}

	public static void promptPromotionApplicableMessage(String productName, int additionalQuantity) {
		System.out.printf(NEW_LINE + "현재 %s은(는) %d개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)%n",
			productName, additionalQuantity);
	}

	public static void promptPromotionExceedsMessage(String productName, int nonEligibleQuantity) {
		System.out.printf(NEW_LINE + "현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)%n",
			productName, nonEligibleQuantity);
	}

	public static void checkMembershipDiscount() {
		System.out.print(PROMPT_MEMBERSHIP_PROMOTION.getMessage());
	}

	public static void printError(String errorMessage) {
		System.out.println(NEW_LINE + errorMessage);
	}

	public static void printNewLine() {
		System.out.println();
	}
}
