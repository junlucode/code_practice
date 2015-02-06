package ExcelSheetColumnNumber;
public class ExcelSheetColumnNumber {
	public int titleToNumber(String s) {
		int number = 0;
		byte[] columnTitleBytes = s.toUpperCase().getBytes();
		for (int i = columnTitleBytes.length - 1; i >= 0; i--) {			
			if (columnTitleBytes[i] < 'A' || columnTitleBytes[i] > 'Z') {
				throw new IllegalArgumentException();
			}
			int convertedToNumber = columnTitleBytes[i] - 'A' + 1;
			number = number + convertedToNumber * (int)(Math.pow(26, (columnTitleBytes.length - i - 1)));
		}
		
		return number;
	}
}
