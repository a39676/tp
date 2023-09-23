package test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Md5Hash {
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		getMd5Hash();
	}

	public static void getMd5Hash() throws NoSuchAlgorithmException, IOException {
		String mainFolderPathStr = "d:/tmp/pt";
		File folder = new File(mainFolderPathStr);
		MessageDigest md = MessageDigest.getInstance("MD5");

		File[] files = folder.listFiles();
		for (File f : files) {
			md.update(Files.readAllBytes(Paths.get(f.getAbsolutePath())));
			byte[] digest = md.digest();
			String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
			System.out.println(f.getName() + ", " + myChecksum);
			System.out.println();
		}

	}
}
