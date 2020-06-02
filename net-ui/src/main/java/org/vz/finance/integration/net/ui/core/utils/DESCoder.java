package org.vz.finance.integration.net.ui.core.utils;


import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public abstract class DESCoder
        extends SecurityCoder {
    public static final String KEY_ALGORITHM = "DES";
    public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5PADDING";

    private static Key toKey(byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        DESKeySpec dks = new DESKeySpec(key);

        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");

        return keyFactory.generateSecret(dks);
    }


    public static byte[] decrypt(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");

        cipher.init(2, k);

        return cipher.doFinal(data);
    }


    public static byte[] encrypt(byte[] data, byte[] key) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key k = toKey(key);

        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5PADDING");

        cipher.init(1, k);

        return cipher.doFinal(data);
    }


    public static byte[] initKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("DES");


        kg.init(56, new SecureRandom());

        SecretKey secretKey = kg.generateKey();

        return secretKey.getEncoded();
    }
}
