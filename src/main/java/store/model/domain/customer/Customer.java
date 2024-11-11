package store.model.domain.customer;

import static store.constant.ErrorMessage.INVALID_FORMAT;
import static store.constant.ErrorMessage.NON_EXIST_PRODUCT;
import static store.constant.ErrorMessage.OVER_STOCK_QUANTITY;

import java.util.HashMap;
import java.util.Map;

import store.model.domain.product.Products;

public class Customer {
	private final Map<String, Integer> buyingProduct = new HashMap<>();
	private final Map<String, Integer> giftProduct = new HashMap<>();

	public Customer(Map<String, Integer> initialBuyingProduct, Products products) {
		validateBuyingProduct(initialBuyingProduct, products);
	}

	private void validateBuyingProduct(Map<String, Integer> inputProduct, Products products) {
		inputProduct.forEach((productName, quantity) -> {
			validateProduct(quantity, productName, products);
			buyingProduct.put(productName, quantity);
		});
	}

	private void validateProduct(Integer quantity, String productName, Products products) {
		validateProductFormat(quantity);
		validateProductExist(productName, products);
		validateStockQuantity(productName, quantity, products);
	}

	private void validateProductFormat(Integer quantity) {
		if (quantity == null || quantity <= 0) {
			throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
		}
	}

	private void validateProductExist(String productName, Products products) {
		if (!products.isProductContain(productName)) {
			throw new IllegalArgumentException(NON_EXIST_PRODUCT.getMessage());
		}
	}

	private void validateStockQuantity(String productName, Integer quantity, Products products) {
		if (products.getTotalStockByName(productName) < quantity) {
			throw new IllegalArgumentException(OVER_STOCK_QUANTITY.getMessage());
		}
	}

	public Map<String, Integer> getBuyingProduct() {
		return new HashMap<>(buyingProduct);
	}

	public Map<String, Integer> getGiftProduct() {
		return new HashMap<>(giftProduct);
	}

	public void addAdditionalProduct(String productName, int additionalQuantity) {
		buyingProduct.merge(productName, additionalQuantity, Integer::sum);
	}

	public void addGiftProduct(String productName, int giftQuantity) {
		giftProduct.merge(productName, giftQuantity, Integer::sum);
	}
}
