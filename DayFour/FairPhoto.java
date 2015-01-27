import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class FairPhoto implements Comparator<FairPhoto.CowPosition>{
	static public enum Color {
		W(0), 
		S(1);
		
		private final int value;
		
		Color(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return this.value;
		}
	};
	
	static public class CowPosition{
		int position;
		Color color;
		public CowPosition(int position, Color color) {
			this.position = position;
			this.color = color;
		}
	}
	
	private String fileName;
	int[] total = {0,0};
	
	public FairPhoto(String fileName){
		this.fileName = fileName;
		total[0] = 0;
		total[1] = 0;
	}
	
	public int calulateMaxRange() {
		List<CowPosition> cows = loadFile();
		Collections.sort(cows, this);
		return calculateCowRange(cows);
	}
	
	private List<CowPosition> loadFile() {
		try {
			ArrayList<CowPosition> cowPosition = null;
			
			String workingDir = System.getProperty("user.dir");
			Path path = FileSystems.getDefault().getPath(workingDir, fileName);
			BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			String line;
			String[] cowPositionString;
			while((line = reader.readLine()) != null) {
				if (cowPosition == null) {
					cowPosition = new ArrayList<CowPosition>(Integer.valueOf(line));
					continue;
				}
				cowPositionString = line.split(" ");
				CowPosition cow = new CowPosition(Integer.valueOf(cowPositionString[0]), Color.valueOf(cowPositionString[1]));
				total[Color.valueOf(cowPositionString[1]).getValue()]++; 
				cowPosition.add(cow);
			}
			return cowPosition;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new IllegalArgumentException();
		}
	}
	
	private int calculateCowRange(List<CowPosition> cows) {
		int startIndex = 0;
		int lastIndex = cows.size()-1;
		while(startIndex < lastIndex) {			
			if (isValidRange(startIndex, lastIndex)) {
				return cows.get(lastIndex).position - cows.get(startIndex).position;
			}
			int range1 = 0;
			int range2 = 0;
			total[cows.get(startIndex).color.getValue()]--;
			if (isValidRange(startIndex+1, lastIndex)) {
				range1 = cows.get(lastIndex).position - cows.get(startIndex+1).position;
			} 
			total[cows.get(lastIndex).color.getValue()]--;
			if (isValidRange(startIndex, lastIndex-1)){
				range2 = cows.get(lastIndex-1).position - cows.get(startIndex).position;
			}
			if (range1 != 0 || range2 != 0) {
				return range1>range2 ? range1: range2; 
			}
			startIndex = startIndex + 1;
			lastIndex = lastIndex - 1;
		}
		return 0;
	}

	private boolean isValidRange(int startIndex, int lastIndex) {
		if ((lastIndex-startIndex+1)%2 == 1)
			return false;
		if (total[Color.S.getValue()] > total[Color.W.getValue()]) {
			return false;
		}
		return true;
	}
	
	@Override
	public int compare(CowPosition o1, CowPosition o2) {
		return o1.position - o2.position;
	}
	
	public static void main(String[] argvs) {
		FairPhoto fairPhoto = new FairPhoto("input.txt");
		System.out.println(fairPhoto.calulateMaxRange());
	}
}
