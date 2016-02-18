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
		//System.out.print("Number of attributes:");
		numberOfAttributes = 6;//in.nextInt();
		
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
		double S = entropy.calculateEntropy_S(dataSet, structureFile);
		double[] attributeEntropy = new double[structureFile.get(0).length - 1];
		for(int attributeRow = 0; attributeRow < structureFile.get(0).length - 1; attributeRow++) // Outlook, Temperature, Humidity, Wind,...
			attributeEntropy[attributeRow] = entropy.calculateAttributeEntropy(dataSet, structureFile, attributeRow); 
		

		
//		System.out.println(InformationGain.highestGain(S, attributeEntropy));
//		TreeNode root = new TreeNode(structureFile.get(0)[InformationGain.highestGain(S, attributeEntropy)]);
//		System.out.println(root.value);
		
		int highestGainAttributeRow = InformationGain.highestGain(S, attributeEntropy); //outlook
		for(int targetAttributeIndex = 0; targetAttributeIndex < structureFile.get(highestGainAttributeRow + 1).length; targetAttributeIndex++){ //leafnode sunny overcast rainy
			System.out.println(structureFile.get(highestGainAttributeRow + 1)[targetAttributeIndex] + ":");
			for(int attributeRow = 0; attributeRow < structureFile.get(0).length - 1; attributeRow++){ // Outlook, Temperature, Humidity, Wind,...
				if(attributeRow != highestGainAttributeRow)
					attributeEntropy[attributeRow] = entropy.calculateSubAttributeEntropy(dataSet, structureFile, attributeRow, targetAttributeIndex, highestGainAttributeRow);
				else
					attributeEntropy[attributeRow] = Double.MAX_VALUE; //outlook
				System.out.println(structureFile.get(0)[attributeRow]+" "+attributeEntropy[attributeRow]);
			}
			System.out.println();
		}
	}
}


//TreeNode root = new TreeNode("root");
//root.addChild(new TreeNode("1: child1"));
//root.addChild(new TreeNode("1: child2"));
//root.children.get(0).addChild(new TreeNode("2: child1"));
//root.setValue("test");
//root = root.children.get(0);


//int a = 0;
//for(double i : attributeEntropy){
//	double x = S - i;
//	System.out.println(structureFile.get(0)[a++]+" " +S +" - "+ i + " = " + x);
//}