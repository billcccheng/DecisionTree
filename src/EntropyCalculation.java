import java.util.Arrays;
import java.util.List;


public class EntropyCalculation {
	static double trainPortion = 0.6;
	public double calculateAttributeEntropy(List<String[]>  dataSet, List<String[]> structureFile, int attributeRow){
		int numOfClassifier = structureFile.get(structureFile.size()-1).length;
		String[] attributes = structureFile.get(attributeRow + 1);
		double[][] countAttributesWithClassifier = new double[attributes.length][numOfClassifier];
		double[] entropy_A = new double[attributes.length];
		double total = 0;
		double totalEntropy = 0;

		countAttributesWithClassifier = countAttributes(dataSet, structureFile, numOfClassifier, attributes, attributeRow);
		total = sumCount_A(countAttributesWithClassifier, numOfClassifier);
		entropy_A = entropyA(countAttributesWithClassifier, total, numOfClassifier);
		totalEntropy = totalEntropy(entropy_A, countAttributesWithClassifier.length);
//		for(int i = 0; i < countAttributesWithClassifier.length; i++)
//			for(int j = 0; j < countAttributesWithClassifier[0].length; j++ )
//				System.out.println(attributes[i]+" "+countAttributesWithClassifier[i][j]);
			
		return totalEntropy;
	}
	
	private static double[][] countAttributes(List<String[]>  dataSet, List<String[]> structureFile, int numOfClassifier, String[] attributes, int attributeRow){
		double[][] count = new double[attributes.length][numOfClassifier];
		for(int i = 0; i < dataSet.size() * trainPortion; i++){
			for(int j = 0; j < attributes.length; j++){
				for(int k = 0; k < numOfClassifier; k++){
					if(dataSet.get(i)[dataSet.get(i).length - 1].equals(structureFile.get(structureFile.size()-1)[k]) //PlayTennis = No/Yes
							&& dataSet.get(i)[attributeRow].equals(attributes[j])){ // Wind = Weak/Strong 
						count[j][k]++;
					}
				}
			}
		}

		return count;
	}
	
	private static double sumCount_A(double[][] countAttributesWithClassifier, int numOfClassifier){
		double total = 0;
		for(int i = 0; i < countAttributesWithClassifier.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				total += countAttributesWithClassifier[i][j];
			}
		}
		return total;
	}
	
	private static double[] entropyA(double[][] countAttributesWithClassifier, double total, int numOfClassifier){
		double[] entropy = new double[countAttributesWithClassifier.length];
		double[] totalAttribute = new double[countAttributesWithClassifier.length];
		
		for(int i = 0; i < countAttributesWithClassifier.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				totalAttribute[i] += countAttributesWithClassifier[i][j]; //countAttributesWithClassifier[2][0] Temp = cool and PlayTennis = Yes
			}
		}
		
		for(int i = 0; i < countAttributesWithClassifier.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				if(totalAttribute[i] == 0 || total == 0)
					entropy[i] = 0;
				else
					entropy[i] += (totalAttribute[i]/total)*(entropy(countAttributesWithClassifier[i][j]/totalAttribute[i]));
			}
			//System.out.println(totalAttribute[i]/total + " " + totalAttribute[i] + "/" + total);
		}
		
		return entropy;
	}
	
	public double calculateSubAttributeEntropy(List<String[]>  dataSet, List<String[]> structureFile, int attributeRow, int targetAttributeIndex, int highestGainAttributeRow, TreeNode root){
		int numOfClassifier = structureFile.get(structureFile.size()-1).length;
		String leafNode = structureFile.get(highestGainAttributeRow + 1)[targetAttributeIndex]; // sunny overcast rainy
		String[] attributes = structureFile.get(attributeRow + 1); //hot, mild, cool
		double[][] countAttributesWithClassifier = new double[attributes.length][numOfClassifier];
		double[] entropy_A = new double[attributes.length];
		double totalEntropy = 0;
		
		//System.out.println(leafNode);
		countAttributesWithClassifier = countSubAttributes(dataSet, structureFile, numOfClassifier, attributes, attributeRow, leafNode, highestGainAttributeRow, targetAttributeIndex, root);
		
		//double totalOfAttribute = sumCount_SubA(total, countAttributesWithClassifier, numOfClassifier, structureFile.get(highestGainAttributeRow).length);
		entropy_A = entropySubA(countAttributesWithClassifier, numOfClassifier);
		totalEntropy = totalEntropy_A(entropy_A, countAttributesWithClassifier.length, numOfClassifier);
		
//		for(int i = 0; i < countAttributesWithClassifier.length; i++){
//			System.out.println(structureFile.get(highestGainAttributeRow + 1)[targetAttributeIndex]);
//			for(int j = 0; j < countAttributesWithClassifier[0].length; j++ ){
//				System.out.println(structureFile.get(0)[attributeRow] + ":" + attributes[i] +" "+ countAttributesWithClassifier[i][j]);
//			}
//			System.out.println(entropy_A[i]);
//		}
		
		
		return totalEntropy;
	}
	
	
	private static double[][] countSubAttributes(List<String[]>  dataSet, List<String[]> structureFile, int numOfClassifier, String[] attributes, int attributeRow, String leafNode, int highestGainAttributeRow, int targetAttributeIndex, TreeNode root){
		double[][] countAttributesWithClassifier = new double[attributes.length][numOfClassifier];
		for(int i = 0; i < dataSet.size() * trainPortion; i++){
			for(int j = 0; j < attributes.length; j++){  //attributes = sunny,overcast,rainy  attributes = structureFile.get(attributeRow);
				for(int k = 0; k < numOfClassifier; k++){
					if(dataSet.get(i)[dataSet.get(i).length - 1].equals(structureFile.get(structureFile.size()-1)[k]) //PlayTennis = No/Yes
							&& dataSet.get(i)[attributeRow].equals(attributes[j]) && (dataSet.get(i)[highestGainAttributeRow].equals(leafNode))){ // Wind = Weak/Strong
						boolean equals = true;
						if(!root.attributeToBeCompared.isEmpty()){
							for(int l = 0; l < root.attributeToBeCompared.size(); l++){
								if(Arrays.asList(dataSet.get(i)).contains(root.attributeToBeCompared.get(l))){
									//System.out.println(dataSet.get(i)[root.attributeIndex] +"=="+root.attributeToBeCompared.get(l) + " = " + "TRUE");
									equals = true;
								}else{
									//System.out.println(dataSet.get(i)[root.attributeIndex] +"=="+root.attributeToBeCompared.get(l) + " = " + "FALSE");
									equals = false;
								}
							}
							if(equals)
								countAttributesWithClassifier[j][k]++;
						}else{
							countAttributesWithClassifier[j][k]++;
						}
					}
				}
			}
		}

		return countAttributesWithClassifier;
	}
	
	
//	private static double sumCount_SubA(double[] total, double[][] countAttributesWithClassifier, int numOfClassifier, int numOfAttributes){
//		for(int i = 0; i < countAttributesWithClassifier.length; i++){
//			for(int j = 0; j < numOfClassifier; j++){
//				total[i] += countAttributesWithClassifier[i][j];
//			}
//			System.out.println(total[i]); //when sunny hot:2  mild:2  cool:1 
//		}
//		double totalOfAttributes = 0; 
//			for(int i = 0; i < countAttributesWithClassifier.length*numOfClassifier; i++){
//				totalOfAttributes += total[i];
//			}
//			//System.out.println(totalOfAttributes);
//		
//		return totalOfAttributes;
//	}
	
	
	private static double[] entropySubA(double[][] countAttributesWithClassifier, int numOfClassifier){
		double[] entropy = new double[countAttributesWithClassifier.length];
		double[] total = new double[countAttributesWithClassifier.length];
		
		for(int i = 0; i < countAttributesWithClassifier.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				total[i] += countAttributesWithClassifier[i][j];
			}
			//System.out.println(total[i]); //when sunny hot:2  mild:2  cool:1 
		}
		
		double totalOfAttribute = 0;
		for(int i = 0; i < countAttributesWithClassifier.length; i++){
			totalOfAttribute += total[i];
		}
		
		for(int i = 0; i < countAttributesWithClassifier.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				double totalOfSubAttribute = 0;
				for(int k = 0; k < numOfClassifier; k++)
					totalOfSubAttribute += countAttributesWithClassifier[i][k];
				if(total[i] == 0 || totalOfAttribute == 0)
					entropy[i] = 0;
				else
					entropy[i] += (total[i]/totalOfAttribute)*(entropy(countAttributesWithClassifier[i][j]/totalOfSubAttribute));
				//System.out.println(countAttributesWithClassifier[i][j]+"/"+ totalOfSubAttribute + " " + entropy(countAttributesWithClassifier[i][j]/totalOfSubAttribute));
			}
			//System.out.println(total[i]/totalOfAttribute + " " + total[i] + "/" + totalOfAttribute);
		}
		
		return entropy;
	}
	
	
	
	private static double entropy(double probability){
		if(probability == 0)
			return 0;
		double answer = -probability*(Math.log(probability)/Math.log(2));
		return answer;
	}
	
	private static double totalEntropy(double[] entropy, int attributeLength){
		double totalEntropy = 0;
		for(int i = 0; i < attributeLength; i++){
			totalEntropy += entropy[i];
		}

		return totalEntropy;
	}
	
	private static double totalEntropy_A(double[] entropy, int attributeLength, int numOfClassifier){
		double totalEntropy = 0;
		for(int i = 0; i < attributeLength; i++){
			totalEntropy += entropy[i];
		}

		return totalEntropy;
	}
}
