# JAVA学习

> 本项目为个人学习用。除了自己总结的之外，还学习参考整理了许多的[博客与项目](#4)。
> 对于算法题，还总结了[leetcode刷题笔记](#2)，常用的[设计模式](#3)。
> 自己做的项目[总结分享](#5)

* JavaSE
    * [JAVA基础](src/JavaSE/se.md)
    * [聊聊String](src/JavaSE/String.md)
    
* 常用容器分析
    * [ArrayList与CopyOnWriteArrayList](src/JavaSE/List/arraylist.md)
    * [LinkedList](src/JavaSE/List/LinkedList.md)  
    * [HashMap的底层原理](src/JavaSE/Hashmap/hashmap.md)
    * [Concurrenthashmap底层原理分析](src/JavaSE/Hashmap/conhashmap.md)  
    * [红黑树](src/interview/redblacktree/redblack.md)

* 计算机网络
    * [计算机网络](src/network/计算机网络.md)
    * [数据链路层](src/network/数据链路层/lianlu.md)  
    * [网络层](src/network/网络层/wl.md)  
    * [应用层（HTTP）](src/network/应用层/http.md)
    * [传输层（TCP/UDP）](src/network/传输层/tcp.md)
    
* 操作系统
    * [操作系统概述（用户态内核态，系统调用）](src/OS/os.md)
    * [process进程](src/OS/mem/mem.md)
    * [memory内存管理](src/OS/process/process.md)
    * [linux系统入门](src/OS/linux.md)
  
* 多线程
    * [多线程入门](src/multi_thread/线程.md)
    * [说说各种锁](src/multi_thread/locks/lock/lock.md)
    * [AQS理解](src/multi_thread/AQS/AQS.md) 
    * [CAS机制理解](src/multi_thread/locks/CAS/cas.md)  
    * [深入Synchronized与volatile](src/multi_thread/locks/syn/syn.md)
    * [线程池](src/multi_thread/ExcutorService/xianchenchi.md)
    * [ThreadLocal](src/multi_thread/threadlocal/threadlocal.md)
    
* I/O
    * [五大IO模型](src/IO/socketIO.md)
    * [javaIO-NIO](src/IO/JAVAIO.md)

* **JVM**    
    * [类加载机制](src/JVM/classload/classloader.md)
    * [JVM内存](src/JVM/jvm.md)
    * [cpu使用过高怎么办](src/interview/cpu过高.md)
    * [内存泄漏问题](src/interview/MemoryLeak.md)
    
* SQL
    * [Mysql基础](src/sql/sql基础.md)
    * [Mysql索引](src/sql/sql索引/sql索引.md)
    * [Mysql事务](src/sql/sql事务.md)
    * [Innodb四大特性](src/sql/Innodb.md)
      
* Redis
    * [Redis基础](src/sql/redis/Redis.md)
    * [Redis的HashMap与跳表](src/sql/redis/sjjg/shuju.md)  
    * [BitMap简单入门](src/sql/redis/sjjg/bitmap.md)
    * [布隆过滤器BloomFilter](src/sql/redis/BloomFilter.md)  

    
* Spring框架
    * [Spring](src/SSM/Spring/Spring.md)
    * [SpringMVC](src/SSM/MVC/SpringMvc.md)
    * [SpringMVC源码解读](src/SSM/springmvccode.md)
    * [SpringIOC源码解读](src/SSM/springioc.md)
  
* 消息队列
    * [RabbitMQ](src/消息队列MQ/RabbitMQ.md)
    
* Netty
    * [Reactor与异步模型](src/rpcframe/Netty/reactor/reactor.md)
    * [Netty核心组件&心跳机制&WebSocket长链接](src/rpcframe/Netty/zujian/Netty核心组件.md)
    * [序列化&TCP粘包](src/rpcframe/Netty/up/nettyup.md)
    
* 分布式
    * [分布式理论(一致性)](src/distributed/theory/theory.md)
    * [分布式锁](src/distributed/lock/distributedlock.md)
    * [分布式事务](src/distributed/trans/trans.md)

<h2 id="5">个人项目与分享</h2>

* 门户网站
    * [数据库索引与缓存](src/project/project.md)
    * [网关](src/project/gateway/gateway.md)
    * [token及登录](src/project/token/token.md)

* RPC框架
    * [zookeeper](src/rpcframe/zookeeper/zookeeper.md)
    * [Netty](src/rpcframe/Netty/reactor/reactor.md) 
    
<h2 id="2">leetcode刷题笔记</h2>

* [常用的数学计算](src/leetcode/math.md)
* [常用排序总结](src/leetcode/归并_快排/sort.md)

* 位运算
    * [如何计算二进制数中1的个数](src/leetcode/位运算/byte.md)
    * [不用加号的加法](src/leetcode/剑指offer/byteadd.md)
    
* **二分查找**
    * [二分查找的左右边界](src/leetcode/双指针_二分/二分查找.md)
    * [二分查找的判定问题](src/leetcode/双指针_二分/erfen.md) 
    * [最长递增子序列](src/leetcode/双指针_二分/txef.md)
    
* 前缀和
    * [一般的前缀和做法](src/leetcode/哈希表/qzh.md)
* 回文
    * [回文字符串如何分析](src/leetcode/回文/huiwen.md)

* **动态规划**
    * [经典的背包问题](src/leetcode/动态规划/bags.md)
    * [leetcode中的背包问题](src/leetcode/动态规划/leetcodebags.md)
    * [分组dp](src/leetcode/动态规划/fengzudp.md)
    * [最长公共子串与子序列](src/leetcode/动态规划/lencommon.md)
   
* 二叉树二叉搜索树 
    * [二叉树的中序遍历](src/leetcode/树/inorder.md)
    * [lca最近公共节点](src/leetcode/树/lca.md)
    * [BST删除节点](src/leetcode/树/BST.md)
    * [子树问题](src/leetcode/程序员面试金典/subtree.md)
  
* 优先队列
    * [PriorityQueue优先队列](src/leetcode/单调栈_优先队列/pq.md)
    * [优先队列怎么用](src/leetcode/单调栈_优先队列/priority.md)
    * [优先队列配贪心]()
    * [数据流的中位数](src/leetcode/单调栈_优先队列/offer41.md)
    
* 栈
    * [简化文件路径]()
    * [逆波兰表达式（后缀表达式）](src/leetcode/模拟/nbl.md)
    * [栈的妙用——单调栈](src/leetcode/单调栈_优先队列/stack.md)
  
* 分治与归并
    * [写个归并排序呗](src/leetcode/归并_快排/mergesort.md)
    * [逆序对]()
    
* 回溯
    * [和为target的方案](src/leetcode/回溯_DFS_BFS/btrack_sum.md)

* 链表
    * [有环无环环入口](src/leetcode/链表/huan.md)
    * [反转！](src/leetcode/链表/reverse.md)
    
* 并查集
    * [并查集模板](src/leetcode/并查集/uf.md)
    
* 图

* 字典树
    * [实现一个Trie](src/leetcode/字典树/trie.md)

<h2 id="3">设计模式</h2>

* [单例模式](src/设计模式/single/single.md)
* [工厂模式](src/设计模式/factory/factory.md)
* [代理模式](src/设计模式/Proxy/proxy.md)
* [适配器模式](src/设计模式/adapter/adapter.md)

<h2 id="4">感谢</h2>

**感谢这些优秀的开源项目！**

* https://github.com/CyC2018/CS-Notes
* https://github.com/Snailclimb/JavaGuide
* https://github.com/geekxh/hello-algorithm
