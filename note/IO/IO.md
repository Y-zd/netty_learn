## 流类

#### 流的概念
Java程序通过流来完成输人/输出。流是生产或消费信息的抽象。流通过Java的输人/输出系统与物理设备链接。
尽管与它们链接的物理设备不尽相同,所有流的行为具有同样的方式。这样,相同的输人输出类和方法适用于所有类型的外部设备。
这意味着一个输人流能够抽象多种不同类型的输人:从磁盘文件,从键盘或从网络套接字。同样，一个输出流可以输出到控制台,磁盘文件或相连的网络。
流是处理输人/输出的一个洁浄的方法,例如它不需要代码理解键盘和网络的不同。Java中流的实现是在 :Java.Io包定义的类层次结构内部的。


#### 输入/输出流概念
 1. 输入/输出时,数据在通信通道中流动。所谓“数据流(stream)”指的
是所有数据通信通道之中,数据的起点和终点。信息的通道就是一
个数据流。只要是数据从一个地方“流”到另外一个地方,这种数据
流动的通道都可以称为数据流。
 2. 输入输出是相对于程序来说的。程序在使用数据时所扮演的角色有
两个:一个是源,一个是目的。若程序是数据流的源,即数据的提
供者,这个数据流对程序来说就是一个“输出数据流”数据从程序流
出)。若程序是数据流的终点,这个数据流对程序而言就是一个“输
入数据流”(数据从程序外流向程序)


### 流的分类
 1. 输入流( Input Stream):可从中读出一系列字节的对象
```
输入流读数据的逻辑为:
 open a stream
 while more information
 read information
 close the stream
```
 2. 输出流(Output Stream):向其中写入一系列字节的对象
```
输出流写数据的逻辑为:
 open a stream
 while more information
 write information
 close the stream
```


### 流的分类
 1. 节点流:从特定的地方读写的流类,例如:磁盘或一块内存区域。
例如FileInputStream和FileOutputStream，他们直接从文件中读取或往文件中写入字节流。

2. 过滤流:使用节点流作为输入或输出过滤流是使用一个已经存在的输入流或输出流连接创建的。
例如BufferedInputStream,BufferedOutputStream,DataInputStream,DataOutputStream等,使用已经存在的节点流来构造,提供带缓冲的读写。


### Input Stream

   ![image](https://github.com/Y-zd/netty_learn/blob/master/note/IO/image/InputStream的类层次.png)

 1. InputStream中包含一套字节输入流需要的方法,可以完成最基本的从输入流读入数据的功能。
 当Java程序需要外设的数据时,可根据数据的不同形式,创建一个适当的InputStream子类类型的对象
来完成与该外设的连接,然后再调用执行这个流类对象的特定输入方法来实现对相应外设的输入操作。
 2. 常用的方法:
  * read():读数据的方法
  * available():获取输人流字节数的方法 
  * 定位输入位置指针的方法skip()、reset()、mark()等


### OutputStream

   ![image](https://github.com/Y-zd/netty_learn/blob/master/note/IO/image/OutputStream的类层次.png)

 1. OutputStream是定义了流式字节输出  模式的抽象类。
 2. 常用方法:
    * abstract void write(int b):往输出流中写入一个字节
    * void write(byte[] b):往输出流中写入数组b中的所有字节
    * void write(byte[] b,int off,int len):往输出流中写入数组b中从偏移量off开始的len个字节的数据
    * void flush():刷新输出流,强制缓冲区中的输出字节被写出
    * void close():关闭输出流,释放和这个流相关的系统资源



### Java IO库的设计原则

   ![image](https://github.com/Y-zd/netty_learn/blob/master/note/IO/image/IO流的链接.png)

 1. java的l/O库提供了一个称做链接的机制,可以将一个流与另一个流首尾相接,形成一个流管道的链接。
 这种机制实际上是一种被称为 Decorator(装饰)设计模式的应用。
   * 通过流的链接,可以动态的增加流的功能,而这种功能的增加是通
过组合一些流的基本功能而动态获取的。
   * 我们要获取一个1/O对象,往往需要产生多个/对象,这也是java IO库不太容易掌握的原因,
   但在IO库中Decoratort模式的运用,给我们提供了实现上的灵活性。

