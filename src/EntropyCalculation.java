import java.util.List;


public class EntropyCalculation {
	int numOfClassifier;
	public double calculateEntropy_S(List<String[]>  dataSet, List<String[]> structureFile){
		int numOfClassifier = structureFile.get(structureFile.size()-1).length;
		double[] count = new double[numOfClassifier];
		double[] entropy_S = new double[numOfClassifier];
		double total = 0;
		double totalEntropy = 0;

		count = count(dataSet, structureFile, numOfClassifier);
		total = sumCount_S(count, numOfClassifier);
		entropy_S = entropyS(count, total, numOfClassifier);
		totalEntropy = totalEntropy(entropy_S, numOfClassifier);
		
		return totalEntropy;
	}
	
	public double calculateAttributeEntropy(List<String[]>  dataSet, List<String[]> structureFile, int attributeIndex){
		int numOfClassifier = structureFile.get(structureFile.size()-1).length;
		String[] attributes = structureFile.get(attributeIndex);
		double[][] count = new double[attributes.length][numOfClassifier];
		double[] entropy_A = new double[attributes.length];
		double total = 0;
		double totalEntropy = 0;

		count = countAttributes(dataSet, structureFile, numOfClassifier, attributes, attributeIndex);
		total = sumCount_A(count, numOfClassifier);
		entropy_A = entropyA(count, total, numOfClassifier);
		totalEntropy = totalEntropy(entropy_A, count.length);
//		for(int i = 0; i < count.length; i++)
//			for(int j = 0; j < count[0].length; j++ )
//				System.out.println(attributes[i]+" "+count[i][j]);
			
		return totalEntropy;
	}
	
	private static double[] count(List<String[]>  dataSet, List<String[]> structureFile, int numOfClassifier){
		double[] count = new double[numOfClassifier];
		for(int i = 0; i < numOfClassifier; i++){
			for(int j = 0; j < dataSet.size(); j++){
				if(dataSet.get(j)[dataSet.get(j).length - 1].equals(structureFile.get(structureFile.size()-1)[i])){
					count[i]++;
				}
			}
		}
		return count;
	}
	
	private static double[][] countAttributes(List<String[]>  dataSet, List<String[]> structureFile, int numOfClassifier, String[] attributes, int attributeIndex){
		double[][] count = new double[attributes.length][numOfClassifier];
		for(int i = 0; i < dataSet.size(); i++){
			for(int j = 0; j < attributes.length; j++){
				for(int k = 0; k < numOfClassifier; k++){
					if(dataSet.get(i)[dataSet.get(i).length - 1].equals(structureFile.get(structureFile.size()-1)[k]) //PlayTennis = No/Yes
							&& dataSet.get(i)[attributeIndex - 1].equals(attributes[j])){ // Wind = Weak/Strong dataSet.get(j)[0]<-change the 0 to a variable
						count[j][k]++;
					}
				}
			}
		}

		return count;
	}
	
	private static double sumCount_S(double[] count, int numOfClassifier){
		double total = 0;
		for(int i = 0; i < numOfClassifier; i++){
			total += count[i];
		}
		return total;
	}
	
	private static double sumCount_A(double[][] count, int numOfClassifier){
		double total = 0;
		for(int i = 0; i < count.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				total += count[i][j];
			}
		}
		return total;
	}
	
	
	private static double[] entropyS(double[] count, double total, int numOfClassifier){
		double[] entropy = new double[numOfClassifier];
		
		for(int i = 0; i < numOfClassifier; i++){
			entropy[i] = entropy(count[i]/total);
		}
		return entropy;
	}
	
	private static double[] entropyA(double[][] count, double total, int numOfClassifier){
		double[] entropy = new double[count.length];
		double[] totalAttribute = new double[count.length];
		
		for(int i = 0; i < count.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				totalAttribute[i] += count[i][j];
			}
		}
		
		for(int i = 0; i < count.length; i++){
			for(int j = 0; j < numOfClassifier; j++){
				entropy[i] += (totalAttribute[i]/total)*(entropy(count[i][j]/totalAttribute[i]));
			}
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
}


//double i = 0, j = 0, k = 0, l = 0;
//for(int a = 0; a < entries.size(); a++){
//	if(entries.get(a)[6].equals("unacc")){
//		i++;
//		//System.out.println(entries.get(i)[6]);
//	}else if(entries.get(a)[6].equals("acc")){
//		j++;
//	}else if(entries.get(a)[6].equals("good")){
//		k++;
//	}else{
//		l++;
//	}
//}
////System.out.println(i +" "+ j +" "+ k + " " + l);
////System.out.println(Math.log10(0.7));
//double entropy = -(i/(i+j+k+l))*Math.log10(i/(i+j+k+l));;
//return entropy;