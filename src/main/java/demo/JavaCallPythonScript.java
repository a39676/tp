package demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

public class JavaCallPythonScript {

	public static void main(String[] args) throws IOException, URISyntaxException {

		String s = null;

		try {

			// run the Unix "ps -ef" command
			// using the Runtime exec method:
			;
			Process p = Runtime.getRuntime().exec("python " + System.getProperty("user.home") + "/tmp/tmp2.py a b c");

			BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));

			BufferedReader stdError = new BufferedReader(new InputStreamReader(p.getErrorStream()));

			// read the output from the command
			System.out.println("Here is the standard output of the command:\n");
			while ((s = stdInput.readLine()) != null) {
				System.out.println(s);
			}

			// read any errors from the attempted command
			System.out.println("Here is the standard error of the command (if any):\n");
			while ((s = stdError.readLine()) != null) {
				System.out.println(s);
			}

			System.exit(0);
		} catch (IOException e) {
			System.out.println("exception happened - here's what I know: ");
			e.printStackTrace();
			System.exit(-1);
		}
	}
}

/*
 * import sys
 * 
 * print("1")
 * 
 * def f1(str1, str2, str3): print(str1) print(str2) print(str3)
 * 
 * if __name__ == "__main__": str1 = sys.argv[1] str2 = sys.argv[2] str3 =
 * sys.argv[3] print(f1(str1, str2, str3))
 */
