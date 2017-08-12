package com.wdm.test.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.google.common.primitives.Bytes;


/**
 * Created by wdmyong on 2017/8/3.
 * Authorization for: https://www.qcloud.com/document/product/460/6968
 */
public class HMACSHA {

    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String APPID = "10001290";
    private static final String BUCKET = "tencentyun";
    private static final String SECRET_ID = "AKIDgaoOYh2kOmJfWVdH4lpfxScG2zPLPGoK";
    private static final String SECRET_KEY = "nwOKDouy5JctNOlnere4gkVoOUz5EYAb";

    /**
     * 生成签名数据
     *
     * @param data 待加密的数据
     * @param key  加密使用的key
     */
    public static byte[] getSignature(String data,String key) throws Exception{
        byte[] keyBytes=key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        return mac.doFinal(data.getBytes());
    }

    public static String hmac_sha1(String value, String key) {
        try {
            // Get an hmac_sha1 key from the raw key bytes
            byte[] keyBytes = key.getBytes();
            SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");

            // Get an hmac_sha1 Mac instance and initialize with the signing key
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // Compute the hmac on input data bytes
            byte[] rawHmac = mac.doFinal(value.getBytes());

            // Convert raw bytes to Hex
            String hexBytes = byte2hex(rawHmac);
            return hexBytes;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String byte2hex(final byte[] b){
        String hs="";
        String stmp="";
        for (int n=0; n<b.length; n++){
            stmp=(java.lang.Integer.toHexString(b[n] & 0xFF));
            if (stmp.length()==1) hs=hs+"0"+stmp;
            else hs=hs+stmp;
        }
        return hs;
    }

    private static String appendEqualSign(String s){
        int len = s.length();
        int appendNum = 8 - (int)(len/8);
        for (int n=0; n<appendNum; n++){
            s += "%3D";
        }
        return s;
    }

    private static String encode(byte[] bytes) {
        String s = System.getProperty("line.separator");
        return java.util.Base64.getEncoder().encodeToString(bytes).replaceAll(s, "");
    }

    private static String hmacSHA1(String key, String text) {
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(new SecretKeySpec(key.getBytes(), "HmacSHA1"));
            byte[] tmp = mac.doFinal(text.getBytes());
            byte[] tt = new byte[tmp.length + text.getBytes().length];
            System.arraycopy(tmp, 0, tt, 0, tmp.length);
            System.arraycopy(text.getBytes(), 0, tt, tmp.length, text.getBytes().length);
            return encode(tt);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getBatchAuthString(long currentTime, long expired, String rdm) {
        String srcString = "a=" + APPID + "&b=" + BUCKET + "&k=" + SECRET_ID + "&e=" + expired
                + "&t=" + currentTime + "&r=" + rdm + "&u=0" + "&f=";
        return calcAuthBySrcString(srcString);
    }

    private static String calcAuthBySrcString(String srcString) {
        try {
            byte[] keyBytes = SECRET_KEY.getBytes();
            SecretKey secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] textBytes = srcString.getBytes();
            byte[] hmacBytes = mac.doFinal(textBytes);
            byte[] totalBytes = Bytes.concat(hmacBytes, textBytes);
            return new String(Base64.encodeBase64(totalBytes)).trim();
        } catch (Exception e) {

        }
        return null;
    }

    public static void main(String[] args) {
        try {
            long expired = System.currentTimeMillis() / 1000 + 2592000;
            long onceExpired = 0;
            long current = System.currentTimeMillis() / 1000;
            String rdm = String.valueOf(new Random().nextInt());
            String userid = "0";
            String fileid = "tencentyunSignTest";
            String srcStr = "a=" + APPID + "&b=" + BUCKET + "&k=" + SECRET_ID + "&e=" + expired
                + "&t=" + current + "&r=" + rdm + "&u=" + userid + "&f=";
            String srcWithFile = "a=" + APPID + "&b=" + BUCKET + "&k=" + SECRET_ID + "&e=" + expired
                    + "&t=" + current + "&r=" + rdm + "&u=" + userid + "&f=" + fileid;
            String srcStrOnce = "a=" + APPID + "&b=" + BUCKET + "&k=" + SECRET_ID + "&e=" + onceExpired
                    + "&t=" + current + "&r=" + rdm + "&u=" + userid + "&f=" + fileid;
            // System.out.println(srcStr);
            // System.out.println(srcWithFile);
            // System.out.println(srcStrOnce);

            byte[] a = getSignature(srcStr, SECRET_KEY);
            byte[] b = srcStr.getBytes();
            byte[] c = new byte[a.length + b.length];
            System.arraycopy(a, 0, c, 0, a.length);
            System.arraycopy(b, 0, c, a.length, b.length);
            System.out.println(new String(Base64.encodeBase64(c)));

            System.out.println(hmacSHA1(SECRET_KEY, srcStr));

            System.out.println(getBatchAuthString(current, expired, rdm));

            System.out.println(appendEqualSign(new String(Base64.encodeBase64((hmac_sha1(srcWithFile, SECRET_KEY) + srcWithFile).getBytes()))));

            System.out.println(appendEqualSign(new String(Base64.encodeBase64((hmac_sha1(srcStrOnce, SECRET_KEY) + srcStrOnce).getBytes()))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

