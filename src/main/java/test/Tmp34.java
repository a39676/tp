package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Tmp34 {

	public static void main(String[] args) throws IOException {
		String script = "curl -H \"Content-Type: application/json\" -H \"token: eyJhbGciOiJIUzUxMiJ9.eyJvcmdJZCI6ImQ1YmNiZjhjLWU1OTUtNDBmZi05YzBjLWYzOWE1ZDAyZjFiYyIsInVzZXJJZCI6IjM0YTI3M2Y2LWU4MDgtNDgzYi1iMTAxLTk4NDczM2QxMDIzOCIsImNsdXN0ZXJJZCI6Imh1YXdlaS1pdGFpIiwiZXhwIjoxNzQwNDYwMTUwfQ.mn0uhJ6XgpD-1MB0o1ncLYhNXirlmKZgua41UKiKGBDRdzdPZyOM10jqIOSVBqMx_y0b-7iDftV2rOC9i7nx0g\" -H \"source: 501\" --request GET \"https://api-phx-hw02.yunxuetang.cn/ote/upm/list/nocount?status=1&name=&offset=0&limit=12&oteGroupFuncReq=0\"";
		ProcessBuilder pb = new ProcessBuilder(script.split(" "));
		Process p = pb.start();

		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder responseStrBuilder = new StringBuilder();

		String line = new String();

		while ((line = br.readLine()) != null) {
			System.out.println("read line from curl command: " + line);
			responseStrBuilder.append(line);
		}
	}
}
