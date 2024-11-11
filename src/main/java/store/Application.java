package store;

import store.controller.StoreController;
import store.service.FileService;

public class Application {
	public static void main(String[] args) {
		StoreController storeController = new StoreController(new FileService());
		storeController.start();
	}
}
