package domain.db;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import domain.model.DomainException;

public class StringHasher {

    public static String sha512(String str, byte[] seed){
        try{
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");
            crypt.reset();
            byte[] strBytes = str.getBytes("UTF-8");
            crypt.update(strBytes);
            crypt.update(seed);
            byte[] digest = crypt.digest();
            return new BigInteger(1, digest).toString(16);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e){
            throw new DomainException("Hash Problem", e);
        }
    }

    public static byte[] getSeed(){
        SecureRandom random = new SecureRandom();
        return random.generateSeed(20);
    }
}
