package com.yzd.netty.handler3;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.nio.charset.Charset;
import java.util.UUID;

/***
 *
 * @author : yanzhidong
 * @date : 2019/6/15 
 * @version : V1.0
 *
 */
public class MyServerHandler extends SimpleChannelInboundHandler<PersonProtocol> {

    private int count;


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, PersonProtocol msg) throws Exception {
        System.out.println("服务器端接收到的数据：");
        System.out.println("长度：" + msg.getLength());
        System.out.println("内容：" + new String(msg.getContent(), Charset.forName("utf-8")));
        System.out.println("服务器端接收到的消息数量：" + (++this.count));

        String responseMessage = UUID.randomUUID().toString();
        byte[] messageBytes = responseMessage.getBytes(Charset.forName("utf-8"));
        PersonProtocol personProtocol = new PersonProtocol().setLength(messageBytes.length).setContent(messageBytes);
        ctx.writeAndFlush(personProtocol);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
