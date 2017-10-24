package com.siemens.cms.message.utils;

import java.security.MessageDigest;

/**
 * Created by Simon.Lau on 24-Nov-16.
 */
public class EncryptionUtil {

    public static String encrypt (String data, String algorithm) throws Exception {
        return bytes2Hex(encrypt(data.getBytes(), algorithm));
    }

    public static byte[] encrypt (byte[] data, String algorithm) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
        messageDigest.update(data);
        return messageDigest.digest();
    }

    private static String bytes2Hex (byte[] bts) {
        String des = "";
        String tmp;
        for (int i = 0; i < bts.length; i++) {
            tmp = (Integer.toHexString(bts[i] & 0xFF));
            if (tmp.length() == 1) {
                des += "0";
            }
            des += tmp;
        }
        return des;
    }

}
