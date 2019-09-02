package tool_package.violent_test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

public class DecryptAndEncrypt {
	
	public String sha1(String strToSha1) {
		return DigestUtils.sha1Hex(strToSha1);
	}
	
	public String sha1(byte[] stringAsBytearray) {
	    MessageDigest md = null;

	    try
	    {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch (NoSuchAlgorithmException e)
	    {
//	        Logger.WriteLog(e.toString());
	    	e.printStackTrace();
	    }

	    if (md == null)
	        return null;


	    md.reset();
	    md.update(stringAsBytearray);

	    byte[] byteData = md.digest();
	    StringBuilder sb = new StringBuilder();

	    for (byte currByte : byteData)
	        sb.append(Integer.toString((currByte & 0xff) + 0x100, 16).substring(1));

	    return sb.toString();
	}
	
	public String stringToUnhex(String strToUnhex) {
		try {
			byte[] bytes = Hex.decodeHex(strToUnhex.toCharArray());
			return new String(bytes, "UTF-8");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public byte[] unhex(String strToUnhex) {
		try {
			return DatatypeConverter.parseHexBinary(strToUnhex);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
//	public static void main(String[] args) {
//		DecryptAndEncrypt dae = new DecryptAndEncrypt();
//		System.out.println(dae.unhex("95"));
//	}

}
