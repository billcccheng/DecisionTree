
public class InformationGain {
	public static int highestGain(double[] attributeEntropy){
		int highestGainAttribute = 0;
		double temp = Double.MAX_VALUE;
		for(int i = 0; i < attributeEntropy.length; i++){
			if(attributeEntropy[i] < temp){
				temp = attributeEntropy[i];
				highestGainAttribute = i; 
			}
		}
		return highestGainAttribute;
	}
}
