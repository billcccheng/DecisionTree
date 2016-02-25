import java.util.List;
import java.util.ArrayList;

public class TreeNode{
	
    List<TreeNode> children;
    List<String> attributeToBeCompared;
    List<Integer> attributeIndex;
    List<String> attributeIndexName;
    String attributeName;
    
    public TreeNode(List<Integer> attributeIndex, List<String> attributeIndexName, List<String> attributeToBeCompared, String attributeName){
        this.children = new ArrayList<TreeNode>();
        this.attributeIndex = attributeIndex;
        this.attributeIndexName = attributeIndexName;
        this.attributeToBeCompared = attributeToBeCompared;
        this.attributeName = attributeName;
    }

    public void addChild(TreeNode child){
        children.add(child);
    }
    
    public void removeChild(int child){
    	children.remove(child);
    }
    
    public static void traverseTree(TreeNode root, List<String[]> structureFile, List<String[]> dataSet, int level, List<List<Integer>> accuracyTestAttributeIndex, List<List<String>> accuracyTestAttributeToBeCompared){
    	if(root == null || root.children == null)
    		return;
    	
    	if(root.children.isEmpty()){
    		for(int dataSetIndex = 0; dataSetIndex < dataSet.size(); dataSetIndex++){
				boolean found = true;
				for(int index = 0; index < root.attributeIndex.size(); index++){
					if(root.attributeIndex.get(index) != -2){
    					if(dataSet.get(dataSetIndex)[root.attributeIndex.get(index)].equals(root.attributeToBeCompared.get(index))){
    						found = true;
    					}else{
    						found = false;
    						break;
    					}
					}
				}
				if(found){
					root.attributeIndexName.add(dataSet.get(dataSetIndex)[dataSet.get(dataSetIndex).length-1]);
					root.attributeName = dataSet.get(dataSetIndex)[dataSet.get(dataSetIndex).length-1];
					root.attributeToBeCompared.add(dataSet.get(dataSetIndex)[dataSet.get(dataSetIndex).length-1]);
					accuracyTestAttributeToBeCompared.add(root.attributeToBeCompared);
					accuracyTestAttributeIndex.add(root.attributeIndex);
    				break;
				}
			}
    		if(!root.attributeName.isEmpty()){
    			/* Print out bottom leaves */
    			for(int i = 0; i < level; i++)
    				System.out.print("    ");
    			System.out.println("class = "  + root.attributeName + " " + root.attributeToBeCompared);
    		}

    	}else{
    		/* Print out leaves */
    		for(int i = 0; i < level; i++)
    			System.out.print("    ");
    		System.out.println(root.attributeName + " " + root.attributeToBeCompared );
    	}
    	
    	for(int i = 0; i < root.children.size(); i++){
			TreeNode currentNode = root.children.get(i);
			if(currentNode.attributeIndex.get(currentNode.attributeIndex.size()-1) != -1){
				traverseTree(currentNode, structureFile, dataSet, level+1, accuracyTestAttributeIndex, accuracyTestAttributeToBeCompared);
			}
    	}
    }
    
    
    public static double testAccuracy(TreeNode root, List<String[]> structureFile, List<String[]> dataSet, List<List<Integer>> accuracyTestAttributeIndex, List<List<String>> accuracyTestAttributeToBeCompared){
    	int correctAnswer = 0;
    	boolean equals = true; 
    	//root.children.remove(1);
    	for(int i = 0; i < dataSet.size(); i++){
    		 // accuracyTestAttributeIndex.size() = accuracyTestAttributeToBeCompared.size()
    		for(int j = 0; j < accuracyTestAttributeIndex.size(); j++){  // [[sunny, high, no], [sunny, normal, yes]... => [sunny, high, no]
    			equals = true;
    			for(int k = 0; k < accuracyTestAttributeIndex.get(j).size(); k++){  // [sunny, high, no] => sunny..high..no
    				if(accuracyTestAttributeIndex.get(j).get(k) != -2){
    					if(!dataSet.get(i)[accuracyTestAttributeIndex.get(j).get(k)].equals(accuracyTestAttributeToBeCompared.get(j).get(k))){
    						equals = false;
    						break;
    					}
    				} else{
    					if(!dataSet.get(i)[dataSet.get(i).length-1].equals(accuracyTestAttributeToBeCompared.get(j).get(k))){
    						equals = false;
    						break;
    					}
    				}
    			}
    			if(equals)
    				break;
    		}
    		if(equals)
    			correctAnswer++;
    	}
    	int dataSize = dataSet.size();
    	double accuracy = ((double)correctAnswer/dataSize);
    	System.out.println(correctAnswer + " / " + dataSize + " = " + accuracy);
    	
    	return accuracy;
    }   
}
