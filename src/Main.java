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
		
		List<Integer> attributeIndexList = new ArrayList<Integer>();
		List<String> attributeIndexName = new ArrayList<String>();
		
		int highestGainAttributeRow = InformationGain.highestGain(attributeEntropy); //outlook
		
		attributeIndexList.add(highestGainAttributeRow); 
		attributeIndexName.add(structureFile.get(0)[highestGainAttributeRow]);
		//System.out.println(structureFile.get(0)[highestGainAttributeRow]);
		TreeNode root = new TreeNode(attributeIndexList, attributeIndexName,new ArrayList<String>(),structureFile.get(0)[highestGainAttributeRow]);
		buildTree(structureFile, dataSet, highestGainAttributeRow, root, entropy);

		TreeNode.traverseTree(root, structureFile, dataSet, 0);

	}
	
	public static void buildTree(List<String[]> structureFile, List<String[]> dataSet, int highestGainAttributeRow, TreeNode root, EntropyCalculation entropy){

		double[] attributeEntropy = new double[structureFile.get(0).length - 1];
		for(int targetAttributeIndex = 0; targetAttributeIndex < structureFile.get(highestGainAttributeRow + 1).length; targetAttributeIndex++){ //leafnode sunny overcast rainy
			for(int attributeRow = 0; attributeRow < structureFile.get(0).length - 1  ; attributeRow++){ // Outlook, Temperature, Humidity, Wind,...structureFile.get(0).length - 1
				if(attributeRow != highestGainAttributeRow ){
					if(root.attributeIndex.contains(attributeRow)){
						attributeEntropy[attributeRow] = Double.MAX_VALUE; //outlook
					}else{
						attributeEntropy[attributeRow] = entropy.calculateSubAttributeEntropy(dataSet, structureFile, attributeRow, targetAttributeIndex, highestGainAttributeRow, root);
					}
				}else{
					attributeEntropy[attributeRow] = Double.MAX_VALUE; //outlook
				}
			}

			List<String> currentAttributesToBeCompared = new ArrayList<String>();
			currentAttributesToBeCompared.addAll(root.attributeToBeCompared);
			currentAttributesToBeCompared.add(structureFile.get(highestGainAttributeRow + 1)[targetAttributeIndex]);
			
			int informationGain = InformationGain.highestGain(attributeEntropy);
			List<Integer> currentAttributeIndex = new ArrayList<Integer>();
			List<String> currentAttributeIndexName = new ArrayList<String>();
			if(InformationGain.highestGain(attributeEntropy) == -2){
				currentAttributeIndex.addAll(root.attributeIndex);
				currentAttributeIndex.add(informationGain);
				currentAttributeIndexName.addAll(root.attributeIndexName);
				root.addChild(new TreeNode(currentAttributeIndex, currentAttributeIndexName, currentAttributesToBeCompared, "class ="));
			}else{
				currentAttributeIndex.addAll(root.attributeIndex);
				currentAttributeIndex.add(informationGain);
				currentAttributeIndexName.addAll(root.attributeIndexName);
				currentAttributeIndexName.add(structureFile.get(0)[informationGain]);
				root.addChild(new TreeNode(currentAttributeIndex, currentAttributeIndexName, currentAttributesToBeCompared, structureFile.get(0)[informationGain]));	
			}
		}

		for(int i = 0; i < root.children.size(); i++){
			TreeNode currentNode = root.children.get(i);
			if(currentNode.attributeIndex.get(currentNode.attributeIndex.size()-1) != -2){
				buildTree(structureFile, dataSet, currentNode.attributeIndex.get(currentNode.attributeIndex.size()-1), currentNode, entropy);
			}
		}			
	}
}