package com.yzd.zerocopy;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;

/***
 *
 * @author : yanzhidong
 * @date : 2019/7/17 
 * @version : V1.0
 *
 */
public class OldIOClient {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 8899);
        String fileName = "D:\\谷歌浏览器下载\\mysql-5.7.25-winx64.zip";
        InputStream inputStream = new FileInputStream(fileName);
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
        byte[] buffer = new byte[4096];
        long read;
        long total = 0;
        long startTime = System.currentTimeMillis();
        while ((read = inputStream.read(buffer)) >= 0) {
            total += read;
            dataOutputStream.write(buffer);
        }
        System.out.println();
        System.out.println("发送字节数：" + total + ",耗时：" + (System.currentTimeMillis() - startTime));
        dataOutputStream.close();
        socket.close();
        inputStream.close();
    }
}
