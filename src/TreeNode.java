import java.util.List;
import java.util.ArrayList;

public class TreeNode{
    List<TreeNode> children = null;
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
    
    public static void traverseTree(TreeNode root, List<String[]> structureFile, List<String[]> dataSet){
    	//System.out.println("-------------");
    	if(root.children.isEmpty()){
	    	for(int index = 0; index < root.attributeIndex.size(); index++){
	    		if(root.attributeIndex.get(index) == -2){
	    			for(int dataSetIndex = 0; dataSetIndex < dataSet.size(); dataSetIndex++){
	    				boolean found = true;
						for(int subIndex = 0; subIndex < root.attributeIndex.size(); subIndex++){
	    					if(root.attributeIndex.get(subIndex) != -2){
		    					if(dataSet.get(dataSetIndex)[root.attributeIndex.get(subIndex)].equals(root.attributeToBeCompared.get(subIndex))){
		    						found = true;
		    					}else{
		    						found = false;
		    						break;
		    					}
	    					}
	    				}
	    				if(found){
	    					root.attributeIndexName.add(dataSet.get(dataSetIndex)[dataSet.get(dataSetIndex).length-1]);
		    				break;
	    				}
	    			}
	    		}else{
	    			//root.attributeIndexName.add(structureFile.get(0)[root.attributeIndex.get(index)]);
	    			//System.out.print(structureFile.get(0)[root.attributeIndex.get(index)] + "[" + root.attributeToBeCompared.get(index) + "]" +",");
	    		}
	    	}
	    	
	    	System.out.println(root.attributeIndexName);
	    	System.out.println(root.attributeToBeCompared);
	    	System.out.println();
    	}
    	
//    	System.out.println(root.attributeIndex);
//    	if(root.children.isEmpty())
//    		root.attributeToBeCompared.add("Yes/No");
//    	System.out.println(root.attributeToBeCompared);

    	
    	for(int i = 0; i < root.children.size(); i++){
			TreeNode currentNode = root.children.get(i);
			if(currentNode.attributeIndex.get(currentNode.attributeIndex.size()-1) != -1){
				traverseTree(currentNode, structureFile, dataSet);
			}
    	}
    }

}


//public class TreeNode {
//	int val;
//	TreeNode left;
//	TreeNode right;
//	TreeNode(int val){
//		this.val = val; 
//	}
//}