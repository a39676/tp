package tool_package.violent_test;

import java.io.File;
import java.util.List;
import java.security.MessageDigest;

import org.apache.poi.poifs.crypt.Decryptor;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

public abstract class Chameleon {
	
	protected static DecryptAndEncrypt dae = new DecryptAndEncrypt();
	
	protected String target;
	
	protected Decryptor officeDecryptor;
	
	
	
	
	public void setTarget(String target) {
		this.target = target;
	}

	protected boolean setDecryptor(String filePath)  {
		return false;
	}
	
	public static Chameleon setMatcher(Integer i) {
		switch(i) {
			
		case 1:
			return new ChameleonMysql();
			
		case 2:
			return new ChameleonOffice2007();
			
		case 3:
			return new ChameleonOffice97();
			
		case 4:
			return new ChameleonZip();

		case 5:
			return new ChameleonCustom01();
		
		case 0:
			return new ChameleonTest();

		default:
			return null;
		}
	}
	
	protected boolean matcher(String str) {
		return false;
	}
	
	public String matchers(String str) {
		return null;
	}
	

//	8ejdc89jmjdhhiad
//	9CF8C0EEABE58362D99512EEC8075DE0


	
}



class ChameleonTest extends Chameleon {
	public String matchers(String str) {
		return str;
	}
}


class ChameleonMysql extends Chameleon {
	
	public boolean matcher(String str) {
		return toMysql(str).equals(target.toLowerCase());
	}
	
	private String toMysql(String str) {
		return dae.sha1(dae.unhex(dae.sha1(str)));
	}
	
}


class ChameleonOffice2007 extends Chameleon {
	
	public boolean matcher(String str) {
		try {
			return officeDecryptor.verifyPassword(str);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setDecryptor(String filePath)  {
		try {
			File file = new File(filePath);
			POIFSFileSystem poifsfs = new POIFSFileSystem(file);
			EncryptionInfo info = new EncryptionInfo(poifsfs);
			officeDecryptor = Decryptor.getInstance(info);
			System.out.println("set decryptor office2007");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}


class ChameleonOffice97 extends Chameleon {
	
	public boolean matcher(String str) {
		try {
			return officeDecryptor.verifyPassword(str);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setDecryptor(String filePath)  {
		try {
			File file = new File(filePath);
			if(!file.exists()) {
				System.out.println("file not found");
				return false;
			}
			NPOIFSFileSystem npoifsFileSystem = new NPOIFSFileSystem(file);
			EncryptionInfo info = new EncryptionInfo(npoifsFileSystem);
			officeDecryptor = Decryptor.getInstance(info);
			System.out.println("set decryptor office97");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}


class ChameleonZip extends Chameleon {
	
	ZipFile zipFile = null;
	
	@SuppressWarnings("unchecked")
	public boolean matcher(String str) {
		String tmpPath = "d:/auxiliary/tmp/";
		File tmpFile = null;
		
		try {
		    if (zipFile.isEncrypted()) {
		        zipFile.setPassword(str);
		        List<FileHeader> files = zipFile.getFileHeaders();
		        System.out.println(files);
		        tmpFile = new File(tmpPath + files.get(0).getFileName());
		        zipFile.extractFile(files.get(0).getFileName(), tmpPath);
		        if(tmpFile.exists()) {
		        	return true;
		        } else {
		        	return false;
		        }
		    } else {
		    	return true;
		    }
		} catch (ZipException e) {
		    e.printStackTrace();
		    return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean setDecryptor(String filePath)  {
		try {
			this.zipFile = new ZipFile(filePath);
			if(!zipFile.isValidZipFile()) {
				System.out.println("is not a zip file");
				return false;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}

class ChameleonCustom01 extends Chameleon {
	
	public boolean matcher(String str) {
		return toOrg(str).equals(target);
	}
	
	private String toOrg(String str) {
		StringBuilder sb = new StringBuilder();
		byte[] result = str.getBytes();
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(result);
			for (byte by : md5.digest()) {
				sb.append(String.format("%02X", by));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	
}
