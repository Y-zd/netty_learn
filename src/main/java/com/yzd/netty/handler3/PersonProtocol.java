package com.yzd.netty.handler3;

/***
 *
 * @author : yanzhidong
 * @date : 2019/11/18 
 * @version : V1.0
 *
 */
public class PersonProtocol {

    private int length;

    private byte[] content;

    public int getLength() {
        return length;
    }

    public PersonProtocol setLength(int length) {
        this.length = length;
        return this;
    }

    public byte[] getContent() {
        return content;
    }

    public PersonProtocol setContent(byte[] content) {
        this.content = content;
        return this;
    }
}
