package test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

public class Tmp21 {

	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
		givenFile_generatingChecksum_thenVerifying();
	}

	public static void givenFile_generatingChecksum_thenVerifying() throws NoSuchAlgorithmException, IOException {
		String mainFolder = "D:/aiArt/stable-diffusion-webui/models/Lora";
		String f1 = mainFolder + "/girlAndShadow_v2.safetensors";
		String f2 = mainFolder + "/girlAndShadow_v10.safetensors";

		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(Files.readAllBytes(Paths.get(f1)));
		byte[] digest = md.digest();
		String myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
		System.out.println(myChecksum);

		md = MessageDigest.getInstance("MD5");
		md.update(Files.readAllBytes(Paths.get(f2)));
		digest = md.digest();
		myChecksum = DatatypeConverter.printHexBinary(digest).toUpperCase();
		System.out.println(myChecksum);

	}
}
