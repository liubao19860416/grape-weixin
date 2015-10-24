package com.saike.grape.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.fileupload.util.Streams;
/**
 * 文件拷贝操作
 * @author Liubao
 * @2015年6月17日
 *
 */
public class StreamsTest {
    
    public static void main(String[] args) throws Exception {
        InputStream pInputStream = new FileInputStream(new File("d:\\temp\\1.jpg"));
        OutputStream pOutputStream = new FileOutputStream(new File("d:\\temp\\1_copy00.jpg"));
        Streams.copy(pInputStream, pOutputStream, true);
        
        pInputStream = new FileInputStream(new File("d:\\temp\\activeUsers.xml"));
        String asString = Streams.asString(pInputStream);
        System.out.println(asString);
    }
}
