# Netty入门

## Reactor模式
Reactor的数量与处理资源池线程的数量不同，有三种典型实现：
1. 单Reactor单线程
2. 单Reactor多线程
3. 主从Reactor多线程

整体设计理念：I/O复用结合线程池

![img.png](reactorjb.png)

1. 服务端程序处理传入多个请求，并将他们同步分派到相应的处理线程。
2. 使用IO复用监听事件（Handler），收到事件后分发给各个线程。

### 单Reactor单线程、

![img.png](reactor1.png)

结合实例：服务器端用一个线程通过多路复用搞定所有的 IO 操作（包括连接，读、写等），编码简单，清晰明了，
但是如果客户端连接数量较多，将无法支撑。

### 单Reactor多线程

![img.png](reactor2.png)

1. Reactor 对象通过 Select 监控客户端请求事件，收到事件后，通过 Dispatch 进行分发
2. 如果是建立连接请求，则由 Acceptor 通过 accept 处理连接请求，然后创建一个 Handler 对象处理完成连接后的各种事件
3. 如果不是连接请求，则由 Reactor 分发调用连接对应的 handler 来处理（也就是说连接已经建立，后续客户端再来请求，那基本就是数据请求了，直接调用之前为这个连接创建好的handler来处理）
4. handler 只负责响应事件，不做具体的业务处理（这样不会使handler阻塞太久），通过 read 读取数据后，会分发给后面的 worker 线程池的某个线程处理业务。【业务处理是最费时的，所以将业务处理交给线程池去执行】
5. worker 线程池会分配独立线程完成真正的业务，并将结果返回给 handler
6. handler 收到响应后，通过 send 将结果返回给 client

* 优点：可以充分的利用多核 cpu 的处理能力
* 缺点：多线程数据共享和访问比较复杂。Reactor 承担所有的事件的监听和响应，它是单线程运行，在高并发场景容易出现性能瓶颈。也就是说Reactor主线程承担了过多的事

### 主从Reactor多线程

![img.png](reactor3.png)

1. Reactor 主线程 MainReactor 对象通过 select 监听连接事件，收到事件后，通过 Acceptor 处理连接事件
2. 当 Acceptor 处理连接事件后，MainReactor 将连接分配给 SubReactor
3. subreactor 将连接加入到连接队列进行监听，并创建 handler 进行各种事件处理
4. 当有新事件发生时，subreactor 就会调用对应的 handler 处理
5. handler 通过 read 读取数据，分发给后面的 worker 线程处理
6. worker 线程池分配独立的 worker 线程进行业务处理，并返回结果
7. handler 收到响应的结果后，再通过 send 将结果返回给 client
8. Reactor 主线程可以对应多个 Reactor 子线程，即 MainRecator 可以关联多个 SubReactor

* 优点：父线程与子线程的数据交互简单职责明确，父线程只需要接收新连接，子线程完成后续的业务处理。
* 优点：父线程与子线程的数据交互简单，Reactor 主线程只需要把新连接传给子线程，子线程无需返回数据。
* 缺点：编程复杂度较高

## Netty模型
简易版模型 ：

![img.png](ezmod.png)

1. BossGroup 线程维护 Selector，只关注 Accecpt
2. 当接收到 Accept 事件，获取到对应的 SocketChannel，封装成 NIOScoketChannel 并注册到 Worker 线程（事件循环），并进行维护
3. 当 Worker 线程监听到 Selector 中通道发生自己感兴趣的事件后，就进行处理（就由 handler），注意 handler 已经加入到通道

详细版模型：

![img.png](hardmod.png)

1. Netty 抽象出两组线程池 ，BossGroup 专门负责接收客户端的连接，WorkerGroup 专门负责网络的读写
2. BossGroup 和 WorkerGroup 类型都是 NioEventLoopGroup
3. NioEventLoopGroup 相当于一个事件循环组，这个组中含有多个事件循环，每一个事件循环是 NioEventLoop
4. NioEventLoop 表示一个不断循环的执行处理任务的线程，每个 NioEventLoop 都有一个 Selector，用于监听绑定在其上的 socket 的网络通讯
5. NioEventLoopGroup 可以有多个线程，即可以含有多个 NioEventLoop
6. 每个 BossGroup下面的NioEventLoop 循环执行的步骤有 3 步
    * 轮询 accept 事件
    * 处理 accept 事件，与 client 建立连接，生成 NioScocketChannel，并将其注册到某个 workerGroup NIOEventLoop 上的 Selector
    * 继续处理任务队列的任务，即 runAllTasks
7. 每个 WorkerGroup NIOEventLoop 循环执行的步骤
    * 轮询 read，write 事件
    * 处理 I/O 事件，即 read，write 事件，在对应 NioScocketChannel 处理
    * 处理任务队列的任务，即 runAllTasks
8. 每个 Worker NIOEventLoop 处理业务时，会使用 pipeline（管道），pipeline 中包含了 channel（通道），即通过 pipeline 可以获取到对应通道，管道中维护了很多的处理器。（这个点目前只是简单的讲，后面重点说）

## TaskQueue
每个NioEventLoop中，除了selector，还有一个重要的TaskQueue。

任务队列的典型应用场景：
1. 用户程序自定义的普通任务（TaskQueue）

```java
ctx.channel().eventLoop().execute(new Runnable() {}
```

2. 用户自定义定时任务（ScheduleTaskQueue）

```java
ctx.channel().eventLoop().schedule(new Runnable(){}
```

3. 非当前 Reactor 线程调用 Channel 的各种方法 例如在推送系统的业务线程里面，根据用户的标识，
   找到对应的 Channel 引用，然后调用 Write 类方法向该用户推送消息，就会进入到这种场景。最终的 Write 会提交到任务队列中后被异步消费
    * 实现思路 ：使用容器

进一步理解：
* Netty 抽象出两组线程池，BossGroup 专门负责接收客户端连接，WorkerGroup 专门负责网络读写操作。
* NioEventLoop 表示一个不断循环执行处理任务的线程，每个 NioEventLoop 都有一个 Selector，用于监听绑定在其上的 socket网络通道。
* NioEventLoop 内部采用串行化设计，从消息的 读取->解码->处理->编码->发送，始终由 IO 线程 NioEventLoop 负责
* NioEventLoopGroup 下包含多个 NioEventLoop
* 每个 NioEventLoop 中包含有一个 Selector，一个 taskQueue
* 每个 NioEventLoop 的 Selector 上可以注册监听多个 NioChannel
* 每个 NioChannel 只会绑定在唯一的 NioEventLoop 上
* 每个 NioChannel 都绑定有一个自己的 ChannelPipeline（互相绑定，可以互相获取）

## 异步模式
官方API的说法：

ChannelFuture的作用是用来保存Channel异步操作的结果。

我们知道，在Netty中所有的I/O操作都是异步的。这意味着任何的I/O调用都将立即返回，而不保证这些被请求的I/O操作在调用结束的时候已经完成。
取而代之地，你会得到一个返回的ChannelFuture实例，这个实例将给你一些关于I/O操作结果或者状态的信息。

也就是说，任何IO操作都会直接返回一个可以查看状态的ChannelFuture，而不是等待处理结束才返回.

1. 异步的概念和同步相对。当一个异步过程调用发出后，调用者不能立刻得到结果。实际处理这个调用的组件在完成后，通过状态、通知和回调来通知调用者。
2. Netty 中的 I/O 操作是异步的，包括 Bind、Write、Connect 等操作会首先简单的返回一个 ChannelFuture。
3. 调用者并不能立刻获得结果，而是通过 Future-Listener 机制，用户可以方便的主动获取或者通过通知机制获得 IO 操作结果。
4. Netty 的异步模型是建立在 future 和 callback 的之上的。callback 就是回调。重点说 Future，它的核心思想是：假设一个方法 fun，计算过程可能非常耗时，
   等待 fun 返回显然不合适。那么可以在调用 fun 的时候，立马返回一个 Future，后续可以通过 Future 去监控方法 fun 的处理过程（即：Future-Listener 机制）
   
![img.png](channelfuture.png)

被创建时的ChannelFuture处于uncompleted状态(非失败,非成功,非取消);一旦ChannelFuture完成I/O操作,ChannelFuture将处于completed状态,结果可能有三种:
1. 操作成功
2. 操作失败
3. 操作被取消(I/O操作被主动终止)


Future 说明
* 表示异步的执行结果,可以通过它提供的方法来检测执行是否完成，比如检索计算等等。
* ChannelFuture 是一个接口：public interface ChannelFuture extends Future<Void> 我们可以添加监听器，当监听的事件发生时，就会通知到监听器。

![img.png](lianshi.png)

Future-Listener机制： 
1. 当Future对象刚刚创建时，处于非完成状态，调用者可以通过返回的ChannelFuture来获取操作执行的状态，注册监听函数来执行完成后的操作

常见有如下操作:
* 通过isDone 方法来判断当前操作是否完成
* 通过isSuccess 方法来判断已完成的当前操作是否成功
* 通过 getCause 方法来获取已完成的当前操作失败的原因
* 通过 isCancelled 方法来判断已完成的当前操作是否被取消
* 通过 addListener 方法来注册监听器，当操作已完成(isDone 方法返回完成)，将会通知指定的监听器；如果Future 对象已完成，则通知指定的监听器