package store.constant.file;

public enum FilePath {
	PRODUCT_FILE_PATH("src/main/resources/products.md"),
	PROMOTION_FILE_PATH("src/main/resources/promotions.md"),
	;

	private final String filePath;

	FilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}
}
