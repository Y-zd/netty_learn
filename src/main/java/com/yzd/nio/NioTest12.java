package com.yzd.nio;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/***
 * java 字符串编码解码
 * @author : yanzhidong
 * @date : 2019/7/10 
 * @version : V1.0
 *
 */
public class NioTest12 {
    public static void main(String[] args) throws Exception {

        String inputFile = "D:\\project\\mine\\netty\\src\\main\\resources\\input.txt";
        String outputFile = "D:\\project\\mine\\netty\\src\\main\\resources\\output.txt";

        RandomAccessFile input = new RandomAccessFile(inputFile, "r");
        RandomAccessFile output = new RandomAccessFile(outputFile, "rw");

        long inputLength = input.length();

        FileChannel inputChannel = input.getChannel();
        FileChannel outputChannel = output.getChannel();

        MappedByteBuffer inputData = inputChannel.map(FileChannel.MapMode.READ_ONLY, 0, inputLength);


        Charset charset = Charset.forName("utf-8");
        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();
        CharBuffer charBuffer = charsetDecoder.decode(inputData);
        ByteBuffer outputData = charsetEncoder.encode(charBuffer);

        outputChannel.write(outputData);

        input.close();
        output.close();

        Charset.availableCharsets().forEach((k,v)-> System.out.println(k+","+v));


    }
}
