package utils.eiffelyk.www.rsademo;

import java.security.PrivateKey;
import java.security.PublicKey;

public class RSATest
{
	/* 密钥内容 base64 code */
	private static String PUCLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC/g5HfxJntxYM3aoEwOq5ng+0J\n" +
			"8tCy3hFgZammAb3Im3GAvVL0yzktOh8Vk7qooLKq6bpTzQ9jawJ6rXGhlATnIWuZ\n" +
			"q6eBMkQAAjrrwIxcF7GR6WALffMbyCoRGvsSqCzMzMBZGTZGEggSDMdTx4ezt18A\n" +
			"bEylu7WQD4MmctYfVQIDAQAB";
	private static String PRIVATE_KEY = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAL+Dkd/Eme3Fgzdq\n" +
			"gTA6rmeD7Qny0LLeEWBlqaYBvcibcYC9UvTLOS06HxWTuqigsqrpulPND2NrAnqt\n" +
			"caGUBOcha5mrp4EyRAACOuvAjFwXsZHpYAt98xvIKhEa+xKoLMzMwFkZNkYSCBIM\n" +
			"x1PHh7O3XwBsTKW7tZAPgyZy1h9VAgMBAAECgYEAjkcAqG02rh5+TT0inOH7j4vD\n" +
			"HhD2Wuv/nM3qKxpAFDjnKe19I7+WyrLTRQRrN7urAK6Dbp55QDLPtLQGJzQALyrM\n" +
			"HsmblZi13svF6aQF2ZPNHybuux7oWuU8rmhIii0mmKWT0EsFndA2aXR2FfPHvz6n\n" +
			"iokSLX8dWdzO0L8zXgECQQD4ThkTSCkr8G5pFJOAcL5NO8jS3sudqy+rVpG5jqPQ\n" +
			"GE+/OUyxEkOItXmtDwVYmJa4V+GI6jIQFOKVQ+G2/IqZAkEAxXLs69tEkZTP7ZbL\n" +
			"c+7fhx90hLkZcTbghALCEUwy/95HpukcOpi7gI0Y3RKFO4tSd7cRLBaIx5XHKsj/\n" +
			"gd1MHQJAAIqzFU6wAE+K/iG0XLBiSeraelNQIGzyMq6CrOP5yYd0DRfD6hL/ILo8\n" +
			"pLsGqPXURlyyukjAiTZydB7szChq6QJBAJQYs2PkvvWC7MKEDG4UiUxMZ5T1UQP9\n" +
			"6UCa4P0gblBEZPHofufkjx7IthF7Rrt2sJBim1q6PDWXPh/1StAZMHECQHHsKpYR\n" +
			"q6ntS0RhwoAwxqMmRLVaBcJLaGU7DVe0fYXpyzNv8jth4oBaFeemleS2BFMHuJ8h\n" +
			"WY8qiToCV5xxTVg=";

	public static void main(String[] args) throws Exception
	{
		String source = "test";
//		InputStream publicIS = new FileInputStream("C:\\rsa_public_key.pem");
//		InputStream privateIS = new FileInputStream("C:\\pkcs8_rsa_private_key.pem");
		 PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
//		PublicKey publicKey = RSAUtils.loadPublicKey(publicIS);
		 PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
//		PrivateKey privateKey = RSAUtils.loadPrivateKey(privateIS);
		byte[] b1 = RSAUtils.encryptData(source.getBytes(), publicKey);
		System.out.println(">>>" + new String(RSAUtils.decryptData(b1, privateKey)));
	}
}
