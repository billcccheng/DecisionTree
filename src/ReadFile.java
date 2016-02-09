import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class ReadFile {
	
	public ReadFile(){
		System.out.println("ReadFile");
	}
	
	public static List<String[]> readParsedInformation(int numberOfAttributes, String fileName) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(fileName));
	    List<String[]> myEntries = reader.readAll();
	    return myEntries;
	}
	
	public static String read(){
		String fileName;
		File file = new File("");
		
		Scanner in = new Scanner(System.in);
		System.out.print("File Name:");
		fileName = in.nextLine();
		file = new File(fileName);
		
		while(!file.exists()){
			System.out.print("Wrong file name! File Name:");
			fileName = in.nextLine();
			file = new File(fileName);
		}
		
		return fileName;
	}
}
