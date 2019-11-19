package com.yzd.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/18 
 * @version : V1.0
 *
 */
public class MyLongToByteEncoder extends MessageToByteEncoder<Long> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Long msg, ByteBuf out) throws Exception {
        System.out.println("执行编码!");
        System.out.println(msg);
        out.writeLong(msg);
    }
}
