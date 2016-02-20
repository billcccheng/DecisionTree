import java.util.List;
import java.util.ArrayList;

public class TreeNode{
    List<TreeNode> children = null;
    List<String> attributeToBeCompared;
    List<Integer> attributeIndex;
    String attributeName;
    
    public TreeNode(List<Integer> attributeIndex, List<String> attributeToBeCompared, String attributeName){
        this.children = new ArrayList<TreeNode>();
        this.attributeIndex = attributeIndex;
        this.attributeToBeCompared = attributeToBeCompared;
        this.attributeName = attributeName;
    }

    public void addChild(TreeNode child){
        children.add(child);
    }
    
    public static void traverseTree(TreeNode root, List<String[]> structureFile, List<String[]> dataSet){
    	if(root == null)
    		return;
    	System.out.println("-------------");
    	for(int i : root.attributeIndex)
    		System.out.print(structureFile.get(0)[i] + ", ");
    	//for(int i = 0; i < dataSet.size())
    	System.out.println(root.attributeToBeCompared);

    	
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