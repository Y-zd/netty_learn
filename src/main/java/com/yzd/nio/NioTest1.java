package com.yzd.nio;

import java.nio.IntBuffer;
import java.security.SecureRandom;

/***
 *  Buffer
 * @author : yanzhidong
 * @date : 2019/6/27 
 * @version : V1.0
 *
 */
public class NioTest1 {
    public static void main(String[] args) {
        IntBuffer buffer = IntBuffer.allocate(10);
        System.out.println("capacity:" + buffer.capacity());
        for (int i = 0; i < 5; i++) {
            int randomNunber = new SecureRandom().nextInt(20);
            buffer.put(randomNunber);
        }
        System.out.println("before flip limit:" + buffer.limit());
        //前面buffer.put是对buffer进行写调用flip方法将buffer翻转为读
        buffer.flip();
        System.out.println("after flip limit:" + buffer.limit());
        while (buffer.hasRemaining()) {
            System.out.println("position:" + buffer.position());
            System.out.println("limit:" + buffer.limit());
            System.out.println("capacity:" + buffer.capacity());
            System.out.println(buffer.get());
        }
    }

}
