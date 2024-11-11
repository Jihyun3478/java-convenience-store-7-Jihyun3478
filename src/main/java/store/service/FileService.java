package store.service;

import java.io.File;
import java.util.List;

import store.model.domain.product.Product;
import store.model.domain.product.Products;
import store.model.domain.product.Promotion;
import store.model.domain.product.Promotions;
import store.util.FileReader;
import store.util.Parser;

public class FileService {
	public Products createProducts(String filePath) {
		List<String> lines = FileReader.readFile(new File(filePath));
		List<Product> productList = Parser.parseProduct(lines);
		return new Products(productList);
	}

	public Promotions createPromotions(String filePath) {
		List<String> lines = FileReader.readFile(new File(filePath));
		List<Promotion> promotionList = Parser.parsePromotion(lines);
		return new Promotions(promotionList);
	}
}
