package com.yzd.nio;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/***
 *
 * MappedByteBuffer
 * @author : yanzhidong
 * @date : 2019/7/4 
 * @version : V1.0
 *
 */
public class NioTest9 {

    public static void main(String[] args) throws Exception {
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\project\\mine\\netty\\src\\main\\resources\\NioTest2.txt", "rw");
        FileChannel channel = randomAccessFile.getChannel();

        MappedByteBuffer mappedByteBuffer = channel.map(FileChannel.MapMode.READ_WRITE, 0, 3);
        mappedByteBuffer.put(0, (byte) 'a');
        mappedByteBuffer.put(1, (byte) 'b');
        randomAccessFile.close();
    }

}
