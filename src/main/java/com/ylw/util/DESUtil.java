package com.ylw.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESUtil {
	// 算法名称
	public static final String KEY_ALGORITHM = "DES";
	// 算法名称/加密模式/填充方式
	// DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	
	private static final char[] legalChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/"
            .toCharArray();

	/***
	 * MD5加码 生成32位md5码
	 */
	public static String string2MD5(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16)
				hexValue.append("0");
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 
	 * 生成密钥key对象
	 * 
	 * @param KeyStr
	 *            密钥字符串
	 * @return 密钥对象
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeySpecException
	 * @throws Exception
	 */
	private static SecretKey keyGenerator(String keyStr) throws Exception {
		byte input[] = HexString2Bytes(keyStr);
		DESKeySpec desKey = new DESKeySpec(input);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(desKey);
		return securekey;
	}

	private static int parse(char c) {
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	// 从十六进制字符串到字节数组转换
	public static byte[] HexString2Bytes(String hexstr) {
		byte[] b = new byte[hexstr.length() / 2];
		int j = 0;
		for (int i = 0; i < b.length; i++) {
			char c0 = hexstr.charAt(j++);
			char c1 = hexstr.charAt(j++);
			b[i] = (byte) ((parse(c0) << 4) | parse(c1));
		}
		return b;
	}

	/**
	 * 加密数据
	 * 
	 * @param data
	 *            待加密数据
	 * @param key
	 *            密钥
	 * @return 加密后的数据
	 */
	public static String encrypt(String data, String key) throws Exception {
		Key deskey = keyGenerator(key);
		// 实例化Cipher对象，它用于完成实际的加密操作
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		SecureRandom random = new SecureRandom();
		// 初始化Cipher对象，设置为加密模式
		cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
		byte[] results = cipher.doFinal(data.getBytes());
		// 该部分是为了与加解密在线测试网站（http://tripledes.online-domain-tools.com/）的十六进制结果进行核对
		/*for (int i = 0; i < results.length; i++) {
			System.out.print(results[i] + " ");
		}
		System.out.println();*/
		// 执行加密操作。加密后的结果通常都会用Base64编码进行传输
		 return Base64.encodeBase64String(results);
	}

	/**
	 * 解密数据
	 * 
	 * @param data
	 *            待解密数据
	 * @param key
	 *            密钥
	 * @return 解密后的数据
	 */
	public static String decrypt(String data, String key) throws Exception {
		Key deskey = keyGenerator(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		// 初始化Cipher对象，设置为解密模式
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		// 执行解密操作
		return new String(cipher.doFinal(Base64.decodeBase64(data)));
	}
	/**
	 * IOS解密
	 * @param decryptString
	 * @param decryptKey
	 * @return
	 * @throws Exception
	 */
	public static String IOSdecryptDES(String decryptString, String decryptKey)
	             throws Exception {
		     byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };   
	         byte[] byteMi = decode(decryptString);
	         IvParameterSpec zeroIv = new IvParameterSpec(iv);
	         SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
	         Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	         cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
	         byte decryptedData[] = cipher.doFinal(byteMi);
	 
	         return new String(decryptedData);
	  }
	 public static byte[] decode(String s) {
         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         try {
             decode(s, bos);
         } catch (IOException e) {
             throw new RuntimeException();
         }
         byte[] decodedBytes = bos.toByteArray();
         try {
             bos.close();
             bos = null;
         } catch (IOException ex) {
             System.err.println("Error while decoding BASE64: " + ex.toString());
         }
         return decodedBytes;
     }
     private static void decode(String s, OutputStream os) throws IOException {
         int i = 0;
         int len = s.length();
         while (true) {
             while (i < len && s.charAt(i) <= ' ')
                 i++;
             if (i == len)
                 break;
             int tri = (decode(s.charAt(i)) << 18)
                     + (decode(s.charAt(i + 1)) << 12)
                     + (decode(s.charAt(i + 2)) << 6)
                     + (decode(s.charAt(i + 3)));
 
             os.write((tri >> 16) & 255);
             if (s.charAt(i + 2) == '=')
                 break;
             os.write((tri >> 8) & 255);
             if (s.charAt(i + 3) == '=')
                 break;
             os.write(tri & 255);
             i += 4;
         }
     }
     private static int decode(char c) {
         if (c >= 'A' && c <= 'Z')
             return ((int) c) - 65;
         else if (c >= 'a' && c <= 'z')
             return ((int) c) - 97 + 26;
         else if (c >= '0' && c <= '9')
             return ((int) c) - 48 + 26 + 26;
         else
             switch (c) {
             case '+':
                 return 62;
             case '/':
                 return 63;
             case '=':
                 return 0;
             default:
                 throw new RuntimeException("unexpected code: " + c);
             }
     }
     /**
      * IOS 加密
      */
     private static byte[] iv = { 1, 2, 3, 4, 5, 6, 7, 8 };
     public static String IOSencryptDES(String encryptString, String encryptKey)
             throws Exception {
         IvParameterSpec zeroIv = new IvParameterSpec(iv);
         SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
         Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
         cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
         byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
         return IOSEncode(encryptedData);
     }
     /**
      * data[]进行编码 IOS
      * @param data
      * @return
 */
     public static String IOSEncode(byte[] data) {
         int start = 0;
         int len = data.length;
         StringBuffer buf = new StringBuffer(data.length * 3 / 2);
         int end = len - 3;
         int i = start;
         int n = 0;
         while (i <= end) {
             int d = ((((int) data[i]) & 0x0ff) << 16)
                     | ((((int) data[i + 1]) & 0x0ff) << 8)
                     | (((int) data[i + 2]) & 0x0ff);

             buf.append(legalChars[(d >> 18) & 63]);
             buf.append(legalChars[(d >> 12) & 63]);
             buf.append(legalChars[(d >> 6) & 63]);
             buf.append(legalChars[d & 63]);

             i += 3;

             if (n++ >= 14) {
                 n = 0;
                 buf.append(" ");
             }
         }
         if (i == start + len - 2) {
             int d = ((((int) data[i]) & 0x0ff) << 16)
                     | ((((int) data[i + 1]) & 255) << 8);

             buf.append(legalChars[(d >> 18) & 63]);
             buf.append(legalChars[(d >> 12) & 63]);
             buf.append(legalChars[(d >> 6) & 63]);
             buf.append("=");
         } else if (i == start + len - 1) {
             int d = (((int) data[i]) & 0x0ff) << 16;

             buf.append(legalChars[(d >> 18) & 63]);
             buf.append(legalChars[(d >> 12) & 63]);
             buf.append("==");
         }
         return buf.toString();
     }
	public static void main(String[] args) throws Exception {
		String source = "{\"id\":\"1\",\"itemid\":\"1\",\"status\":\"10\",\"recmobile\":\"13811111111\",\"recname\":\"张峰\",\"berecmobile\":\"13822222222\",\"berecname\":\"肖\",\"recid\":\"1\"}{\"id\":\"1\",\"itemid\":\"1\",\"status\":\"10\",\"recmobile\":\"13811111111\",\"recname\":\"张峰\",\"berecmobile\":\"13822222222\",\"berecname\":\"肖\",\"recid\":\"1\"}{\"id\":\"1\",\"itemid\":\"1\",\"status\":\"10\",\"recmobile\":\"13811111111\",\"recname\":\"张峰\",\"berecmobile\":\"13822222222\",\"berecname\":\"肖\",\"recid\":\"1\"}";
	    source = "123456";
		System.out.println("原文: " + source);
		String key = DESUtil.string2MD5("daile123");
		String encryptData = encrypt(source, key);
		System.out.println("加密后: " + encryptData);
		String decryptData = decrypt(encryptData, key);
		System.out.println("解密后: " + decryptData);
	}
}