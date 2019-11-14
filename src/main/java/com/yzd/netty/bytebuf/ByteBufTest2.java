package com.yzd.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/14 
 * @version : V1.0
 *
 */
public class ByteBufTest2 {

    public static void main(String[] args) {
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heapBuf = Unpooled.buffer(10);

        ByteBuf directBuf = Unpooled.directBuffer(8);
        compositeByteBuf.addComponents(heapBuf, directBuf);
        compositeByteBuf.removeComponent(0);

        compositeByteBuf.forEach(System.out::println);

    }

}
