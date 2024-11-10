package store.model.util;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class FileReaderTest {
	@Test
	@DisplayName("productions.md 파일을 읽어온다.")
	public void 상품_파일을_읽어온다() {
		File file = new File("src/main/resources/products.md");

		FileReader fileReader = new FileReader();
		List<String> lines = fileReader.readFile(file);

		assertEquals(17, lines.size());
		assertEquals("name,price,quantity,promotion", lines.get(0));
		assertEquals("콜라,1000,10,탄산2+1", lines.get(1));
		assertEquals("콜라,1000,10,null", lines.get(2));
	}

	@Test
	@DisplayName("promotions.md 파일을 읽어온다.")
	public void 프로모션_파일을_읽어온다() {
		File file = new File("src/main/resources/promotions.md");

		FileReader fileReader = new FileReader();
		List<String> lines = fileReader.readFile(file);

		assertEquals(4, lines.size());
		assertEquals("name,buy,get,start_date,end_date", lines.get(0));
		assertEquals("탄산2+1,2,1,2024-01-01,2024-12-31", lines.get(1));
		assertEquals("MD추천상품,1,1,2024-01-01,2024-12-31", lines.get(2));
	}

	@Test
	@DisplayName("존재하지 않는 파일일 경우 예외가 발생한다.")
	public void 존재하지_않는_파일이다() {
		FileReader fileReader = new FileReader();
		File nonExistingFile = new File("non_existing_file.md");

		assertThatThrownBy(() -> fileReader.readFile(nonExistingFile))
			.isInstanceOf(IllegalStateException.class)
			.hasMessage("[ERROR] 존재하지 않는 파일입니다.");
	}
}
