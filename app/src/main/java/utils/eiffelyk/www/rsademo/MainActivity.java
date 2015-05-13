package utils.eiffelyk.www.rsademo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import java.io.InputStream;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MainActivity extends Activity implements OnClickListener
{
	private Button btn1, btn2;// 加密，解密
	private EditText et1, et2, et3;// 需加密的内容，加密后的内容，解密后的内容

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

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}

	private void initView()
	{
		btn1 = (Button) findViewById(R.id.btn1);
		btn2 = (Button) findViewById(R.id.btn2);
		btn1.setOnClickListener(this);
		btn2.setOnClickListener(this);

		et1 = (EditText) findViewById(R.id.et1);
		et2 = (EditText) findViewById(R.id.et2);
		et3 = (EditText) findViewById(R.id.et3);
	}

	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		// 加密
		case R.id.btn1:
			String source = et1.getText().toString().trim();
			try
			{
				// 从字符串中得到公钥
				// PublicKey publicKey = RSAUtils.loadPublicKey(PUCLIC_KEY);
				// 从文件中得到公钥
				InputStream inPublic = getResources().getAssets().open("rsa_public_key.pem");
				PublicKey publicKey = RSAUtils.loadPublicKey(inPublic);
				// 加密
				byte[] encryptByte = RSAUtils.encryptData(source.getBytes(), publicKey);
				// 为了方便观察吧加密后的数据用base64加密转一下，要不然看起来是乱码,所以解密是也是要用Base64先转换
				String afterEncrypt = Base64Utils.encode(encryptByte);
				et2.setText(afterEncrypt);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		// 解密
		case R.id.btn2:
			String encryptContent = et2.getText().toString().trim();
			try
			{
				// 从字符串中得到私钥
				// PrivateKey privateKey = RSAUtils.loadPrivateKey(PRIVATE_KEY);
				// 从文件中得到私钥
				InputStream inPrivate = getResources().getAssets().open("pkcs8_rsa_private.pem");
				PrivateKey privateKey = RSAUtils.loadPrivateKey(inPrivate);
				// 因为RSA加密后的内容经Base64再加密转换了一下，所以先Base64解密回来再给RSA解密
				byte[] decryptByte = RSAUtils.decryptData(Base64Utils.decode(encryptContent), privateKey);
				String decryptStr = new String(decryptByte);
				et3.setText(decryptStr);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}

}
