package store.model.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
	public List<String> readFile(File file) {
		List<String> fileLines = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
			String fileLine;
			while ((fileLine = br.readLine()) != null) {
				fileLines.add(fileLine);
			}
		} catch (IOException e) {
			throw new IllegalStateException("[ERROR] 존재하지 않는 파일입니다.");
		}
		return fileLines;
	}
}
