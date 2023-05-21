package cu.edu.cujae.bd.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

public class Encription {
	public static String getMd5(String chain){
		 String encriptation = null; 
		 MessageDigest md;
		try {
			md = MessageDigest.getInstance(MessageDigestAlgorithms.MD5);
		    md.update(chain.getBytes());
		    byte[] key = md.digest();
	  
		    encriptation = new String(Base64.encodeBase64(key));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return encriptation;
	}

}
