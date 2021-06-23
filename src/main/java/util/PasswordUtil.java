package util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class PasswordUtil {
	private static final String SECRET_KEY = "NlLAFrIjMx2IYZrVHHmfrAdborlD/5qJ";
	//private static final byte[] SALT = "\u00e0\u004f\u00d0\u0020\u00ea\u003a\u0069\u0010\u00a2\u00d8\u0008\u0000\u002b\u0030\u0030\u009d".getBytes();
	//private static final String SALT = "\u00e0\u004f\u00d0\u0020\u00ea\u003a\u0069\u0010\u00a2\u00d8\u0008\u0000\u002b\u0030\u0030\u009d";
	private static final String SALT = "QCVJ9H7TDgT16K4N8aFNpASDLBQOfojz";
	/**
	 * Encrypts the string along with salt
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String pwd) {
		Base64 converter = new Base64();
		try {
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
			return converter.encodeToString(cipher.doFinal(pwd.getBytes(StandardCharsets.UTF_8)));
		} catch (Exception e) {
			System.out.println("Error while encrypting: " + e.toString());
		}
		return null;
	}

	public static String decrypt(String pwdToDecrypt) {
		try {
			byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			IvParameterSpec ivspec = new IvParameterSpec(iv);

			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey, ivspec);
			return new String(cipher.doFinal(Base64.decodeBase64(pwdToDecrypt)));
		} catch (Exception e) {
			System.out.println("Error while decrypting: " + e.toString());
		}
		return null;
	}

	/**
	 * Hash password in database
	 * @param input
	 * @return hashed password stored in database
	 */
	public static String sha256(String input) {

		String sha256 = null;

		if(null == input) return null;

		try {

			//Create MessageDigest object for MD5
			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			//Update input string in message digest
			String hash = input + SALT;
			//	digest.update(input.getBytes(), 0, input.length());
			digest.update(hash.getBytes(), 0, hash.length());

			//Converts message digest value in base 16 (hex)
			sha256 = new BigInteger(1, digest.digest()).toString(16);

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
		return sha256;
	}



	//[Debug]
	public static void main(String[] args) {
		System.out.println("user2 = " + encrypt("user2"));
		System.out.println("000 - in hex: " + sha256("000"));
	}
}
