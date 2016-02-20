
public class InformationGain {
	public static int highestGain(double[] attributeEntropy){
		int highestGainAttribute = 0;
		int zeroCount = 0;
		int maxCount = 0;
		double temp = Double.MAX_VALUE;
		
		for(int i = 0; i < attributeEntropy.length; i++){
			if(attributeEntropy[i] == 0)
				zeroCount++;
			else if(attributeEntropy[i] == Double.MAX_VALUE)
				maxCount++;
		}
		
		
		for(int i = 0; i < attributeEntropy.length; i++){
			if(attributeEntropy[i] < temp){
				temp = attributeEntropy[i];
				highestGainAttribute = i; 
			}
		}
		
		if(maxCount + zeroCount == attributeEntropy.length)
			return -1;
		else
			return highestGainAttribute;
	}
}
