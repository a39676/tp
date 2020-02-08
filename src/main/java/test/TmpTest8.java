package test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.util.Base64;

public class TmpTest8 {

	public static void main(String[] args) throws Exception {
		System.out.println("s");
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		kpg.initialize(4096);
		KeyPair kp = kpg.generateKeyPair();

		System.out.println("-----BEGIN PRIVATE KEY-----");
		System.out.println(Base64.getMimeEncoder().encodeToString(kp.getPrivate().getEncoded()));
		System.out.println("-----END PRIVATE KEY-----");
		System.out.println("-----BEGIN PUBLIC KEY-----");
		System.out.println(Base64.getMimeEncoder().encodeToString(kp.getPublic().getEncoded()));
		System.out.println("-----END PUBLIC KEY-----");
	}

}
