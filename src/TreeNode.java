import java.util.List;
import java.util.ArrayList;

public class TreeNode{
    List<TreeNode> children = null;
    String value;

    public TreeNode(String value){
        this.children = new ArrayList<TreeNode>();
        this.value = value;
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