
public class InformationGain {
	public static int highestGain(double[] attributeEntropy){
		int highestGainAttribute = 0;
		int count = 0;
		double temp = Double.MAX_VALUE;
		
		for(int i = 0; i < attributeEntropy.length; i++){
			if(attributeEntropy[i] == 0)
				count++;
		}
		
		for(int i = 0; i < attributeEntropy.length; i++){
			if(attributeEntropy[i] < temp){
				temp = attributeEntropy[i];
				highestGainAttribute = i; 
			}
		}
		
		if(count == attributeEntropy.length - 1)
			return -1;
		else
			return highestGainAttribute;
	}
}
