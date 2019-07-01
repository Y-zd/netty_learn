package com.yzd.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 *
 * @author : yanzhidong
 * @date : 2019/7/1 
 * @version : V1.0
 *
 */
public class NioTest4 {
    public static void main(String[] args) throws Exception {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\project\\mine\\netty\\src\\main\\resources\\output.txt");
        FileInputStream fileInputStream = new FileInputStream("D:\\project\\mine\\netty\\src\\main\\resources\\input.txt");
        FileChannel inputChannel = fileInputStream.getChannel();
        FileChannel outputChannel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while (true) {
            buffer.clear();
            int read = inputChannel.read(buffer);
            System.out.println("read:"+read);
            if (-1 == read) {
                break;
            }
            buffer.flip();
            outputChannel.write(buffer);
        }
        inputChannel.close();
        outputChannel.close();
    }

}
