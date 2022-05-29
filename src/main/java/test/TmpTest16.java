package test;

public class TmpTest16 {

	public static void main(String[] args) throws Exception {

		String str = "data:image/jpeg;base64,/9j/4AAQSkZJRgABAQAAAQABAA";
		
		System.out.println(str.contains("base64,"));
		
		System.out.println(str.indexOf("base64,"));
		System.out.println(str.indexOf("data:image/"));
		
		System.out.println(str.substring(11, str.indexOf("base64,") - 1));
	}
}
