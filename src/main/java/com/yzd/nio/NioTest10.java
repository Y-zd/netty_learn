package com.yzd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/***
 *
 * 关于Buffer的 Scattering 与 Gathering
 *
 *  这是channel操作buffer的两个特性，发散和聚合
 *   发散是指从channel中读取数据的时候可以传入buffer的数组，并依次将buffer数组写满，直到channel中无数据
 *   聚合是指往channel中写入数据时可以传入buffer数据，并依次将buffer数据中的数据读入channel中，直到无数据
 *
 * @author : yanzhidong
 * @date : 2019/7/5 
 * @version : V1.0
 *
 */
public class NioTest10 {

    public static void main(String[] args) throws IOException {
        //telnet 10.28.6.202 8899 连接此socket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();


        InetSocketAddress address = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(address);


        int messageLength = 2 + 3 + 4;

        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);
        SocketChannel socketChannel = serverSocketChannel.accept();

        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long r = socketChannel.read(byteBuffers);
                byteRead += r;
                System.out.println("byteRead:" + byteRead);
                Arrays.asList(byteBuffers).stream().map(byteBuffer -> "position:" + byteBuffer.position() + ",limit:" + byteBuffer.limit())
                        .forEach(System.out::println);
            }

            Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::flip);
            long byteWritten = 0;
            while (byteWritten < messageLength) {
                long r = socketChannel.write(byteBuffers);
                byteWritten += r;
            }
            Arrays.asList(byteBuffers).stream().forEach(ByteBuffer::clear);
        }
    }

}
