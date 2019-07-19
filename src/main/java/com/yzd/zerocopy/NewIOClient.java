package com.yzd.zerocopy;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/***
 *
 * @author : yanzhidong
 * @date : 2019/7/17 
 * @version : V1.0
 *
 */
public class NewIOClient {

    public static void main(String[] args) throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost", 8899));
        socketChannel.configureBlocking(true);
        String fileName = "D:\\谷歌浏览器下载\\mysql-5.7.25-winx64.zip";
        long startTime = System.currentTimeMillis();
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        long transferTo = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送字节数：" + transferTo + ",耗时：" + (System.currentTimeMillis() - startTime));
        fileChannel.close();
    }


}
