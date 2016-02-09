import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		int numberOfAttributes;
		List<String[]> myEntries = null;
		String fileName = ReadFile.read();
		
		Scanner in = new Scanner(System.in);
		System.out.print("Number of attributes:");
		numberOfAttributes = in.nextInt();
		try {
			myEntries = ReadFile.readParsedInformation(numberOfAttributes, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			in.close();
		}
		
		
	}
}


//TreeNode root = new TreeNode("root");
//root.addChild(new TreeNode("1: child1"));
//root.addChild(new TreeNode("1: child2"));
//root.children.get(0).addChild(new TreeNode("2: child1"));
//root.setValue("test");
//root = root.children.get(0);