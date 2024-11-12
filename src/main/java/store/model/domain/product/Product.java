package store.model.domain.product;

public class Product {
	private final String name;
	private final int price;
	private int quantity;
	private final String promotion;

	public Product(String name, int price, int quantity, String promotion) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.promotion = promotion;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

	public int getQuantity() {
		return quantity;
	}

	public String getPromotion() {
		return promotion;
	}

	public void decreaseQuantity(int buyQuantity) {
		if (buyQuantity <= quantity) {
			quantity -= buyQuantity;
		}
	}

	@Override
	public String toString() {
		StringBuilder output = new StringBuilder(String.format("- %s %,d원", name, price));

		if (quantity == 0) {
			output.append(" 재고 없음");
		}
		if (quantity != 0) {
			output.append(String.format(" %d개", quantity));
		}
		if (promotion != null && !promotion.isEmpty())
			output.append(" ").append(promotion);
		return output.toString();
	}
}
