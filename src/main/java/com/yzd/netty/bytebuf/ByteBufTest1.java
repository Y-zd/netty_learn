package com.yzd.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/13 
 * @version : V1.0
 *
 */
public class ByteBufTest1 {
    public static void main(String[] args) {
        ByteBuf byteBuf = Unpooled.copiedBuffer("你hello world", Charset.forName("utf-8"));
        System.out.println(byteBuf);
        if (byteBuf.hasArray()) {
            byte[] array = byteBuf.array();
            System.out.println(new String(array, Charset.forName("utf-8")));
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.readableBytes());
            System.out.println(byteBuf.capacity());
        }
        for (int i = 0; i < byteBuf.readableBytes(); i++) {
            System.out.println((char) byteBuf.getByte(i));
        }

        System.out.println(byteBuf.getCharSequence(0, 4, Charset.forName("utf-8")));
        System.out.println(byteBuf.getCharSequence(4, 6, Charset.forName("utf-8")));

    }
}
