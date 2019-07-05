### java.io
 * java.io中最为核心的一个概念流(Stream)，面向流的编程。
   1. 一个流要么是输入流要么是输出流。
    

### java.nio
 * java.nio中有3个核心概念:Selector，Channel与Buffer,面向块(block)或是缓冲区(buffer)编程。
   1. Selector对应多个Channel，一个Channel对应一个Buffer。
   2. Buffer本身就是一块内存，底层实现上就是个数组。数据的读写都是通过Buffer实现的。
   3. 除了数组之外，Buffer还提供了对数据的结构化访问方式，并且可以追踪到系统的读写过程。同一个Buffer既能读也能写。
   4. java中的7种(没有BooleanBuffer)原生数据类型都有对应的Buffer类型，如：IntBuffer，ByteBuffer等。
   5. Channel指的是可以向其写入数据或者从中读取数据的对象，它类似于java.io中的Steam。所有数据的读写都是通过Buffer进行的。
   6. 与Stream不同的是Channel是双向的，Channel打开后可以进行读取，写入或者读写。
   7. 由于Channel是双向的，因此它能更好的反映出底层操作系统的真实情况。在Linux系统中，底层操作系统的通道是双向的。

*  关于nio Buffer中3个重要状态属性的含义:position，limit和capacity   [NioTest1](/src/main/java/com/yzd/nio/NioTest1.java)
   1. 0 <= mark <= position <= limit <= capacity

*  通过NIO读取文件涉及3个步骤： [NioTest4](/src/main/java/com/yzd/nio/NioTest4.java)

   1. 从FileInputStream获取FileChannel对象
   2. 创建Buffer
   3. 将数据从Channel读取到Buffer
   
*  绝对方法与相对方法的含义
   1. 相对方法：limit值与position值会在操作时被考虑到
   2. 绝对方法：完全忽略掉limit与position值   
       
*  DirectByteBuffer(buffer数组在堆外直接内存) [NioTest8](/src/main/java/com/yzd/nio/NioTest8.java) 和 HeapByteBuffer(buffer数组在堆内)
   1. DirectByteBuffer存放着指向堆外直接内存的的address
   2. HeapByteBuffer与操作系统交互数据的时候需要把数据拷贝到堆外直接内存
   3. DirectByteBuffer实现0拷贝

*  MappedByteBuffer 内存映射文件