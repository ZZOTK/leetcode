# Nosql四大分类

## KV键值对
* 新浪 ：Redis
* 美团 ：Redis + Tair
* 阿里、百度 ：Redis + memecache

## 文档型数据库（bson格式，类似json）
* MongoDB 
  * MongoDB是一个基于分布式文件存储的数据库，C++编写，处理大量数据文档。
  * MongoDB是一个介于关系型数据库与非关系型数据库中间的产品。是非关系型数据库中功能最多，最像关系型数据库的。
    
*ConthDB

## 列存储数据库
* HBase
* 分布式文件系统

## 图关系数据库
* 存放拓扑关系，如Neo4j，InfoGrid

# Redis概述
* Redis（Remote Dictionary Server )，即远程字典服务。官方表示有100000+的QPS（每秒查询效率）
* Redis可以做到 ： 内存存储，持久化（rdb，aof），发布订阅系统，地图信息分析，计时器，计数器。。。
* 特性 ：数据多样，持久化，集群，事务
* Redis为单线程（核心模块）（网络I/O已经有多线程）
  * Redis是基于内存操作的，cpu不是他的性能瓶颈。
  * Redis的瓶颈是内存大小和网络带宽。
    
## Q：Redis为什么单线程还这么快
* 多线程由于CPU上下文切换，不一定比单线程效率高。
* Redis将所有数据都放在内存中，所以单线程操作速度很快。
* 高效数据结构。  
* 合理的数据编码。  
* I/O使用多路复用技术。Redis使用的是非阻塞IO，IO多路复用，使用了单线程来轮询描述符，将数据库的开、关、读、写都转换成了事件，减少了线程切换时上下文的切换和竞争。
* Redis的高并发能力，主要看它的网络模型，单线程的redis是不如多线程的mc的。

## Q : 说一下 Redis 和 Memcached 的区别和共同点
共同点 ： 
* 都是基于内存的数据库，一般都用来当做缓存使用。
* 都有过期策略。
* 两者的性能都非常高

不同点 ： 
* **Redis 支持更丰富的数据类型**（支持更复杂的应用场景）。Memcached 只支持最简单的 k/v 数据类型。Redis还提供 list，set，zset，hash 等数据结构的存储。
* **Redis 支持数据的持久化**，可以将内存中的数据保持在磁盘中，重启的时候可以再次加载进行使用,而 Memecache 把数据全部存在内存之中。
* Redis 有灾难恢复机制。 因为可以把缓存中的数据持久化到磁盘上。
* Memcached 是多线程，非阻塞 IO 复用的网络模型；**Redis 使用单线程的多路 IO 复用模型。** （Redis 6.0 引入了多线程 IO ）
* **Redis 支持发布订阅模型、Lua 脚本、事务等功能**，而 Memcached 不支持。并且，Redis 支持更多的编程语言。
* Memcached过期数据的删除策略只用了惰性删除，而 Redis 同时使用了惰性删除与定期删除。

# Redis数据类型
* Redis有五大数据类型 ：
  * String
  * List
  * Set
  * Hash
  * Zset
   
* 三种特殊的数据类型 : 
  * geospatial 地理位置
  * Hyperloglog 基数统计
  * Bitmap 位图场景 

