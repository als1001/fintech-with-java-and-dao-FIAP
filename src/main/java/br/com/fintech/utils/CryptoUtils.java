package br.com.fintech.utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class CryptoUtils {
	public static String encrypt(String password) throws Exception {
        // Get an instance of the MD5 algorithm
        MessageDigest md = MessageDigest.getInstance("MD5");
        // Update with the data to be encrypted and specify the default encoding
        md.update(password.getBytes("ISO-8859-1"));
        // Generate the encrypted password as a byte array for further hashing
        byte[] digest = md.digest();
        // Convert the byte array to a positive BigInteger
        BigInteger hash = new BigInteger(1, digest);
        // Return the encrypted password as a hexadecimal string
        return hash.toString(16);
    }
}