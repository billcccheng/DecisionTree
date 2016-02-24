import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

public class ReadFile {
	
	public ReadFile(){
		//System.out.println("ReadFile");
	}
	
	public static List<String[]> readData() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("data.txt"));
	    List<String[]> myEntries = reader.readAll();
	    List<String[]> structureFile = readStructure();
	    
	    for(int i = 0; i < myEntries.size(); i++){
	    	for(int j = 0; j < myEntries.get(i).length - 1; j++){
	    		if(myEntries.get(i)[j].equals("?")){
	    			myEntries.get(i)[j] = structureFile.get(j+1)[(int)(Math.random()*structureFile.get(j+1).length)];
	    		}
	    	}
	    }
			
	    Collections.shuffle(Arrays.asList(myEntries));
	    reader.close();
	    return myEntries;
	}
	
	
	public static List<String[]> readStructure() throws IOException{
		CSVReader reader = new CSVReader(new FileReader("structure.txt"));
	    List<String[]> structureEntries = reader.readAll();
	    reader.close();
	    return structureEntries;
	}
}


//int[][] findMaxOccurrenceAttribte = new int[structureFile.size()][structureFile.get(1).length];
//List<Integer> missingValuesX = new ArrayList<Integer>();
//List<Integer> missingValuesY = new ArrayList<Integer>();

//String missingSymbol = structureFile.get(0)[structureFile.get(0).length - 1];
//for(String[] entries: myEntries){
//for(String element: entries){
//	System.out.print(element + ", ");
//}
//System.out.println();
//}

//System.out.println(missingValuesX + " " + missingValuesY);
//for(int i = 0; i < myEntries.get(i).length - 1; i++){
//for(int j = 0; j < missingValuesY.size(); j++){
//	structureFile.get(missingValuesY.get(j)+1);
//	findMaxOccurence[j][] 
//}
//}
