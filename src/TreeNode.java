import java.util.List;
import java.util.ArrayList;

public class TreeNode{
    List<TreeNode> children = null;
    List<String> attributeToBeCompared;
    int attributeIndex;
    String attributeName;
    
    public TreeNode(int attributeIndex, List<String> attributeToBeCompared, String attributeName){
        this.children = new ArrayList<TreeNode>();
        this.attributeIndex = attributeIndex;
        this.attributeToBeCompared = attributeToBeCompared;
        this.attributeName = attributeName;
    }

    public void addChild(TreeNode child){
        children.add(child);
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