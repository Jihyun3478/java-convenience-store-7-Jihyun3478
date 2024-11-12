package store.model.domain.discount;

public class MembershipDiscount implements DiscountPolicy {
	private static final double DISCOUNT_RATE = 0.3;
	private static final int MAX_DISCOUNT = 8000;

	@Override
	public int discount(int price) {
		int discount = (int)(price * DISCOUNT_RATE);
		return Math.min(discount, MAX_DISCOUNT);
	}
}

