import java.util.List;


public class EntropyCalculation {
	int numOfClassifier;
	public double calculateEntropy_S(List<String[]>  dataSet, List<String[]> structureFile){
		int numOfClassifier = structureFile.get(structureFile.size()-1).length;
		double[] count = new double[numOfClassifier];
		double[] entropy = new double[numOfClassifier];
		double total = 0;
		double totalEntropy = 0;

		count = count(dataSet, structureFile, numOfClassifier);
		total = sumCount(count, numOfClassifier);
		entropy = individualAttributeEntropy(count, total, numOfClassifier);
		totalEntropy = totalEntropy(entropy, numOfClassifier);
		
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
	
	private static double sumCount(double[] count, int numOfClassifier){
		double total = 0;
		for(int i = 0; i < numOfClassifier; i++){
			total += count[i];
		}
		return total;
	} 
	
	private static double[] individualAttributeEntropy(double[] count, double total, int numOfClassifier){
		double[] entropy = new double[numOfClassifier];
		for(int i = 0; i < numOfClassifier; i++){
			entropy[i] = entropy(count[i]/total);
		}
		return entropy;
	}
	
	private static double entropy(double x){
		double answer = -Math.log(x)/Math.log(2);
		answer *= x;
		return answer;
	}
	
	private static double totalEntropy(double[] entropy, int numOfClassifier){
		double totalEntropy = 0;
		for(int i = 0; i < numOfClassifier; i++){
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