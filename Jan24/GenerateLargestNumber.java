import java.util.Arrays;
import java.util.Comparator;

public class GenerateLargestNumber {
	public String generate(int[] num){
		String stringValue = Arrays.toString(num);
		String[] stringArray = stringValue.substring(1, stringValue.length()-1).split(",");
		Arrays.sort(stringArray, new sortNumber());
		StringBuilder largestNumber = new StringBuilder();
		for (String element : stringArray) {
			largestNumber = largestNumber.insert(0, element.trim());
		}
		if (largestNumber.toString().replace("0", "").length() == 0) {
			return "0";
		}
		return largestNumber.toString();
	}
	
	public class sortNumber implements Comparator<String> {
		public int compare(String a, String b) {
			return (a.trim()+b.trim()).compareTo(b.trim()+a.trim());
		}
	}
	
	public static void main(String[] argvs) {
		GenerateLargestNumber generator = new GenerateLargestNumber();
		
		int[] testcase1 = {3, 30, 34, 5, 9};
		System.out.println(generator.generate(testcase1));
		int[] testcase2 = {0, 0};
		System.out.println(generator.generate(testcase2));
		int[] testcase3 = {2};
		System.out.println(generator.generate(testcase3));
		int[] testcase4 = {};
		System.out.println(generator.generate(testcase4));
		int[] testcase5 = {0, 1000, 213};
		System.out.println(generator.generate(testcase5));
		int[] testcase6 = {999999998,999999997,999999999};
		System.out.println(generator.generate(testcase6));
	}
}
