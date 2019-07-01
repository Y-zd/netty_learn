package com.yzd.nio;

import java.nio.ByteBuffer;

/***
 *
 * @author : yanzhidong
 * @date : 2019/7/1 
 * @version : V1.0
 *
 */
public class NioTest5 {

    public static void main(String[] args) {

        ByteBuffer buffer = ByteBuffer.allocate(512);
        buffer.putChar('我');
        buffer.putInt(1);
        buffer.putChar('你');
        buffer.flip();
        System.out.println(buffer.getChar());
        System.out.println(buffer.getInt());
        System.out.println(buffer.getChar());


    }

}
