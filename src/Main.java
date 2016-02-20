import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws IOException{
		List<String[]> structureFile = ReadFile.readStructure();
		List<String[]> dataSet = ReadFile.readData();
		
		
		EntropyCalculation entropy = new EntropyCalculation();
		//double S = entropy.calculateEntropy_S(dataSet, structureFile);
		double[] attributeEntropy = new double[structureFile.get(0).length - 1];
		for(int attributeRow = 0; attributeRow < structureFile.get(0).length - 1; attributeRow++) // Outlook, Temperature, Humidity, Wind,...
			attributeEntropy[attributeRow] = entropy.calculateAttributeEntropy(dataSet, structureFile, attributeRow); 
		
		
//		System.out.println(InformationGain.highestGain(S, attributeEntropy));
//		TreeNode root = new TreeNode(structureFile.get(0)[InformationGain.highestGain(1, attributeEntropy)]);
		TreeNode root = new TreeNode(InformationGain.highestGain(attributeEntropy), new ArrayList<String>(),structureFile.get(0)[InformationGain.highestGain(attributeEntropy)]);
//		System.out.println(structureFile.get(0)[InformationGain.highestGain(attributeEntropy)]);
		
		int highestGainAttributeRow = InformationGain.highestGain(attributeEntropy); //outlook
		
		buildTree(structureFile, dataSet, highestGainAttributeRow, root, entropy);
		
		//System.out.println(root.children.get(1).children.get(1).attributeName);
		//System.out.println(root.children.get(0).children.get(0).attributeToBeCompared);

	}
	
	public static void buildTree(List<String[]> structureFile, List<String[]> dataSet, int highestGainAttributeRow, TreeNode root, EntropyCalculation entropy){
//		System.out.println(root.attributeToBeCompared);
		if(root.attributeToBeCompared.size() == structureFile.get(0).length-1)
			return;
		System.out.println("-----------");	
		double[] attributeEntropy = new double[structureFile.get(0).length - 1];
		for(int targetAttributeIndex = 0; targetAttributeIndex < structureFile.get(highestGainAttributeRow + 1).length; targetAttributeIndex++){ //leafnode sunny overcast rainy
			System.out.println(structureFile.get(highestGainAttributeRow + 1)[targetAttributeIndex] + ":");
			for(int attributeRow = 0; attributeRow < structureFile.get(0).length - 1  ; attributeRow++){ // Outlook, Temperature, Humidity, Wind,...structureFile.get(0).length - 1
				if(attributeRow != highestGainAttributeRow){
					attributeEntropy[attributeRow] = entropy.calculateSubAttributeEntropy(dataSet, structureFile, attributeRow, targetAttributeIndex, highestGainAttributeRow, root);
				}else{
					attributeEntropy[attributeRow] = Double.MAX_VALUE; //outlook
				}
				//System.out.println(root.attributeIndex);
				System.out.println(structureFile.get(0)[attributeRow]+" "+attributeEntropy[attributeRow]);
			}
			if(InformationGain.highestGain(attributeEntropy) == -1)
				System.out.println("Yes/No" + "\n");
			else
				System.out.println(structureFile.get(0)[InformationGain.highestGain(attributeEntropy)] + "\n");
			List<String> currentAttributesToBeCompared = new ArrayList<String>();
			currentAttributesToBeCompared.addAll(root.attributeToBeCompared);
			currentAttributesToBeCompared.add(structureFile.get(highestGainAttributeRow + 1)[targetAttributeIndex]);
			int informationGain = InformationGain.highestGain(attributeEntropy);
			if(InformationGain.highestGain(attributeEntropy) == -1){
				root.addChild(new TreeNode(informationGain, currentAttributesToBeCompared, "Yes/No"));
				//System.out.println(currentAttributesToBeCompared);
			}else{
				root.addChild(new TreeNode(informationGain, currentAttributesToBeCompared, structureFile.get(0)[informationGain]));	
				//System.out.println(currentAttributesToBeCompared);
			}
		}
		//System.out.println(root.attributeName);
		for(int i = 0; i < root.children.size(); i++){
			TreeNode currentNode = root.children.get(i);
			if(currentNode.attributeIndex != -1){
				buildTree(structureFile, dataSet, currentNode.attributeIndex, currentNode, entropy);
			}
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