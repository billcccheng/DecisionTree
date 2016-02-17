
public class InformationGain {
	public static int highestGain(double S, double[] attributeEntropy){
		int highestGainAttribute = 3;
		double temp = 0;
		for(int i = 0; i < attributeEntropy.length; i++){
			if(S - attributeEntropy[i] > temp){
				temp = S - attributeEntropy[i];
				highestGainAttribute = i; 
			}
		}
		return highestGainAttribute;
	}
}
