package com.rucha.passmgmtservice.util;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import java.util.Arrays;

@Component
public final class PasswordEDUtil {

    private static final String UNICODE_FORMAT = "UTF8";
    //private static final String DES_ENCRYPTION_SCHEME = "TripleDES/CBC/PKCS5Padding";
    private static final String DES_ENCRYPTION_SCHEME = "DESede";
    static byte[] arrayBytes;

    static SecretKey key;

    public static SecretKey convertStringToSecretKey() throws Exception {
        String encryptionSecretKey = "W61ml0kjFeHlPs80NXeEY1tUA5oI1U5DYqpdIQUsBTALuLKLU1+12UOPwBQsC4tZas";
        arrayBytes = encryptionSecretKey.getBytes(UNICODE_FORMAT);
        KeySpec ks = new DESedeKeySpec(arrayBytes);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(DES_ENCRYPTION_SCHEME);
        key = skf.generateSecret(ks);
        return key;
    }

    public static String encrypt(String unencryptedString) {
        String encryptedString = null;
        try {
            Cipher cipher = Cipher.getInstance(DES_ENCRYPTION_SCHEME);
            cipher.init(Cipher.ENCRYPT_MODE, convertStringToSecretKey());
            byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = Base64.encodeBase64String(encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedString;
    }

    public static String decrypt(String encryptedString) {
        String decryptedText=null;
        try {
            Cipher cipher = Cipher.getInstance(DES_ENCRYPTION_SCHEME);
            cipher.init(Cipher.DECRYPT_MODE, convertStringToSecretKey());
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText= new String(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptedText;
    }

//    public static String encrypt(String unencryptedString) throws Exception {
//        MessageDigest md = MessageDigest.getInstance("md5");
//        byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//                .getBytes("utf-8"));
//        byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//        for (int j = 0, k = 16; j < 8;) {
//            keyBytes[k++] = keyBytes[j++];
//        }
//
//        SecretKey key = new SecretKeySpec(keyBytes, "DESede");
//        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//        final Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
//
//        final byte[] plainTextBytes = unencryptedString.getBytes("utf-8");
//        final byte[] cipherText = cipher.doFinal(plainTextBytes);
//        //String encodedCipherText = new sun.misc.BASE64Encoder().encode(cipherText);
//        return new String(cipherText);
//    }
//
//    public static String decrypt(String encryptedString) throws Exception {
//        final MessageDigest md = MessageDigest.getInstance("md5");
//        final byte[] digestOfPassword = md.digest("HG58YZ3CR9"
//                .getBytes("utf-8"));
//        final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
//        for (int j = 0, k = 16; j < 8;) {
//            keyBytes[k++] = keyBytes[j++];
//        }
//
//        final SecretKey key = new SecretKeySpec(keyBytes, "DESede");
//        final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
//        final Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
//        decipher.init(Cipher.DECRYPT_MODE, key, iv);
//
//        // final byte[] encData = new
//        // sun.misc.BASE64Decoder().decodeBuffer(message);
//        final byte[] plainText = decipher.doFinal(encryptedString.getBytes("UTF-8"));
//        return new String(plainText, "UTF-8");
//    }

}
