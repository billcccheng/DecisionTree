import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args){
		int numberOfAttributes;
		String fileName = readFile();
		
		
		Scanner in = new Scanner(System.in);
		System.out.print("Number of attributes:");
		numberOfAttributes = in.nextInt();
		
		try {
			ParseText.read(numberOfAttributes, fileName);
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			in.close();
		}	
		
		
	}
	
	public static String readFile(){
		String fileName;
		File file = new File("");
		
		Scanner in = new Scanner(System.in);
		System.out.print("File Name:");
		fileName = in.nextLine();
		file = new File(fileName);
		
		while(!file.exists()){
			System.out.print("Wrong file name! File Name:");
			fileName = in.nextLine();
			file = new File(fileName);
		}
		
		return fileName;
	}
}


//TreeNode root = new TreeNode("root");
//root.addChild(new TreeNode("1: child1"));
//root.addChild(new TreeNode("1: child2"));
//root.children.get(0).addChild(new TreeNode("2: child1"));
//root.setValue("test");
//root = root.children.get(0);