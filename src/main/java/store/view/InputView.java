package store.view;

import java.util.Map;

import camp.nextstep.edu.missionutils.Console;
import store.util.Parser;

public class InputView {
	public static Map<String, Integer> buyProduct() {
		String buyingProduct = input();
		return Parser.parseBuyProduct(buyingProduct);
	}

	private static String input() {
		return Console.readLine();
	}
}
