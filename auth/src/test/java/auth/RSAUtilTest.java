package auth;

import base.utils.RSAUtil;
import base.utils.RSAUtil.RSAKeyPair;

public class RSAUtilTest {
	
	private static final String src = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) throws Exception {
        System.out.println("\n");
        RSAKeyPair keyPair = RSAUtil.generateKeyPair();
        System.out.println("公钥：" + keyPair.getPublicKey());
        System.out.println("私钥：" + keyPair.getPrivateKey());
        System.out.println("\n");
        test1(keyPair, src);
        System.out.println("\n");
        test2(keyPair, src);
        System.out.println("\n");
    }
	
	/**
     * 公钥加密私钥解密
     */
    private static void test1(RSAKeyPair keyPair, String source) throws Exception {
        System.out.println("***************** 公钥加密私钥解密开始 *****************");
        String text1 = RSAUtil.encryptByPublicKey(keyPair.getPublicKey(), source);
        String text2 = RSAUtil.decryptByPrivateKey(keyPair.getPrivateKey(), text1);
        System.out.println("加密前：" + source);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (source.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 公钥加密私钥解密结束 *****************");
    }

    /**
     * 私钥加密公钥解密
     *encryptByPublicKey decryptByPublicKey
     * @throws Exception
     */
    private static void test2(RSAKeyPair keyPair, String source) throws Exception {
        System.out.println("***************** 私钥加密公钥解密开始 *****************");
        String text1 = RSAUtil.encryptByPrivateKey(keyPair.getPrivateKey(), source);
        String text2 = RSAUtil.decryptByPublicKey(keyPair.getPublicKey(), text1);
        System.out.println("加密前：" + source);
        System.out.println("加密后：" + text1);
        System.out.println("解密后：" + text2);
        if (source.equals(text2)) {
            System.out.println("解密字符串和原始字符串一致，解密成功");
        } else {
            System.out.println("解密字符串和原始字符串不一致，解密失败");
        }
        System.out.println("***************** 私钥加密公钥解密结束 *****************");
    }
	
}

