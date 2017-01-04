package cn.com.duiba.ds.tools;

import cn.com.duiba.ds.encrypt.BlowfishUtils;

public class AppSecrectDecode {
	//解密的秘钥
	private static final String secretKey="Ziy66Kf";
	//解密的秘钥,测试环境
//		private static final String secretKey="t4QM9Jp7kAcdRS";
		
	
	//需要解密的code
	private static final String appSecretCode="Xaa19jZn4L2Hc2anSM2M7KC9gSt4Usi7rzcbM";
	public static void main(String[] args) {
		String appSecret=BlowfishUtils.decryptBlowfish(appSecretCode, secretKey);
		System.out.println("appSecret: "+appSecret);
	}
}