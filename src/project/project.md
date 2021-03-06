
## 读写分离的情况下如何保持数据一致性
![img_1.png](dxfl.png)

MySQL之间数据复制的基础是二进制日志文件（binary log file）。一台MySQL数据库一旦启用二进制日志后，其作为master，它的数据库中所有操作都会以“事件”的方式记录在二进制日志中，其他数据库作为slave通过一个I/O线程与主服务器保持通信，并监控master的二进制日志文件的变化，如果发现master二进制日志文件发生变化，则会把变化复制到自己的中继日志（Relay log）中，然后slave的一个SQL线程会把相关的“事件”执行到自己的数据库中，以此实现从数据库和主数据库的一致性，也就实现了主从复制。

简言之可分为下面几个步骤：
1. 主库的更新事件会被写到bin log日志中。
2. 从库启用slave服务，发起连接，连接到主库。
3. 从库创建一个I/O线程，从主库读取bin log日志的内容并写入到relay log日志中。
4. 从库创建一个SQL线程，从relay log里面读取内容，将更新内容写入到从库。


## 利用redis优化点赞功能

问题： 帖子点赞数是在数据库的，一个用户不能重复点赞

* 思路一：利用数据库存储是否对这个点赞
    * 设计数据库时没有考虑，比较复杂
    
* 思路二： 利用redis
    * 当点赞后，在redis中，设置一个帖子id+用户id，并将它给个值1
    * 点赞时判断，如果get(帖子id+用户id)不为null，则返回已经点赞了


