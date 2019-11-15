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
  
 ### EventLoopGroup
 1. 一个EventLoopGroup当中包含一个或多个EventLoop
 2. 一个EventLoop在它的整个生命周期当中都只会与唯一一个Thread进行绑定
 3. 所有由EventLoop所处理的各种I/O事件都将在它所关联的那个Thread上进行处理
 4. 一个Chanel在它的整个生命周期中只会注册在一个EventLoop上
 5. 一个EventLoop在运行过程当中，会被分配给一个或者多个Channel
 
 重要结论： 再netty中，Channel的实现一定是线程安全的；基于此，我们可以存储一个Channel的引用，在需要向远端发送数据时通过引用调相应方法；即便是很多线程
          在使用它也不会出现多线程问题，而且数据一定会发出出去（因为当若发送数据时不是当前Channel绑定的线程会以任务的形式提交到对应EventLoop的Thread）。
 
 重要结论： 我们在业务开发中，不要将长时间执行的耗时任务放入到evetLoop的执行队列中，因为它将会一直阻塞该线程所对应的所有Channel上的其他执行任务，如果我们
          需要进行阻塞调用或者耗时操作，那我们要使用一个专门的EventExecutor(业务线程池)。 

 业务线程池通常会有两种实现方式： 
        1. 在 ChannelHandler的回调方法中,使用自己定义的业务线程池,这样就可以实现异步调用。
        2. 借助于Netty提供的向ChannelPipeline添加ChannelHandler时调用的addLast方法来传递EventExecutor。默认情况下(调用addLast(handler),
           ChannelHandler中的回调方法都是由I/O线程所执行,如果调用了 ChannelPipeline addLast(EventExecutorGroup group, ChannelHandler... handlers);
           方法,那么 ChannelHandler中的回调方法就是由参数中的group线程组来执行的。

JDK所提供的Future只能通过手工方式检查执行结果，而这个操作是会阻塞的；Netty则对ChannelFuture进行增强，通过ChannelFutureListener以回调的方式获取执行结果，
。需要注意：ChannelFutureListener的operationComplete方法是由I/O线程执行的，因此不要在这里执行耗时操作。

###在Netty中种发送消息的方式
   1. 可以直接写到Channel中,消息会从ChannelPipeline的未尾开始流动,流经所有ChannelHandler。
   2. 也可以写到与ChannelHandler所关联的那个ChannelHandlerContext中,消息将从ChannelPipeline中的下一个ChannelHandler开始流动。

  结论： 1. ChannelHandlerContext与ChannelHandler之间的关联绑定关系是永远都不会发生改变的，因此对其进行缓存是没有问题的。 
        2. 对与ChannelHandlerContext和Channel的同名方法，ChannelHandlerContext的方法将会产生更短的事件流。 
     

###io.netty.buffer.ByteBuf
```java
       //Sequential Access Indexing 具体查看doc文档
       //      +-------------------+------------------+------------------+
       //      | discardable bytes |  readable bytes  |  writable bytes  |
       //      |                   |     (CONTENT)    |                  |
       //      +-------------------+------------------+------------------+
       //      |                   |                  |                  |
       //      0      <=      readerIndex   <=   writerIndex    <=    capacity
       
``` 
netty ByteBuf提供的三种缓冲区类型（还有池化的和非池化的）
1. heap ByteBuf: 在jvm堆上分配的，底层是数组
    这是常用的类型，将数据存到jvm的堆空间中，并将数据实际放到byte array中来实现。
    优点：由于数据在堆中，可以快速的创建与释放。并且提供了直接访问内部字节数组的方法。
    缺点：每次读写数据时，需要先将数据拷贝到堆外直接内存中再进行网络传输。
2. direct ByteBuf: 堆外直接内存
    在堆外直接分配内存，直接缓冲区并不会占用堆的容量，它是由操作系统在本地内存进行的数据分配。
    优点：在使用socket进行数据传输时，性能非常好，因为数据直接位于操作系统本地内存中。
    缺点：内存空间的分配与释放比堆空间分配更加复杂缓慢。
    netty通过提供内存池来解决这个问题，直接缓冲区不支持通过字节数组的方式访问数据。
3. composite ByteBuf: 复合缓冲区（java NIO 没有这种类型）        
    可以容纳上面两种Buf

重点：对于后端业务消息的编解码来说，推荐使用HeapByteBuf；对于I/O通信线程在读写缓冲区时，推荐使用DirectByteBuf。

###JDK中ByteBuffer与Netty中ByteBuf与的之间的差异比对
1. Netty中ByteBuf的采用了读写索引分离的略(readerIndex与writerIndex),一个初始化(里面尚未有任何数据)的ByteBuf的readerIndex与writerIndex与值都为0
2. Netty中当读索引与写索引处于同一个位置时,如果我们继续读取,那么就会抛出 IndexOutIOfBoundsException
3. 对于Netty中ByteBuf的任何读写操作都会分别单独维护读索引与写索引。maxCapacityI最大容量默认的限制就是Integer.MAX_VALUE 
4. Netty中ByteBuf自动扩容

JDK中ByteBuffer的缺点：
1. final byte[] hb;//存数据的字节数组未final一旦分配好不能动态扩容，如果空间不足，只能创建一个全新的Buffer对象将之前数据复制过去需要开发自己处理。
2. 只使用position指针来标识位置信息，在进行读写切换时需要调用flip方法或rewind方法，使用不便。
Netty中ByteBuf的优点：
1. 存数据的数组是动态的，容量最大值Integer.MAX_VALUE
2. 读写索引是完全分离的。

###(ByteBuf引用计数)ReferenceCounted中AtomicIntegerFieldUpdater要点总结：
1. 更新器更新的必须是int类型变量
2. 更新器更新的必须是volatile类型变量
3. 变量不能是static的，必须要是实例变量。cas操作本质上是通过对象实例的偏移量来直接进行赋值的。
4. 更新器只能改变它可见范围的变量，因为更新器是通过反射来得到变量的。

    
     
     