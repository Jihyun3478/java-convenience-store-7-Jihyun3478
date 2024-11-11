package store.model.domain.customer;

import static org.assertj.core.api.Assertions.*;
import static store.constant.ErrorMessage.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import store.model.domain.product.Product;
import store.model.domain.product.Products;

class CustomerTest {

	@ParameterizedTest
	@ValueSource(ints = {-1, 0})
	void 유효하지_않은_제품_형식_경우_예외(int quantity) {
		Map<String, Integer> initialBuyingProduct = Map.of("cola", quantity);
		Products products = new Products(List.of(new Product("cola", 1000, 10, null)));

		assertThatThrownBy(() -> new Customer(initialBuyingProduct, products))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(INVALID_FORMAT.getMessage());
	}

	@ParameterizedTest
	@ValueSource(strings = {"sprite", "fanta"})
	void 존재하지_않는_제품일_경우_예외(String productName) {
		Map<String, Integer> initialBuyingProduct = Map.of(productName, 2);
		Products products = new Products(List.of(new Product("cola", 1000, 10, null)));

		assertThatThrownBy(() -> new Customer(initialBuyingProduct, products))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(NON_EXIST_PRODUCT.getMessage());
	}

	@Test
	void 재고_수량_초과일_경우_예외() {
		Map<String, Integer> initialBuyingProduct = Map.of("cola", 20);
		Products products = new Products(List.of(new Product("cola", 1000, 10, null)));

		assertThatThrownBy(() -> new Customer(initialBuyingProduct, products))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage(OVER_STOCK_QUANTITY.getMessage());
	}

	@Test
	void 추가_구매_제품_추가_성공() {
		Map<String, Integer> initialBuyingProduct = Map.of("cola", 1);
		Products products = new Products(List.of(new Product("cola", 1000, 10, null)));
		Customer customer = new Customer(initialBuyingProduct, products);

		customer.addAdditionalProduct("cola", 2);
		assertThat(customer.getBuyingProduct().get("cola")).isEqualTo(3);
	}

	@Test
	void 증정_제품_추가_성공() {
		Map<String, Integer> initialBuyingProduct = Map.of("cola", 1);
		Products products = new Products(List.of(new Product("cola", 1000, 10, null)));
		Customer customer = new Customer(initialBuyingProduct, products);

		customer.addGiftProduct("cola", 1);
		assertThat(customer.getGiftProduct().get("cola")).isEqualTo(1);
	}
}
