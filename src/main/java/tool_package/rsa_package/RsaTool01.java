package tool_package.rsa_package;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import com.sun.jersey.core.util.Base64;

public class RsaTool01 {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException {
		RsaTool01 r1 = new RsaTool01();
		RsaTool01.generateKeys("RSA", 1024);
		r1.genrateRsaPublicKeyInHexString();
		r1.generateRsaPublicKeyAsByte();
	}

	public void generateRsaPublicKeyAsByte() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		byte[] publicKey = keyGen.genKeyPair().getPublic().getEncoded();
		StringBuffer retString = new StringBuffer();
		retString.append("[");
		for (int i = 0; i < publicKey.length; ++i) {
			retString.append(publicKey[i]);
			retString.append(", ");
		}
		retString = retString.delete(retString.length() - 2, retString.length());
		retString.append("]");
		System.out.println(retString); // e.g. [48, 92, 48, .... , 0, 1]
		System.out.println(new String(publicKey, StandardCharsets.UTF_16));
	}

	public void genrateRsaPublicKeyInHexString() throws NoSuchAlgorithmException {
		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
		keyGen.initialize(1024);
		byte[] publicKey = keyGen.genKeyPair().getPublic().getEncoded();
		StringBuffer retString = new StringBuffer();
		for (int i = 0; i < publicKey.length; ++i) {
			retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
		}
		System.out.println(retString);
	}

	private static void generateKeys(String keyAlgorithm, int numBits) {
		try {
			// Get the public/private key pair
			KeyPairGenerator keyGen = KeyPairGenerator.getInstance(keyAlgorithm);
			keyGen.initialize(numBits);
			KeyPair keyPair = keyGen.genKeyPair();
			PrivateKey privateKey = keyPair.getPrivate();
			PublicKey publicKey = keyPair.getPublic();

			System.out.println("\n" + "Generating key/value pair using " + privateKey.getAlgorithm() + " algorithm");

			// Get the bytes of the public and private keys
			byte[] privateKeyBytes = privateKey.getEncoded();
			byte[] publicKeyBytes = publicKey.getEncoded();

			// Get the formats of the encoded bytes
//			String formatPrivate = privateKey.getFormat(); // PKCS#8
//			String formatPublic = publicKey.getFormat(); // X.509

//			System.out.println("Private Key : " + Base64.encode(String.valueOf(privateKeyBytes)));
			System.out.println("Private Key : " + new String(Base64.encode(String.valueOf(privateKeyBytes))));
			System.out.println("Public Key : " + Base64.encode(String.valueOf(publicKeyBytes)));

			// The bytes can be converted back to public and private key objects
			KeyFactory keyFactory = KeyFactory.getInstance(keyAlgorithm);
			EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
			PrivateKey privateKey2 = keyFactory.generatePrivate(privateKeySpec);

			EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
			PublicKey publicKey2 = keyFactory.generatePublic(publicKeySpec);

			// The original and new keys are the same
			System.out.println("  Are both private keys equal? " + privateKey.equals(privateKey2));
			System.out.println("  Are both public keys equal? " + publicKey.equals(publicKey2));
		} catch (InvalidKeySpecException specException) {
			System.out.println("Exception");
			System.out.println("Invalid Key Spec Exception");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Exception");
			System.out.println("No such algorithm: " + keyAlgorithm);
		}
	}
}
