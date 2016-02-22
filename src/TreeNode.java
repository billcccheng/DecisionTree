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
    
    public static void traverseTree(TreeNode root, List<String[]> structureFile, List<String[]> dataSet, int level){
    	if(root.children.isEmpty()){
    		for(int i = 0; i < level; i++)
    			System.out.print("   ");
    		System.out.print(root.attributeName + " ");
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
    				break;
				}
			}
    		for(String element : structureFile.get(structureFile.size() - 1)){
		    	if(root.attributeIndexName.contains(element)){
		    		//for(int i = 0; i < level; i++)
		    		System.out.println(root.attributeIndexName.get(level) + " " + root.attributeToBeCompared);
		    	}
    		}
    	}else{
    		for(int i = 0; i < level; i++)
    			System.out.print("   ");
    		System.out.println(root.attributeName + " " + root.attributeToBeCompared );
    	}
    	
    	for(int i = 0; i < root.children.size(); i++){
			TreeNode currentNode = root.children.get(i);
			if(currentNode.attributeIndex.get(currentNode.attributeIndex.size()-1) != -1){
				traverseTree(currentNode, structureFile, dataSet, level+1);
			}
    	}
    }
    
//    public static String getClassifier(TreeNode root, List<String[]> dataSet){
//    	String attributeName = null;
//    	System.out.println(root.attributeIndex + " " + root.attributeToBeCompared);
//		for(int dataSetIndex = 0; dataSetIndex < dataSet.size(); dataSetIndex++){
//			boolean found = true;
//			if(root.attributeToBeCompared.size() > 0 && root.attributeIndex.size() > 0){
//				for(int index = 0; index < root.attributeToBeCompared.size(); index++){
//					if(dataSet.get(dataSetIndex)[root.attributeIndex.get(index)].equals(root.attributeToBeCompared.get(index))){
//						found = true;
//					}else{
//						found = false;
//						break;
//					}	
//				}
//				if(found){
//					attributeName = dataSet.get(dataSetIndex)[dataSet.get(dataSetIndex).length-1];
//					break;
//				}
//			}
//		}
//		return "class =";
//    }

}
