package store.controller;

import java.util.Map;

import store.constant.file.FilePath;
import store.model.domain.customer.Customer;
import store.model.domain.product.Products;
import store.model.domain.product.Promotions;
import store.service.CalculateService;
import store.service.FileService;
import store.util.InventoryManager;
import store.util.PromotionHandler;
import store.util.ReceiptGenerator;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
	private final FileService fileService;
	private final CalculateService calculateService;

	public StoreController(FileService fileService, CalculateService calculateService) {
		this.fileService = fileService;
		this.calculateService = calculateService;
	}

	public void start() {
		try {
			boolean isProceed = true;
			Products products = fileService.createProducts(FilePath.PRODUCT_FILE_PATH.getFilePath());
			Promotions promotions = fileService.createPromotions(FilePath.PROMOTION_FILE_PATH.getFilePath());

			while (isProceed) {
				processBuying(products, promotions);
				OutputView.printNewLine();
			}
		} catch (IllegalArgumentException e) {
			OutputView.printError(e.getMessage());
			start();
		}
	}

	private void processBuying(Products products, Promotions promotions) {
		OutputView.promptCurrentStock(products);
		Customer customer = getCustomerBuyProduct(products);

		PromotionHandler.handlePromotionMessages(customer, products, promotions);
		InventoryManager.decreaseProductQuantities(customer, products);
		ReceiptGenerator.generateReceipt(customer, products, promotions, calculateService);
	}

	private Customer getCustomerBuyProduct(Products products) {
		while (true) {
			try {
				OutputView.promptBuyProductMessage();
				Map<String, Integer> buyingProduct = InputView.buyProduct();
				return new Customer(buyingProduct, products);
			} catch (IllegalArgumentException e) {
				OutputView.printError(e.getMessage());
			}
		}
	}
}
