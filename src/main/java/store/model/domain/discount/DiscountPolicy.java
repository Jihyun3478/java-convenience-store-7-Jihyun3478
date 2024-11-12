package store.model.domain.discount;

public interface DiscountPolicy {
	int discount(int price);
}
