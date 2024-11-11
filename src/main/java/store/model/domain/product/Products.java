package store.model.domain.product;

import java.util.List;
import java.util.stream.Collectors;

public class Products {
	private final List<Product> products;

	public Products(List<Product> products) {
		this.products = products;
	}

	public int getProductPrice(String productName) {
		return products.stream()
			.filter(product -> product.getName().equals(productName))
			.findFirst()
			.map(Product::getPrice)
			.orElse(0);
	}

	public int getTotalStockByName(String productName) {
		return products.stream()
			.filter(product -> product.getName().equals(productName))
			.mapToInt(Product::getQuantity)
			.sum();
	}

	public Product findProductByName(String productName) {
		return products.stream()
			.filter(product -> product.getName().equals(productName))
			.findFirst()
			.orElse(null);
	}

	public boolean isProductContain(String productName) {
		return products.stream()
			.anyMatch(product -> product.getName().equals(productName));
	}

	public Product findPromotionProductByName(String productName) {
		return products.stream()
			.filter(product -> product.getName().equals(productName) && product.getPromotion() != null)
			.findFirst()
			.orElse(null);
	}

	public Product findRegularProductByName(String productName) {
		return products.stream()
			.filter(product -> product.getName().equals(productName) && product.getPromotion() == null)
			.findFirst()
			.orElse(null);
	}

	@Override
	public String toString() {
		return products.stream()
			.map(Product::toString)
			.collect(Collectors.joining("\n"));
	}
}
