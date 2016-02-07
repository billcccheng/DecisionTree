import java.util.List;
import java.util.ArrayList;

public class TreeNode{
    List<TreeNode> children = null;
    private String value;

    public TreeNode(String value){
        this.children = new ArrayList<TreeNode>();
        this.setValue(value);
    }

    public void addChild(TreeNode child){
        children.add(child);
    }

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
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