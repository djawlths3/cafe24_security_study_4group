import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class HashFunctionSalt {
	public static void main(String[] args) {
		String password = "5048";
		byte[] salt = "salt".getBytes(); 
		
		System.out.println("< 솔트 없이 암호화 >");
		bruteForce(getEncryptString(getHashWithoutSalt(password)));
		
		System.out.println("< 솔트 넣어서 암호화 >");
		bruteForce(getEncryptString(getHashWithSalt(password, salt)));

	}
	
	// 해시 암호문 문자열로 뽑아내기
	public static String getEncryptString(byte[] encrypt) {
		StringBuilder sb = new StringBuilder("");
		for(int i = 0; i < encrypt.length; i++) {
			String hexString = Integer.toHexString(encrypt[i] & 0xff);
			while(hexString.length() < 2) {
				hexString = "0" + hexString;
			}
			sb.append(hexString);
		}
		return sb.toString();
	}
	
	// 솔트 없이 암호화
	public static byte[] getHashWithoutSalt(String password) {
		MessageDigest digest;
		byte[] encrypt = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			encrypt = digest.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrypt;
	}
	
	// 솔트 넣어서 암호화
	public static byte[] getHashWithSalt(String password, byte[] salt) {
		MessageDigest digest;
		byte[] encrypt = null;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			digest.reset();
			digest.update(salt);
			encrypt = digest.digest(password.getBytes("UTF-8"));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encrypt;
	}
	
	// 무작위 공격
	public static void bruteForce(String encryptPassword) {
		System.out.println("=== 무작위 공격 ===");
		int i = 0;
		for(i = 0; i < 10000; i++) {
			String encryptElement = getEncryptString(getHashWithoutSalt(String.valueOf(i)));
			if(encryptPassword.equals(encryptElement)) {
				System.out.printf("비밀번호 찾음: %d\n해시값: %s\n", i, encryptElement);
				break;
			}
		}
		if(i == 10000) {
			System.out.println("비밀번호 찾지 못함");
		}
		System.out.println("===================\n");
	}

}
