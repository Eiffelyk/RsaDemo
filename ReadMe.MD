此例实现了Rsa加密，其实包括一个Activity，一个Base64的工具类，一个自定义的RSAUtils

项目中使用的为openssl生成好的密钥对，其中密钥对放入到Assets目录中了 ，公钥使用的是rsa_public_key.pem，私钥使用的是pkcs8_rsa_private.pem
如何生成公钥和私钥，我采用的是支付宝中带的公钥和私钥生成的工具
参考链接：http://blog.csdn.net/bbld_/article/details/38777491
