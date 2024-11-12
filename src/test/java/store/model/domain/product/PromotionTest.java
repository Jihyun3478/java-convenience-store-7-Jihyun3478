package store.model.domain.product;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PromotionTest {
	static Stream<LocalDateTime> 날짜_범위_테스트_케이스() {
		return Stream.of(
			LocalDate.now().minusDays(1).atStartOfDay(),
			LocalDate.now().atStartOfDay(),
			LocalDate.now().plusDays(1).atStartOfDay()
		);
	}

	@ParameterizedTest
	@MethodSource("날짜_범위_테스트_케이스")
	void 날짜_범위_내에_적용되는_프로모션(LocalDateTime testDate) {
		Promotion promotion = new Promotion("콜라", 1, 1, LocalDate.now().minusDays(1), LocalDate.now().plusDays(1));
		assertThat(promotion.isDateInRange(testDate)).isTrue();
	}

	@Test
	void 날짜_범위_외에_적용되지_않는_프로모션() {
		Promotion promotion = new Promotion("콜라", 1, 1, LocalDate.now().minusDays(10), LocalDate.now().minusDays(1));
		assertThat(promotion.isDateInRange(LocalDateTime.now())).isFalse();
	}

	static Stream<Integer> 프로모션_적용_수량_테스트_케이스() {
		return Stream.of(3, 4, 5);
	}

	@ParameterizedTest
	@MethodSource("프로모션_적용_수량_테스트_케이스")
	void 프로모션_조건_만족(int quantity) {
		Promotion promotion = new Promotion("콜라", 2, 1, LocalDate.now(), LocalDate.now().plusDays(1));
		assertThat(promotion.isEligibleForDiscount(quantity)).isTrue();
	}

	@Test
	void 프로모션_조건_불만족() {
		Promotion promotion = new Promotion("콜라", 3, 1, LocalDate.now(), LocalDate.now().plusDays(1));
		assertThat(promotion.isEligibleForDiscount(2)).isFalse();
	}
}
