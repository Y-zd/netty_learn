package com.yzd.netty.handler3;

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
public class MyPersonDecoder extends ReplayingDecoder<Void> {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        System.out.println("MyPersonDecoder 解码!");
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);
        PersonProtocol personProtocol = new PersonProtocol();
        personProtocol.setLength(length).setContent(content);
        out.add(personProtocol);
    }
}
