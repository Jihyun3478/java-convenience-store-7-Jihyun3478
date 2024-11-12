package store.view;

import java.util.Map;

import camp.nextstep.edu.missionutils.Console;
import store.util.Parser;

public class InputView {
	public static Map<String, Integer> buyProduct() {
		String buyingProduct = input();
		return Parser.parseBuyProduct(buyingProduct);
	}

	public static String checkProceedPurchase() {
		return input();
	}

	public static String checkDiscount() {
		return input();
	}

	private static String input() {
		return Console.readLine();
	}
}
