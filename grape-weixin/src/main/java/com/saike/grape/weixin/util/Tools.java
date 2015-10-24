package com.saike.grape.weixin.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class Tools {
    
    public static void main(String[] args) {
        System.out.println(1/0);
    }

    public static final String inputStream2String(InputStream in)
            throws UnsupportedEncodingException, IOException {
        if (in == null)
            return "";
        StringBuffer out = new StringBuffer();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1;) {
            out.append(new String(b, 0, n, "UTF-8"));
        }
        return out.toString();
    }

    public static final boolean checkSignature(String token, String signature,
            String timestamp, String nonce) {
        final List<String> list = new ArrayList<String>(3){
            private static final long serialVersionUID = 1L;
            public String toString() {
                return this.get(0) + this.get(1) + this.get(2);
            }
        };
        list.add(token);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
//        String temp = list.get(0) + list.get(1) + list.get(2);
        String temp = list.toString();
        boolean bl = DigestUtils.shaHex(temp).equals(signature);
        return bl;
    }
}
