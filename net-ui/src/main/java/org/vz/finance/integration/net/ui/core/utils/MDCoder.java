package org.vz.finance.integration.net.ui.core.utils;


import org.apache.shiro.codec.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class MDCoder
        extends SecurityCoder {
    public static byte[] encodeMD2(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD2");

        return md.digest(data);
    }


    public static byte[] encodeMD4(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD4");

        return md.digest(data);
    }


    public static byte[] encodeMD5(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");

        return md.digest(data);
    }


    public static String MD5(byte[] message, byte[] salt) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


        try {
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            if (null != salt) {
                mdInst.update(salt);
            }

            byte[] md = mdInst.digest(message);

            int j = md.length;
            char[] str = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xF];
                str[k++] = hexDigits[byte0 & 0xF];
            }
            return new String(str);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

            return "";
        }
    }


    public static byte[] encodeTiger(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("Tiger");

        return md.digest(data);
    }


    public static String encodeTigerHex(byte[] data) throws Exception {
        byte[] b = encodeTiger(data);

        return new String(Hex.encode(b));
    }


    public static byte[] encodeWhirlpool(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("Whirlpool");

        return md.digest(data);
    }


    public static String encodeWhirlpoolHex(byte[] data) throws Exception {
        byte[] b = encodeWhirlpool(data);

        return new String(Hex.encode(b));
    }


    public static byte[] encodeGOST3411(byte[] data) throws Exception {
        MessageDigest md = MessageDigest.getInstance("GOST3411");

        return md.digest(data);
    }


    public static String encodeGOST3411Hex(byte[] data) throws Exception {
        byte[] b = encodeGOST3411(data);

        return new String(Hex.encode(b));
    }
}
