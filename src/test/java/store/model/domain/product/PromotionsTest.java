package store.model.domain.product;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;

class PromotionsTest {

	@Test
	void 프로모션_이름으로_찾기() {
		Promotion promotion = new Promotion("콜라", 1, 1, LocalDate.now(), LocalDate.now().plusDays(1));
		Promotions promotions = new Promotions(List.of(promotion));

		assertThat(promotions.findPromotionByName("콜라")).isEqualTo(promotion);
		assertThat(promotions.findPromotionByName("사이다")).isNull();
	}
}
