package ExcelSheetColumnNumber;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExcelSheetColumnNumberTest{
	@Test
	public void test1() {
		ExcelSheetColumnNumber excelSheetColumnNumber = new ExcelSheetColumnNumber();
		Assert.assertEquals(1, excelSheetColumnNumber.titleToNumber("A"));
	}
	
	@Test
	public void test2() {
		ExcelSheetColumnNumber excelSheetColumnNumber = new ExcelSheetColumnNumber();
		Assert.assertEquals(28, excelSheetColumnNumber.titleToNumber("AB"));
	}
}
