package cn.com.duiba.ds.tools.sdk;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * ClassName: MD5 <br/>
 * date: 2016年12月9日 上午11:19:30 <br/>
 *
 * @version 
 */
public class MD5 {

	private static String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6","7", "8", "9", "a", "b", "c", "d", "e", "f" };

	/**
	 * Creates a new instance of MD5.
	 *
	 */
	public MD5() {
		
	}

	/**
	 *
	 * @param b
	 * @return xx
	 * @throws NoSuchAlgorithmException
	 */
	public static String md5(byte b[]) throws NoSuchAlgorithmException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(b, 0, b.length);
		return byteArrayToHexString(md5.digest());
	}

	/**
	 * @param data
	 * @return xx
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String md5(String data) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		byte b[] = data.getBytes("UTF8");
		md5.update(b, 0, b.length);
		return byteArrayToHexString(md5.digest());
	}

	private static String byteArrayToHexString(byte b[]) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++)
			sb.append(byteToHexString(b[i]));

		return sb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

}
