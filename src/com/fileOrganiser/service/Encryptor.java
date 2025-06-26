package com.fileOrganiser.service;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.util.Arrays;

public class Encryptor {

    private static final String ALGO = "AES";

    // Generate AES key from password
    private SecretKey getSecretKey(String password) throws Exception {
        byte[] key = password.getBytes("UTF-8");
        MessageDigest sha = MessageDigest.getInstance("SHA-256");
        key = sha.digest(key); // hash the password
        key = Arrays.copyOf(key, 16); // use first 16 bytes for AES key
        return new SecretKeySpec(key, ALGO);
    }

    // Encrypt the file
    public void encrypt(File inputFile, File outputFile, String password) throws Exception {
        SecretKey key = getSecretKey(password);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, key);

        try (
                FileInputStream fis = new FileInputStream(inputFile);
                FileOutputStream fos = new FileOutputStream(outputFile);
                CipherOutputStream cos = new CipherOutputStream(fos, cipher)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, read);
            }
        }
    }

    // Decrypt the file
    public void decrypt(File inputFile, File outputFile, String password) throws Exception {
        SecretKey key = getSecretKey(password);
        Cipher cipher = Cipher.getInstance(ALGO);
        cipher.init(Cipher.DECRYPT_MODE, key);

        try (
                FileInputStream fis = new FileInputStream(inputFile);
                CipherInputStream cis = new CipherInputStream(fis, cipher);
                FileOutputStream fos = new FileOutputStream(outputFile)) {
            byte[] buffer = new byte[1024];
            int read;
            while ((read = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, read);
            }
        }
    }
}
