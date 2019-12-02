package com.hwy.shipyard.utils;


import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * @author honghong
 * @version 1.0
 * @date 2019/11/20 21:18
 */
public class SignatureRsa {

    /**
     * BASE64Encoder 加密
     *
     * @param data 要加密的数据
     * @return 加密后的字符串
     */
    public static String encryptBASE64(byte[] data) {
        Base64.Encoder encoder = Base64.getEncoder();
        String encode = encoder.encodeToString(data);
        return encode;
    }

    /**
     * BASE64Decoder 解密
     *
     * @param data 要解密的字符串
     * @return 解密后的byte[]
     * @throws Exception
     */
    public static byte[] decryptBASE64(String data) throws Exception {
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] buffer = decoder.decode(data);
        return buffer;
    }



    public static Map jdkRSA(String src) {
        try {
            //1.初始化密钥
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(512);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            String rsaPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJH9apx1q4KcKF3zPlWOZpTUUShBCIrqynULFb6MPSVRmCcX05PRyHLLc2xKnFPOISgrnq33Av6a5IfVcgNSvkMCAwEAAQ==";  //用作验证
            String rsaPrivateKey = "MIIBVAIBADANBgkqhkiG9w0BAQEFAASCAT4wggE6AgEAAkEAkf1qnHWrgpwoXfM+VY5mlNRRKEEIiurKdQsVvow9JVGYJxfTk9HIcstzbEqcU84hKCuerfcC/prkh9VyA1K+QwIDAQABAkBIh87fzNZZHZwwkRj3CLiOWK25ghXsbpoHbGpxMNtwHkefYgmnZPqhpWaS3dRjobrSW6UmwVvW/o7VlwLgwy4BAiEA3txhPUdEoH5IrHEzQeDuiQmNQiy/ZAiaviQmkVjC/FMCIQCnssxnre4hfMAaMcdqCUfEfo1RywKDeJ+d3Ykyf/54UQIhAJNEV/FxZdUUfhuNlkyDQb1GlZTUEwkccn0RUHcdi4PHAiBUuHKlqmwUhOa0FRPukPOt5FVWzh3d95GD+oi2d41CkQIgbne39H6E1zzIg+qfo5Dp1GKT0O+2LV3aODZ3XrCtMLw=";  //用做签名

            //2.执行签名
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decryptBASE64(rsaPrivateKey));//私钥进行签名
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");//得到实例
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("MD5withRSA");  //签名算法
            signature.initSign(privateKey); //初始化密钥
            signature.update(src.getBytes()); //产生签名
            byte[] result = signature.sign();
            //System.out.println("数字签名后的密钥:"+encryptBASE64(result));//签名后的结果

            //3.验证签名
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decryptBASE64(rsaPublicKey));//使用公钥来验证签名
            keyFactory = KeyFactory.getInstance("RSA"); //说明完整性
            PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
            signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(publicKey);//初始化验证方式
            signature.update(src.getBytes());
            boolean bool = signature.verify(result);  //对原始数据的签名进行验证
            //System.out.println("签名验证结果 :" + bool);   //输出验证结果
            Map<String,Object> map =new HashMap<>();;
            map.put("signature", encryptBASE64(result));
            map.put("bool", bool);
            return map;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args) {
        jdkRSA("BigData");
    }

}
