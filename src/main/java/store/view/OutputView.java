package store.view;

import static store.constant.OutputMessage.*;

import store.model.domain.product.Products;

public class OutputView {
	private static final String NEW_LINE = "\n";

	public static void promptCurrentStock(Products products) {
		System.out.println(PROMPT_CURRENT_STOCK.getMessage());
		System.out.println(products.toString());
	}

	public static void printError(String errorMessage) {
		System.out.println(NEW_LINE + errorMessage);
	}

	public static void printNewLine() {
		System.out.println();
	}
}
