package com.yzd.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/***
 *  nio实现小的聊天系统 服务端
 * @author : yanzhidong
 * @date : 2019/7/9 
 * @version : V1.0
 *
 */
public class NioServer {


    private static Map<String, SocketChannel> clientMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(new InetSocketAddress(8899));

        Selector selector = Selector.open();

        //将serverSocketChannel注册到selector上
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);


        while (true) {
            //阻塞的方法，返回关注事件的数量
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            selectionKeys.forEach(
                    selectionKey -> {

                        final SocketChannel client;
                        try {
                            //发起了连接
                            if (selectionKey.isAcceptable()) {
                                ServerSocketChannel server = (ServerSocketChannel) selectionKey.channel();
                                client = server.accept();
                                client.configureBlocking(false);
                                client.register(selector, SelectionKey.OP_READ);
                                String key = "【" + UUID.randomUUID().toString() + "】";
                                clientMap.put(key, client);
                            } else if (selectionKey.isReadable()) {
                                client = (SocketChannel) selectionKey.channel();
                                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                                int count = client.read(readBuffer);
                                if (count > 0) {
                                    readBuffer.flip();
                                    Charset charset = Charset.forName("utf-8");
                                    String readMessage = String.valueOf(charset.decode(readBuffer).array());
                                    System.out.println(client + "：" + readMessage);

                                    String sendKey = null;

                                    for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                        if (client == entry.getValue()) {
                                            sendKey = entry.getKey();
                                            break;
                                        }
                                    }

                                    for (Map.Entry<String, SocketChannel> entry : clientMap.entrySet()) {
                                        SocketChannel value = entry.getValue();
                                        ByteBuffer writeBuffer = ByteBuffer.allocate(1024);
                                        writeBuffer.put((sendKey + "：" + readMessage).getBytes());
                                        writeBuffer.flip();
                                        value.write(writeBuffer);
                                    }

                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            selectionKeys.clear();


        }


    }

}
