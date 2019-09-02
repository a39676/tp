package useTools;

import java.io.File;

public class TmpTool {

	public static void main(String[] args) {
		File mainFolder = new File("D:\\workP1\\toolProjectG\\src\\libs");
		
		for(File file : mainFolder.listFiles()) {
			System.out.println(file.getAbsolutePath());
		}
	}

}
