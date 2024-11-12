package store.view;

import static store.constant.message.OutputMessage.PROMPT_CURRENT_STOCK;
import static store.constant.message.OutputMessage.PROMPT_BUY_PRODUCT;
import static store.constant.message.OutputMessage.PROMPT_MEMBERSHIP_PROMOTION;
import static store.constant.message.OutputMessage.PROMPT_BUYING_PROCEED;

import store.model.domain.customer.Customer;
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

	public static void printReceipt(Customer customer, int promotionDiscount, int membershipDiscount, int finalPrice,
		Products products) {
		printReceiptHeader();
		printPurchasedProducts(customer, products);
		printGiftProducts(customer);
		printReceiptSummary(customer, promotionDiscount, membershipDiscount, finalPrice, products);
	}

	private static void printReceiptHeader() {
		System.out.println("==============W 편의점================");
		System.out.println("상품명\t\t\t\t수량\t\t\t금액");
	}

	private static void printPurchasedProducts(Customer customer, Products products) {
		customer.getBuyingProduct().forEach((productName, quantity) -> {
			int productPrice = products.getProductPrice(productName) * quantity;
			System.out.printf("%-10s\t\t\t%-4d\t\t%,d원%n", productName, quantity, productPrice);
		});
	}

	private static void printGiftProducts(Customer customer) {
		System.out.println("=============증\t\t정===============");
		customer.getGiftProduct().forEach((productName, quantity) -> {
			System.out.printf("%-10s\t\t\t%-4d%n", productName, quantity);
		});
	}

	private static void printReceiptSummary(Customer customer, int promotionDiscount, int membershipDiscount,
		int finalPrice, Products products) {
		System.out.println("====================================");
		int totalQuantity = getTotalQuantity(customer);
		int totalPrice = calculateProductTotalPrice(customer, products);
		System.out.printf("총구매액\t\t\t\t%-4d\t\t%,d%n", totalQuantity, totalPrice);
		System.out.printf("행사할인\t\t\t\t\t\t\t-%,d%n", promotionDiscount);
		System.out.printf("멤버십할인\t\t\t\t\t\t\t-%,d%n", membershipDiscount);
		System.out.printf("내실돈\t\t\t\t\t\t\t%,d%n", finalPrice);
	}

	private static int getTotalQuantity(Customer customer) {
		return customer.getBuyingProduct().values().stream().mapToInt(Integer::intValue).sum();
	}

	private static int calculateProductTotalPrice(Customer customer, Products products) {
		return customer.getBuyingProduct().entrySet().stream()
			.mapToInt(entry -> products.getProductPrice(entry.getKey()) * entry.getValue())
			.sum();
	}

	public static void promptContinueMessage() {
		System.out.println(PROMPT_BUYING_PROCEED.getMessage());
	}

	public static void printError(String errorMessage) {
		System.out.println(NEW_LINE + errorMessage);
	}

	public static void printNewLine() {
		System.out.println();
	}
}
