package com.yzd.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/15 
 * @version : V1.0
 *
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("执行解码!");

        System.out.println(in.readableBytes());

        if (in.readableBytes() >= 0) {
            out.add(in.readLong());
        }

    }
}
