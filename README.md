netty学习
==

## JAVA IO
*  <https://github.com/Y-zd/netty_learn/blob/master/note/IO/IO.md>


## JAVA NIO
*  <https://github.com/Y-zd/netty_learn/blob/master/note/NIO/NIO.md>


## RPC:Remote Procedure Call,远程过程调用
 1. 定义一个接口说明文件：描述了对象（结构体），对象成员，接口方法等一系列信息。
 2. 通过RPC框架所提供的编译器，将接口说明文件编译成具体语言文件。
 3. 在客户端与服务器端分别引入RPC编译器所生成的文件，即可像调用本地方法一样调用远程方法。
 

## netty
*  demo
   1. netty http服务器  [package-info](/src/main/java/com/yzd/netty/firstexample/package-info.java)
   2. netty socket     [package-info](/src/main/java/com/yzd/netty/secondexample/package-info.java)
   3. netty 多客户端连通 [package-info](/src/main/java/com/yzd/netty/thirdexample/package-info.java)
   4. netty 读写检测机制 [package-info](/src/main/java/com/yzd/netty/fourthexample/package-info.java)
   5. netty WebSocket  [package-info](/src/main/java/com/yzd/netty/fifthexample/package-info.java)
```java
        //连接的接收 EventLoopGroup bossGroup = new NioEventLoopGroup();
        //连接的处理 EventLoopGroup workerGroup = new NioEventLoopGroup();
        //handler针对bossGroup
        //childHandler针对workerGroup         
```


