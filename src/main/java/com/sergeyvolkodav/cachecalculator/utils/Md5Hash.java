package com.sergeyvolkodav.cachecalculator.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hash {

    public static String md5Sum(String value) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(value.getBytes(), 0, value.length());
            return new BigInteger(1, m.digest()).toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}
