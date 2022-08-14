package test;

import java.util.Base64;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {

		String encodedString = "Z3VvcnVpMjMzMzMz";
		byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
		String decodedString = new String(decodedBytes);
		System.out.println(decodedString);

		encodedString = "bHJqMDA3QGdtYWlsLmNvbQ==";
		decodedBytes = Base64.getDecoder().decode(encodedString);
		decodedString = new String(decodedBytes);
		System.out.println(decodedString);

	}
}
