package store.util;

import store.model.domain.customer.Customer;
import store.model.domain.product.Product;
import store.model.domain.product.Products;

public class InventoryManager {
	public static void decreaseProductQuantities(Customer customer, Products products) {
		customer.getBuyingProduct().forEach((productName, quantity) -> {
			Product promotionProduct = products.findPromotionProductByName(productName);
			Product regularProduct = products.findRegularProductByName(productName);

			int promotionQuantity = promotionProduct != null ? Math.min(quantity, promotionProduct.getQuantity()) : 0;
			int remainingQuantity = quantity - promotionQuantity;

			if (promotionProduct != null && promotionQuantity > 0) {
				promotionProduct.decreaseQuantity(promotionQuantity);
			}

			if (regularProduct != null && remainingQuantity > 0) {
				regularProduct.decreaseQuantity(remainingQuantity);
			}
		});

		customer.getGiftProduct().forEach((productName, quantity) -> {
			Product promotionProduct = products.findPromotionProductByName(productName);
			Product regularProduct = products.findRegularProductByName(productName);

			if (promotionProduct != null && quantity <= promotionProduct.getQuantity()) {
				promotionProduct.decreaseQuantity(quantity);
			} else {
				int promotionQuantity = promotionProduct != null ? promotionProduct.getQuantity() : 0;
				int remainingQuantity = quantity - promotionQuantity;

				if (promotionProduct != null && promotionQuantity > 0) {
					promotionProduct.decreaseQuantity(promotionQuantity);
				}

				if (regularProduct != null && remainingQuantity > 0) {
					regularProduct.decreaseQuantity(remainingQuantity);
				}
			}
		});
	}
}
