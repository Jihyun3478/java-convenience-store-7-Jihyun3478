package store.util;

import store.model.domain.customer.Customer;
import store.model.domain.product.Product;
import store.model.domain.product.Products;

public class InventoryManager {

	public static void decreaseProductQuantities(Customer customer, Products products) {
		customer.getBuyingProduct().forEach((productName, quantity) -> {
			decreaseBuyingProductQuantities(products, productName, quantity);
		});

		customer.getGiftProduct().forEach((productName, quantity) -> {
			decreaseGiftProductQuantities(products, productName, quantity);
		});
	}

	private static void decreaseBuyingProductQuantities(Products products, String productName, int quantity) {
		Product promotionProduct = products.findPromotionProductByName(productName);
		Product regularProduct = products.findRegularProductByName(productName);

		int promotionQuantity = calculatePromotionQuantity(promotionProduct, quantity);
		int remainingQuantity = quantity - promotionQuantity;

		decreaseProductQuantity(promotionProduct, promotionQuantity);
		decreaseProductQuantity(regularProduct, remainingQuantity);
	}

	private static void decreaseGiftProductQuantities(Products products, String productName, int quantity) {
		Product promotionProduct = products.findPromotionProductByName(productName);
		Product regularProduct = products.findRegularProductByName(productName);

		int promotionQuantity = calculateGiftPromotionQuantity(promotionProduct, quantity);
		int remainingQuantity = quantity - promotionQuantity;

		decreaseProductQuantity(promotionProduct, promotionQuantity);
		decreaseProductQuantity(regularProduct, remainingQuantity);
	}

	private static int calculatePromotionQuantity(Product promotionProduct, int quantity) {
		int promotionQuantity = 0;
		if (promotionProduct != null) {
			promotionQuantity = Math.min(quantity, promotionProduct.getQuantity());
		}
		return promotionQuantity;
	}

	private static int calculateGiftPromotionQuantity(Product promotionProduct, int quantity) {
		if (promotionProduct == null)
			return 0;
		return Math.min(quantity, promotionProduct.getQuantity());
	}

	private static void decreaseProductQuantity(Product product, int quantity) {
		if (product != null && quantity > 0) {
			product.decreaseQuantity(quantity);
		}
	}
}
