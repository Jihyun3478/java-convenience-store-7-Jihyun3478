package store.util;

import store.model.domain.customer.Customer;
import store.model.domain.product.Products;
import store.model.domain.product.Promotions;
import store.service.CalculateService;
import store.view.InputView;
import store.view.OutputView;
import store.model.domain.discount.MembershipDiscount;

public class ReceiptGenerator {
	public static void generateReceipt(Customer customer, Products products, Promotions promotions, CalculateService calculateService) {
		int totalPrice = calculateService.calculateBuyProductTotalPrice(customer, products);
		int promotionDiscount = calculateService.calculatePromotionDiscount(customer, products, promotions);
		int membershipDiscount = applyMembershipDiscount(totalPrice - promotionDiscount);

		int giftProductTotalPrice = calculateService.calculateGiftProductTotalPrice(customer, products);
		int finalPrice = totalPrice + giftProductTotalPrice - promotionDiscount - membershipDiscount;
	}

	private static int applyMembershipDiscount(int discountedTotal) {
		OutputView.checkMembershipDiscount();
		if (InputView.checkDiscount().equals("Y")) {
			return new MembershipDiscount().discount(discountedTotal);
		}
		return 0;
	}
}
