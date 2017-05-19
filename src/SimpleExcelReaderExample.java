import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * A dirty simple program that reads an Excel file.
 * 
 * @author www.codejava.net
 *
 */
public class SimpleExcelReaderExample {
	static HashMap<Integer, String> sample;
	static boolean stepOne = false;
	public static void main(String[] args) throws IOException {
		String excelFilePath = "/Users/prabhu/test.xlsx";
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

		String inputVal = "2345";
		
		sample = new HashMap();
		sample.put(0, "5");
		sample.put(1, "6");
		sample.put(2, "7");
		sample.put(3, "8");
		sample.put(4, "9");
		sample.put(5, "0");
		sample.put(6, "1");
		sample.put(7, "2");
		sample.put(8, "3");
		sample.put(9, "4");

		String addFiveNumber = addFiveToNumber(inputVal);
		 System.out.println(addFiveNumber);
		String addOneNumber = addOneToNumber(inputVal);
		 System.out.println(addOneNumber);
		String subOneNumber = subOneToNumber(inputVal);
		 System.out.println(subOneNumber);

		String[] array = { addFiveNumber, addOneNumber, subOneNumber };
		Workbook workbook = new XSSFWorkbook(inputStream);
		Sheet firstSheet = workbook.getSheetAt(0);
		Iterator<Row> iterator = firstSheet.iterator();

		while (iterator.hasNext()) {
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();

			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				long varA = 0;// = new
								// Double(cell.getNumericCellValue()).longValue();

				switch (cell.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					System.out.print(cell.getStringCellValue());
					break;
				case Cell.CELL_TYPE_BOOLEAN:
					System.out.print(cell.getBooleanCellValue());
					break;
				case Cell.CELL_TYPE_NUMERIC:
					varA = new Double(cell.getNumericCellValue()).longValue();
					// System.out.print(varA);
					break;
				}
				// System.out.print(" ");
				stepOneFunction(varA, addFiveNumber, addOneNumber, subOneNumber, cell.getRowIndex(),
						cell.getColumnIndex());
			}
			// System.out.println();
		}

		workbook.close();
		inputStream.close();
		
		if(stepOne == true) {
			System.out.println("Step One has Passed");
		}else {
			System.out.println("Step One has Failed because no matching found");

		}
	}

	private static String addFiveToNumber(String number) {
		String newNumber = "";
		for (int i = 0; i < number.length(); i++) {
			int j = Character.digit(number.charAt(i), 10);
			if (newNumber.equals("")) {
				newNumber = sample.get(j);
			} else {
				newNumber = newNumber + sample.get(j);
			}
		}
		return newNumber;
	}

	private static String addOneToNumber(String number) {
		String newNumber = "";
		for (int i = 0; i < number.length(); i++) {
			int j = Character.digit(number.charAt(i), 10);
			if (newNumber.equals("")) {
				if ((j + 1) == 10) {
					newNumber = "0";
				} else {
					newNumber = String.valueOf((j + 1));
				}
			} else {
				if ((j + 1) == 10) {
					newNumber = newNumber + "0";
				} else {
					newNumber = newNumber + String.valueOf((j + 1));

				}
			}
		}
		return newNumber;
	}

	private static String subOneToNumber(String number) {
		String newNumber = "";
		for (int i = 0; i < number.length(); i++) {
			int j = Character.digit(number.charAt(i), 10);
			if (newNumber.equals("")) {
				if ((j - 1) == -1) {
					newNumber = "9";
				} else {
					newNumber = String.valueOf((j - 1));
				}
			} else {
				if ((j - 1) == -1) {
					newNumber = newNumber + "9";
				} else {
					newNumber = newNumber + String.valueOf((j - 1));

				}
			}
		}
		return newNumber;
	}

	private static void stepOneFunction(long varA, String val1, String val2, String val3, int row,  int coloumn) {
		// TODO Auto-generated method stub

		if (String.valueOf(varA).equals(val1)) {
			stepOne = true;
			System.out.println("value   " + val1 + " located at row " + row + " column " + coloumn);
		}
		if (String.valueOf(varA).equals(val2)) {
			stepOne = true;
			System.out.println("value   " + val1 + " located at row " + row + " column " + coloumn);
		}
		if (String.valueOf(varA).equals(val3)) {
			stepOne = true;
			System.out.println("value   " + val3 + " located at row " + row + " column " + coloumn);
		}
		
		
	}

}