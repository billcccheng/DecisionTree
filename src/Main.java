public class Main {
	public static void main(String[] args){
		TreeNode root = new TreeNode("root");
	    root.addChild(new TreeNode("1: child1"));
	    root.addChild(new TreeNode("1: child2"));
	    root.children.get(0).addChild(new TreeNode("2: child1"));
//	    root.setValue("test");
	    root = root.children.get(0);
		System.out.println(root.children.get(0).getValue());
	}
}
