import java.util.List;
import java.util.ArrayList;

public class TreeNode{
    List<TreeNode> children = null;
    int attributeIndex;

    public TreeNode(int attributeIndex){
        this.children = new ArrayList<TreeNode>();
        this.attributeIndex = attributeIndex;
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