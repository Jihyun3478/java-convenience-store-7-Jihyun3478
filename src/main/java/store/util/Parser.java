package store.util;

import static store.constant.ErrorMessage.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import store.model.domain.product.Product;
import store.model.domain.product.Promotion;

public class Parser {
	public static List<Product> parseProduct(List<String> lines) {
		return lines.stream()
			.skip(1)
			.map(Parser::parseProductLine)
			.collect(Collectors.toList());
	}

	public static List<Promotion> parsePromotion(List<String> lines) {
		return lines.stream()
			.skip(1)
			.map(Parser::parsePromotionLine)
			.collect(Collectors.toList());
	}

	private static Product parseProductLine(String line) {
		List<String> columns = parseLine(line);
		String name = columns.get(0);
		int price = Integer.parseInt(columns.get(1));
		int quantity = Integer.parseInt(columns.get(2));

		String promotion = "";
		if (!"null".equals(columns.get(3))) {
			promotion = columns.get(3);
		}
		return new Product(name, price, quantity, promotion);
	}

	private static Promotion parsePromotionLine(String line) {
		List<String> columns = parseLine(line);
		String name = columns.get(0);
		int buy = Integer.parseInt(columns.get(1));
		int get = Integer.parseInt(columns.get(2));
		LocalDate startDate = LocalDate.parse(columns.get(3));
		LocalDate endDate = LocalDate.parse(columns.get(4));

		return new Promotion(name, buy, get, startDate, endDate);
	}

	private static List<String> parseLine(String line) {
		return Stream.of(line.split(","))
			.map(String::trim)
			.collect(Collectors.toList());
	}

	public static Map<String, Integer> parseBuyProduct(String buyingProduct) {
		return Arrays.stream(buyingProduct.split("\\],\\["))
			.map(product -> product.replaceAll("[\\[\\]]", ""))
			.map(product -> {
				String[] details = product.split("-");
				validateInput(details);
				return details;
			})
			.collect(Collectors.toMap(
				details -> details[0],
				details -> Integer.parseInt(details[1]),
				(existing, replacement) -> existing,
				LinkedHashMap::new
			));
	}

	private static void validateInput(String[] details) {
		if (details.length != 2 || !details[1].matches("\\d+")) {
			throw new IllegalArgumentException(INVALID_FORMAT.getMessage());
		}
	}
}
