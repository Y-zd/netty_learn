package com.yzd.nio;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/***
 * nio写数据
 * @author : yanzhidong
 * @date : 2019/6/27 
 * @version : V1.0
 *
 */
public class NioTest3 {

    public static void main(String[] args) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\project\\mine\\netty\\src\\main\\resources\\NioTest3.txt");
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        byte[] message = "NioTest3".getBytes();

        for (int i = 0; i <message.length ; i++) {
          byteBuffer.put(message[i]);
        }
        byteBuffer.flip();
        channel.write(byteBuffer);
        fileOutputStream.close();
    }

}
