package util;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PasswordUtil {
	private static final byte[] salt = "\u00e0\u004f\u00d0\u0020\u00ea\u003a\u0069\u0010\u00a2\u00d8\u0008\u0000\u002b\u0030\u0030\u009d".getBytes();
    /**
     * Encrypts the string along with salt 
     * @param password
     * @return
     * @throws Exception
     */
  public static String encrypt(String pwd) {  
	
	  Base64 converter = new Base64();
	  // [Debug]
	  //System.out.println(new String(converter.encode(salt)));	  
	  KeySpec spec = new PBEKeySpec(pwd.toCharArray(), salt, 65536, 128);
	  try {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		byte[] hash = factory.generateSecret(spec).getEncoded();
		return new String(converter.encode(hash));
		
	} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return null;
  }

  //[Debug]
  public static void main(String[] args) {
	  System.out.println("222 = " + encrypt("222"));
	  // SDLBQOfojzbNQmN3B3HqlA==
	  //System.out.println("decrypt = " + decrypt(encrypt("000")));
  }
}
