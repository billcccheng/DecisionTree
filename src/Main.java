import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		int numberOfAttributes;
		String fileName = ReadFile.read();
		List<String[]> dataSet = null;
		List<String[]> structureFile = null;
		Scanner in = new Scanner(System.in);
		System.out.print("Number of attributes:");
		numberOfAttributes = in.nextInt();
		
		try {
			structureFile = ReadFile.readStructure();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		try {
			dataSet = ReadFile.readParsedInformation(numberOfAttributes, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			in.close();
		}
		
		
		EntropyCalculation entropy = new EntropyCalculation();
		System.out.println(entropy.calculateEntropy_S(dataSet, structureFile));
		//System.out.println(myEntries.get(0)[myEntries.get(0).length-1]);
		//System.out.println(structureFile.get(structureFile.size()-1)[2]);
	}
}


//TreeNode root = new TreeNode("root");
//root.addChild(new TreeNode("1: child1"));
//root.addChild(new TreeNode("1: child2"));
//root.children.get(0).addChild(new TreeNode("2: child1"));
//root.setValue("test");
//root = root.children.get(0);