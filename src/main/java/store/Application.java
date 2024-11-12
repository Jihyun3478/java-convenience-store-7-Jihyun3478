package store;

import store.controller.StoreController;
import store.service.CalculateService;
import store.service.FileService;

public class Application {
	public static void main(String[] args) {
		StoreController storeController = new StoreController(new FileService(), new CalculateService());
		storeController.start();
	}
}
