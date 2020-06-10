package com.skeleton.foundation.utils.code;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;

/**
 * 编码工具
 */
public class CodeUtils {

	public static class DES {

		// 算法名称
		public static final String KEY_ALGORITHM = "DES";
		// 算法名称/加密模式/填充方式
		// DES加密模式:ECB(电子密码本模式)、CBC(加密分组链接模式)、CFB(加密反馈模式)、OFB(输出反馈模式)
		// 填充方式:NoPadding(不填充)、Zeros填充(0填充)、PKCS5Padding填充
		public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

		/**
		 *
		 * 生成密钥key对象
		 *
		 * @param keyStr
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
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
			SecretKey secretKey = keyFactory.generateSecret(desKey);
			return secretKey;
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

		private static int parse(char c) {
			if (c >= 'a') {
				return (c - 'a' + 10) & 0x0f;
			}
			if (c >= 'A') {
				return (c - 'A' + 10) & 0x0f;
			}
			return (c - '0') & 0x0f;
		}

		/**
		 * 加密数据
		 *
		 * @param data
		 *            待加密数据,UTF-8
		 * @param key
		 *            密钥,长度16
		 * @return 加密后的数据,UTF-8
		 */
		public static String encryptHex(String data, String key) throws Exception {
			return new String(encrypt(data, key), "UTF-8");
		}

		/**
		 * 加密数据
		 *
		 * @param data
		 *            待加密数据
		 * @param key
		 *            密钥,长度16
		 * @return 加密后的数据,UTF-8
		 */
		public static String encryptHex(byte[] data, String key) throws Exception {
			return new String(encrypt(data, key), "UTF-8");
		}

		/**
		 * 加密数据
		 *
		 * @param data
		 *            待加密数据,UTF-8
		 * @param key
		 *            密钥,长度16
		 * @return 加密后的数据
		 */
		public static byte[] encrypt(String data, String key) throws Exception {
			return encrypt(data.getBytes("UTF-8"), key);
		}

		/**
		 * 加密数据
		 *
		 * @param data
		 *            待加密数据
		 * @param key
		 *            密钥,长度16
		 * @return 加密后的数据
		 */
		public static byte[] encrypt(byte[] data, String key) throws Exception {
			Key deskey = keyGenerator(key);
			// 实例化Cipher对象，它用于完成实际的加密操作
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			SecureRandom random = new SecureRandom();
			// 初始化Cipher对象，设置为加密模式
			cipher.init(Cipher.ENCRYPT_MODE, deskey, random);
			byte[] results = cipher.doFinal(data);
			// 执行加密操作。加密后的结果通常都会用Base64编码进行传输
			return Base64.encodeBase64(results);
		}

		/**
		 * 解密数据
		 *
		 * @param data
		 *            待解密数据,UTF-8
		 * @param key
		 *            密钥,长度16
		 * @return 解密后的数据,UTF-8
		 */
		public static String decryptHex(String data, String key) throws Exception {
			return new String(decrypt(data, key), "UTF-8");
		}

		/**
		 * 解密数据
		 *
		 * @param data
		 *            待解密数据
		 * @param key
		 *            密钥,长度16
		 * @return 解密后的数据,UTF-8
		 */
		public static String decryptHex(byte[] data, String key) throws Exception {
			return new String(decrypt(data, key), "UTF-8");
		}

		/**
		 * 解密数据
		 *
		 * @param data
		 *            待解密数据,UTF-8
		 * @param key
		 *            密钥,长度16
		 * @return 解密后的数据
		 */
		public static byte[] decrypt(String data, String key) throws Exception {
			return decrypt(data.getBytes("UTF-8"), key);
		}

		/**
		 * 解密数据
		 *
		 * @param data
		 *            待解密数据
		 * @param key
		 *            密钥,长度16
		 * @return 解密后的数据
		 */
		public static byte[] decrypt(byte[] data, String key) throws Exception {
			Key deskey = keyGenerator(key);
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			// 初始化Cipher对象，设置为解密模式
			cipher.init(Cipher.DECRYPT_MODE, deskey);
			// 执行解密操作
			return cipher.doFinal(Base64.decodeBase64(data));
		}
	}

	public static class MD5 {
		/**
		 * md5编码
		 *
		 * @param data
		 *            待编码数据
		 * @return 编码后的数据
		 */
		public static byte[] md5(byte[] data) {
			return DigestUtils.md5(data);
		}

		/**
		 * md5编码
		 *
		 * @param data
		 *            待编码数据
		 * @return 编码后的数据
		 */
		public static byte[] md5(InputStream data) throws IOException {
			return DigestUtils.md5(data);
		}

		/**
		 * md5编码
		 *
		 * @param data
		 *            待编码数据
		 * @return 编码后的数据
		 */
		public static byte[] md5(String data) {
			return DigestUtils.md5(data);
		}

		/**
		 * md5编码
		 *
		 * @param data
		 *            待编码数据
		 * @return 编码后的数据
		 */
		public static String md5Hex(byte[] data) {
			return DigestUtils.md5Hex(data);
		}

		/**
		 * md5编码
		 *
		 * @param data
		 *            待编码数据
		 * @return 编码后的数据
		 */
		public static String md5Hex(InputStream data) throws IOException {
			return DigestUtils.md5Hex(data);
		}

		/**
		 * md5编码
		 *
		 * @param data
		 *            待编码数据
		 * @return 编码后的数据
		 */
		public static String md5Hex(String data) {
			return DigestUtils.md5Hex(data);
		}

		/**
		 * HMAC_MD5加密
		 *
		 * @param key
		 *            密钥
		 * @param valueToDigest
		 *            需要加密的数据
		 */
		public static byte[] hmacMd5(String key, String valueToDigest) {
			return new HmacUtils(HmacAlgorithms.HMAC_MD5, key).hmac(valueToDigest);
		}
	}

	public static class SHA {

		/**
		 * HMAC_SHA1加密
		 *
		 * @param key
		 *            密钥
		 * @param valueToDigest
		 *            需要加密的数据
		 */
		public static byte[] hmacSha1(String key, String valueToDigest) {
			return new HmacUtils(HmacAlgorithms.HMAC_SHA_1, key).hmac(valueToDigest);
		}

		/**
		 * HMAC_SHA256加密
		 *
		 * @param key
		 *            密钥
		 * @param valueToDigest
		 *            需要加密的数据
		 */
		public static byte[] hmacSha256(String key, String valueToDigest) {
			return new HmacUtils(HmacAlgorithms.HMAC_SHA_256, key).hmac(valueToDigest);
		}
	}

	/**
	 * 将字符串转换为十六进制编码后解析为long
	 *
	 * @param str
	 *            待转换字符串,长度8
	 * @return 转换后的long
	 */
	public static long strToLong(String str) {
		String s = "";

		for (int i = 0; i < str.length(); i++) {
			int ch = (int) str.charAt(i);
			s += Integer.toHexString(ch);
		}

		return Long.parseLong(s, 16);
	}

	/**
	 * 将long转换为十六进制编码后解析为字符串
	 *
	 * @param l
	 *            待转换long
	 * @return 转换后的字符串,UTF-8
	 * @throws UnsupportedEncodingException
	 */
	public static String longToStr(Long l) throws UnsupportedEncodingException {
		String str = Long.toHexString(l);

		byte[] baKeyword = new byte[str.length() / 2];

		for (int i = 0; i < baKeyword.length; i++) {

			baKeyword[i] = (byte) (0xff & Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16));
		}

		return new String(baKeyword, "UTF-8");
	}

	public static String encodeBase64(byte[] data) {
		return Base64.encodeBase64String(data);
	}

	public static byte[] decodeBase64(byte[] data) {
		return Base64.decodeBase64(data);
	}
}
