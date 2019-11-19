package com.yzd.netty.handler3;

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
public class MyPersonEncoder extends MessageToByteEncoder<PersonProtocol> {
    @Override
    protected void encode(ChannelHandlerContext ctx, PersonProtocol msg, ByteBuf out) throws Exception {
        System.out.println("MyPersonEncoder 编码!");
        out.writeInt(msg.getLength());
        out.writeBytes(msg.getContent());

    }
}
