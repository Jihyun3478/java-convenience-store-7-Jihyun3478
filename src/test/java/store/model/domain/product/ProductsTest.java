package store.model.domain.product;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class ProductsTest {

	@Test
	void 상품_가격_확인() {
		Products products = new Products(List.of(new Product("콜라", 1000, 10, null)));
		assertThat(products.getProductPrice("콜라")).isEqualTo(1000);
	}

	@Test
	void 특정_상품의_총_재고_수량_확인() {
		Products products = new Products(List.of(
			new Product("콜라", 1000, 10, null),
			new Product("콜라", 1000, 5, null)
		));
		assertThat(products.getTotalStockByName("콜라")).isEqualTo(15);
	}

	@Test
	void 상품_존재_여부_확인() {
		Products products = new Products(List.of(new Product("콜라", 1000, 10, null)));
		assertThat(products.isProductContain("콜라")).isTrue();
		assertThat(products.isProductContain("사이다")).isFalse();
	}

	@Test
	void 프로모션_상품_찾기() {
		Products products = new Products(List.of(new Product("콜라", 1000, 10, "할인")));
		assertThat(products.findPromotionProductByName("콜라")).isNotNull();
		assertThat(products.findPromotionProductByName("사이다")).isNull();
	}
}
