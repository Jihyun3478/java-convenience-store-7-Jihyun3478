package store.model.domain.product;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class ProductTest {

	@ParameterizedTest
	@ValueSource(ints = {5, 10})
	void 재고_감소_성공(int decreaseQuantity) {
		Product product = new Product("콜라", 1000, 10, null);
		product.decreaseQuantity(decreaseQuantity);
		assertThat(product.getQuantity()).isEqualTo(10 - decreaseQuantity);
	}

	@ParameterizedTest
	@ValueSource(ints = {15, 20})
	void 재고_부족_경우_감소_불가(int decreaseQuantity) {
		Product product = new Product("콜라", 1000, 10, null);
		product.decreaseQuantity(decreaseQuantity);
		assertThat(product.getQuantity()).isEqualTo(10);
	}
}
