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
        //连接的接收,连接的监听 EventLoopGroup bossGroup = new NioEventLoopGroup();
        //连接的处理 EventLoopGroup workerGroup = new NioEventLoopGroup();
        //ServerBootstrap serverBootstrap = new ServerBootstrap();
        //参数的设置 serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
        //                        .handler(new LoggingHandler(LogLevel.WARN)) handler针对bossGroup(parentGroup)
        //                        .childHandler(new MyServerInitializer());  /childHandler针对workerGroup 
          
```


## Reactor模式（反应器模式）
*   netty整体架构是Reactor模式的完整体现
*   大牛的文档<http://gee.cs.oswego.edu/dl/cpjslides/nio.pdf>
*   EventLoopGroup相当于Reactor
### Reactor模式的角色构造（5种角色构造）
 1. Handle(句柄或是描述符):本质上表示一种资源，是由操作系统提供的；该资源用于表示一个个的事件，比如文件描述符，或是针对网络编程中的Socket描述符。
    事件既可以来着外部，也可以来自内部；外部事件比如客户端的连接请求，客户端发送过来数据等；内部事件比如操作系统产生的定时器事件等。
    它本质上就是一个文件描述符。Handle是产生事件的发源地。
 2. Synchronous Event Demultiplexer(同步事件多路分离器):它本身是一个系统调用，用于等待事件的发生（事件可能是一个或多个）。调用方在调用它的
    时候会被阻塞，一直阻塞到同步事件分离器上有事件产生为止。对于Linux来说，同步事件分离器指的是常用的I/O多路复用的机制，比如select，poll，epoll
    等，在java nio中，同步事件分离器对应的组件就是Selector；对应的阻塞方法就是select方法。
 3. Event Handler(事件处理器):本身由多个回调方法构成，这些回调方法构成了与应用相关的对于某个事件的反馈机制。Netty相比java nio来说，在事件处理
    器这个角色上进行了一个升级，它为我们开发者提供了大量的回调方法，供我们在特定事件产生时实现相应的回调方法进行业务逻辑的处理。
 4. Concrete Event Handler(具体事件处理器):是事件处理的真正实现。它本身实现了事件处理器所提供的各个回调方法，从而实现了特定于业务的逻辑。它本
    质上就是我们所编写的一个个的处理器实现。
 5. Initiation Dispatcher(初始分发器):实际上就是Reactor角色，它本身定义了一些规范，这些规范用于控制事件的调度方式，同时又提供了应用进行事件处
    理器的注册、删除等设施。它本身是整个事件处理器的核心所在，Initiation Dispatcher会通过同步事件分离器来等待事件的发生。一旦事件发生，Initiation Dispatcher
    首先分离出每一个事件，然后调用事件处理器，最后调用相关的回调方法来处理这些事件。
### Reactor模式的流程
 1. 当应用向Initiation Dispatcher注册具体的事件处理器时，应用会标识出该事件处理器希望Initiation Dispatcher在某个事件发生时向其通知的该事件，
    该事件与Handle关联。
 2. Initiation Dispatcher会要求每个事件处理器向其传递内部的Handle。该Handler向操作系统标识了事件处理器。
 3. 当所有的事件处理器注册完毕后，应用会调用handle_event方法启动Initiation Dispatcher的事件循环。这时，Initiation Dispatcher会将每个注册的
    事件管理器的Handle合并起来，并使用同步事件分离器等待这些事件的发生。比如说，TCP协议层会使用select同步事件分离器操作来等待客户端发送的数据到达
    连接的socket handle上。
 4. 当与某个事件源对应的Handle变为ready状态时（比如说，TCP socket变为等待读状态时），同步事件分离器就会通知Initiation Dispatcher。
 5. Initiation Dispatcher会触发事件处理器的回调方法，从而响应这个处于ready状态的Handle。当事件发送时，Initiation Dispatcher会将事件源激活的
    Handle 作为 key 来寻找并分发恰当的事件处理器回调方法。
 6. Initiation Dispatcher会回调事件处理器的handle_event回调方法来执行特定于应用的功能（开发者自己所编写的功能）。从而响应这个事件。所发生的事件
    类型可以作为该方法参数并被该方法内部使用来执行额外的特定于服务的分离与分发。      
  
 
 


