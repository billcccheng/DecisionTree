import java.util.ArrayList;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class ParseText {
	private String everything;
	public static void read(int numberOfAttributes, String fileName) throws IOException{
		CSVReader reader = new CSVReader(new FileReader(fileName));
	    List<String[]> myEntries = reader.readAll();
	    for(int i = 0; i < myEntries.size(); i++){
	    	for(int j = 0; j < numberOfAttributes; j++ ){
	    		System.out.print(myEntries.get(i)[j]);
	    	}
	    	System.out.println();
	    }
	}
}
