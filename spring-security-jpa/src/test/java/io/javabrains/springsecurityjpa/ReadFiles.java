package io.javabrains.springsecurityjpa;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.io.*;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;

public class ReadFiles {

	/*public static void main(String[] args) throws IOException {
		Path configFilePath = FileSystems.getDefault().getPath(
				"C:\\Users\\eshghos\\Downloads\\Docs\\Project-Doc\\TMO\\EDA_SDK_CUSTOMER_ADAPTION_SW-4.282\\CXP9029435\\ca-repository-4.282.tar\\ca-repository-4.282\\.m2\\repository");

		List<Path> fileWithName = Files.walk(configFilePath).filter(s -> s.toString().endsWith(".pom"))
				.map(Path::getFileName).sorted().collect(Collectors.toList());
		int i = 1;

		try {
			String filename = "C:\\Users\\\\eshghos\\Downloads\\Docs\\Project-Doc\\TMO\\Dependency.xls";
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("Default Dependency");

			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("Dependency");
			rowhead.createCell(1).setCellValue("Version");
			for (Path name : fileWithName) {
				String fileName = name.toString().substring(0, name.toString().indexOf(".pom"));
				//System.out.println(fileName);
				System.out.print("FileName:"+fileName.substring(0,fileName.lastIndexOf("-")));
				System.out.print("Version:"+fileName.substring(fileName.lastIndexOf("-")+1));
				System.out.println("");
				HSSFRow row = sheet.createRow((short) i);
				row.createCell(0).setCellValue(fileName.substring(0,fileName.lastIndexOf("-")));
				row.createCell(1).setCellValue(fileName.substring(fileName.lastIndexOf("-")+1));
				i++;
			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			workbook.close();
			System.out.println("Your excel file has been generated!");

		} catch (Exception ex) {
			System.out.println(ex);
		}
	}*/

}
