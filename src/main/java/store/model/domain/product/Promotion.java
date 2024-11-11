package store.model.domain.product;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Promotion {
	private final String name;
	private final int buy;
	private final int get;
	private final LocalDate startDate;
	private final LocalDate endDate;

	public Promotion(String name, int buy, int get, LocalDate startDate, LocalDate endDate) {
		this.name = name;
		this.buy = buy;
		this.get = get;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public int getBuy() {
		return buy;
	}

	public int getGet() {
		return get;
	}

	public boolean isDateInRange(LocalDateTime currentDate) {
		return !currentDate.isBefore(startDate.atStartOfDay()) && !currentDate.isAfter(endDate.atStartOfDay());
	}

	public boolean isEligibleForDiscount(int quantity) {
		return quantity >= buy;
	}

	public boolean isApplyPromotion(int quantity, LocalDateTime currentDate) {
		return isDateInRange(currentDate) && isEligibleForDiscount(quantity);
	}
}
