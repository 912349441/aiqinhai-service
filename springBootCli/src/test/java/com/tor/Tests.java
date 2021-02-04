package com.tor;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.MD5;
import cn.hutool.crypto.symmetric.AES;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.SecureRandom;

public class Tests {

    private final static String aesKey = "12f4vulwp1xy3s2y";

    public static void main(String[] args) {
        try {
            String hospitalNumber = "84622648";
            String idCardNumber = "330120xxxxxxxx0856";

            Key key = new SecretKeySpec(aesKey.getBytes("UTF-8"), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test(){
        try {
            String s = "12f4vulwp1xy3s2y";
            byte[] bytes = s.getBytes("UTF-8");
            Console.log(s);
            // key的转换
            Key key = new SecretKeySpec(s.getBytes("UTF-8"), "AES");

            // 加密
            // AES/工作模式/填充方式
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal("test".getBytes());
            System.out.println("jdk aes encrypt : " + Base64.encodeBase64String(result));

            // 解密
            Key key2 = new SecretKeySpec(s.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, key2);
            result = cipher.doFinal(result);
            System.out.println("jdk aes decrypt : " + new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
