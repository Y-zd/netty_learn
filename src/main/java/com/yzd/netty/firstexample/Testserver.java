package com.yzd.netty.firstexample;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/***
 *
 * @author : yanzhidong
 * @date : 2019/6/13 
 * @version : V1.0
 *
 */
public class Testserver {

    public static void main(String[] args) {

        //连接的接收
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //连接的处理
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(null);


    }


}
