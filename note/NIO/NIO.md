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

*  关于nio Buffer中3个重要状态属性的含义:position，limit和capacity 
     0 <= mark <= position <= limit <= capacity