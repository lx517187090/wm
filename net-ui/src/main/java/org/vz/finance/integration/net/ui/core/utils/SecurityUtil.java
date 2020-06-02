package org.vz.finance.integration.net.ui.core.utils;


import java.util.Map;

public final class SecurityUtil
{
    private static final byte[] ENCRYPT_KEY = { -81, 0, 105, 7, -32, 26, -49, 88 };




    public static final String CHARSET = "UTF-8";





    public static final byte[] decryptBASE64(String key) {
        try {
            return (new BASE64Encoder()).decode(key);
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }








    public static final String encryptBASE64(byte[] key) {
        try {
            return (new BASE64Encoder()).encode(key);
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }








    public static final String decryptDes(String cryptData) { return decryptDes(cryptData, ENCRYPT_KEY); }









    public static final String encryptDes(String data) { return encryptDes(data, ENCRYPT_KEY); }








    public static final String encryptMd5(String strSrc) {
        String outString = null;
        try {
            outString = encryptBASE64(MDCoder.encodeMD5(strSrc.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
        return outString;
    }








    public static final String encryptSHA(String data) {
        try {
            return encryptBASE64(SHACoder.encodeSHA256(data.getBytes("UTF-8")));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }

    public static final String encryptHMAC(String data) { return encryptHMAC(data, ENCRYPT_KEY); }

    public static final String decryptDes(String cryptData, byte[] key) {
        String decryptedData = null;

        try {
            decryptedData = new String(DESCoder.decrypt(decryptBASE64(cryptData), key));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
        return decryptedData;
    }







    public static final String encryptDes(String data, byte[] key) {
        String encryptedData = null;

        try {
            encryptedData = encryptBASE64(DESCoder.encrypt(data.getBytes(), key));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
        return encryptedData;
    }








    public static final String encryptHMAC(String data, byte[] key) {
        try {
            return encryptBASE64(HmacCoder.encodeHmacSHA512(data.getBytes("UTF-8"), key));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }







    public static final String signRSA(String data, String privateKey) {
        try {
            return encryptBASE64(RSACoder.sign(data.getBytes("UTF-8"), decryptBASE64(privateKey)));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }







    public static final boolean verifyRSA(String data, String publicKey, String sign) {
        try {
            return RSACoder.verify(data.getBytes("UTF-8"), decryptBASE64(publicKey), decryptBASE64(sign));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }







    public static final String encryptRSAPrivate(String data, String privateKey) {
        try {
            return encryptBASE64(RSACoder.encryptByPrivateKey(data.getBytes("UTF-8"), decryptBASE64(privateKey)));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }








    public static final String decryptRSAPublic(String cryptData, String publicKey) {
        try {
            return new String(RSACoder.decryptByPublicKey(decryptBASE64(cryptData), decryptBASE64(publicKey)));
        } catch (Exception e) {
            throw new RuntimeException("��������������������", e);
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println(encryptDes("SHJR"));
        System.out.println(decryptDes("INzvw/3Qc4q="));
        System.out.println(encryptMd5("SHJR"));
        System.out.println(encryptSHA("1"));
        Map<String, Object> key = RSACoder.initKey();
        String privateKey = encryptBASE64(RSACoder.getPrivateKey(key));
        String publicKey = encryptBASE64(RSACoder.getPublicKey(key));
        System.out.println(privateKey);
        System.out.println(publicKey);
        String sign = signRSA("132", privateKey);
        System.out.println(sign);
        String encrypt = encryptRSAPrivate("132", privateKey);
        System.out.println(encrypt);
        String org = decryptRSAPublic(encrypt, publicKey);
        System.out.println(org);
        System.out.println(verifyRSA(org, publicKey, sign));
    }
}