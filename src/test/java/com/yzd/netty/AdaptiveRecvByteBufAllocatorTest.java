package com.yzd.netty;

import java.util.ArrayList;
import java.util.List;

/***
 *
 * @author : yanzhidong
 * @date : 2019/8/1 
 * @version : V1.0
 *
 */
public class AdaptiveRecvByteBufAllocatorTest {
    private static final int[] SIZE_TABLE;

    static {
        List<Integer> sizeTable = new ArrayList<Integer>();
        for (int i = 16; i < 512; i += 16) {
            sizeTable.add(i);
        }

        for (int i = 512; i > 0; i <<= 1) {
            sizeTable.add(i);
        }

        SIZE_TABLE = new int[sizeTable.size()];
        for (int i = 0; i < SIZE_TABLE.length; i++) {
            SIZE_TABLE[i] = sizeTable.get(i);
        }
    }


    public static void main(String[] args) {
        AdaptiveRecvByteBufAllocatorTest adaptiveRecvByteBufAllocator = new AdaptiveRecvByteBufAllocatorTest();

        System.out.println(adaptiveRecvByteBufAllocator);
    }

}




