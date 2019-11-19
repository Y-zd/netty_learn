package com.yzd.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/18 
 * @version : V1.0
 *
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("执行解码2!");
        out.add(in.readLong());
    }
}
