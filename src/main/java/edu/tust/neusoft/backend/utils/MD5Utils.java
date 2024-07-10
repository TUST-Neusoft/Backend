package edu.tust.neusoft.backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class MD5Utils {
    public String generateMd5Token() {
        String token = String.valueOf(new Date().getTime());

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(token.getBytes());
            byte[] digest = md.digest();

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
