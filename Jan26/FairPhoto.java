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
	
	public void setFile(String fileName) {
		this.fileName = fileName;
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
		int maxRange = 0;
		int maxStartIndex = 0;
		int maxLastIndex = 0;
		
		int[] tempTotal = {total[0], total[1]};
		
		for (int i = 0; i < cows.size() - 1; i++) {
			tempTotal[0] = total[0];
			tempTotal[1] = total[1];
			for (int j = cows.size()-1; j >i; j--) {
				if (isValidRange(i, j, total[0], total[1])) {
					int range = cows.get(j).position - cows.get(i).position;
					if (range > maxRange) {
						maxRange = range;
						maxStartIndex = i;
						maxLastIndex = j;
						break;
					}
				}
				tempTotal[cows.get(j-1).color.getValue()]--;
			}
			if (i < cows.size() -2) {
				total[cows.get(i).color.getValue()]--;
			}
		}
		if (maxRange != 0) {
			System.out.println("start: " + cows.get(maxStartIndex).position + " end: " + cows.get(maxLastIndex).position);
		}
		return maxRange;
	}

	private boolean isValidRange(int startIndex, int lastIndex, int numberOfWhite, int numberOfSpotted) {
		if ((lastIndex-startIndex+1)%2 == 1)
			return false;
		if (numberOfSpotted > numberOfWhite) {
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
		fairPhoto.setFile("input1.txt");
		System.out.println(fairPhoto.calulateMaxRange());
		fairPhoto.setFile("input2.txt");
		System.out.println(fairPhoto.calulateMaxRange());
		fairPhoto.setFile("input3.txt");
		System.out.println(fairPhoto.calulateMaxRange());
	}
}
