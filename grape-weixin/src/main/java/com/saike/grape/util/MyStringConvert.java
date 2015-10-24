package com.saike.grape.util;

import java.io.IOException;
import java.nio.charset.Charset;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;

public class MyStringConvert extends StringHttpMessageConverter {
    // text/html;chserset=UTF-8
    private MediaType mt = new MediaType("text", "html",
            Charset.forName("UTF-8"));

    @Override
    public MediaType getDefaultContentType(String t) throws IOException {
        System.out.println("设置编码格式执行了...");
        return mt;
    }
}