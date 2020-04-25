package test;

import java.io.IOException;

public class TmpTest9 {

	public static void main(String[] args) throws IOException {
		String str = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAAD/2";
		str = str.substring(0, 30);
		System.out.println(str);
		int slashIndex = str.indexOf("/");
		int semicolonIndex = str.indexOf(";");
		System.out.println(str.substring(slashIndex + 1, semicolonIndex));
	}

}