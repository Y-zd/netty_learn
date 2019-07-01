package com.yzd.nio;

import java.nio.ByteBuffer;

/***
 *
 * slice buffer 创建一个新的字节缓冲区，其内容是*此缓冲区内容的共享子序列
 * @author : yanzhidong
 * @date : 2019/7/1 
 * @version : V1.0
 *
 */
public class NioTest6 {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);


        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte) i);
        }

        buffer.position(3).limit(7);
        ByteBuffer sliceBuffer = buffer.slice();

        for (int i = 0; i < sliceBuffer.capacity(); i++) {
            byte b = sliceBuffer.get(i);
            b *= 2;
            sliceBuffer.put(i, b);
        }

        buffer.position(0).limit(buffer.capacity());
        while (buffer.hasRemaining()) {
            System.out.println(buffer.get());
        }

    }
}
