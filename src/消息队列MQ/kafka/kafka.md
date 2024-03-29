# kafka 基本概念



# 何时kafka会丢失消息
* 生产者丢失消息
    * 目前 Kafka Producer 是异步发送消息的，如果你的 Producer 客户端使用了 producer.send(msg) 方法来发送消息，方法会立即返回，但此时并不能代表消息已经发送成功了。
    * 如果消息再发送的过程中发生了网络抖动，那么消息可能没有传递到 Broker，那么消息可能会丢失。
    * 如果发送的消息本身不符合，如大小超过了 Broker 的承受能力等。

* Kafka Broker 服务端丢失消息
  * Leader Broker 宕机了，触发选举过程，集群选举了一个落后 Leader 太多的 Broker 作为 Leader，那么落后的那些消息就会丢失了。
  * Kafka 为了提升性能，使用页缓存机制，将消息写入页缓存而非直接持久化至磁盘，采用了异步批量刷盘机制，也就是说，按照一定的消息量和时间间隔去刷盘，刷盘的动作由操作系统来调度的，如果刷盘之前，Broker 宕机了，重启后在页缓存的这部分消息则会丢失。

* 消费者丢失消息
  * 消费者拉取了消息，并处理了消息，但处理消息异常了导致失败，并且提交了偏移量，消费者重启后，会从之前已提交的位移的下一个位置重新开始消费，消费失败的那些消息不会再次处理，即相当于消费者丢失了消息。
  * 消费者拉取了消息，并提交了消费位移，但是在消息处理结束之前突然发生了宕机等故障，消费者重启后，会从之前已提交的位移的下一个位置重新开始消费，之前未处理完成的消息不会再次处理，即相当于消费者丢失了消息。


# 实践中如何kafka的消息可靠性

kafka的可靠性从生产者发送，存储，和消费者三个层面入手

* 生产者提高消息可靠性
    * 不要使用 producer.send(msg)，而要使用 producer.send(msg, callback)。带有回调通知的 send 方法可以针对发送失败的消息进行重试处理。
    * 设置 acks  
      * 如果acks=0，生产者在成功写入消息之前不会等待任何来自服务器的响应。如果当中出现了问题，导致服务器没有收到消息，那么生产者就无从得知，消息也就丢失了。可以达到很高的吞吐量。
      * 如果acks=1，只要集群的首领节点收到消息，生产者就会收到一个来自服务器的成功响应。如果消息无法到达首领节点（比如首领节点崩溃，新的首领还没有被选举出来），生产者会收到一个错误响应，为了避免数据丢失，生产者会重发消息。不过，如果一个没有收到消息的节点成为新首领，消息还是会丢失
      * 如果acks=all，只有当所有参与复制的节点全部收到消息时，生产者才会收到一个来自服务器的成功响应。这种模式是最安全的，它可以保证不止一个服务器收到消息，就算有服务器发生崩溃，整个集群仍然可以运行；不过，它的延迟比acks=1时更高，因为我们要等待不只一个服务器节点接收消息
    * 设置retries = 3，当出现网络的瞬时抖动时，消息发送可能会失败，此时配置了 retries > 0 的 Producer 能够自动重试消息发送，避免消息丢失。

* kafka Broker提高消息可靠性
  * 增加存储副本等方法提高存储可靠性
  * 降低buffer大小，更快的往磁盘写

* kafka消费者提高可靠性
  * 确保消息消费完成再提交。最好把它设置成enable.auto.commit = false，并采用手动提交位移的方式。这对于单 Consumer 多线程处理的场景而言是至关重要的。
  * Kafka 没有重试机制不支持消息重试，也没有死信队列，因此使用 Kafka 做消息队列时，需要自己实现消息重试的功能。


# kafka实现重试/延时队列
* 创建一个 Topic 作为重试 Topic，用于接收等待重试的消息。
* 从重试 Topic 获取待重试消息存储到 Redis 的 ZSet 中，并以下一次消费时间排序。
* 定时任务从 Redis 获取到达消费时间的消息，并把消息发送到对应的 Topic。
* 同一个消息重试次数过多则不再重试。

# kafka消息有序性

kafka消息在partition内是有序的。所以
* 只有一个分区
* 发送时指定key/分区

特殊情况：
* 当有消息发送失败重试时，其实kafka的有序性依然被打破了（异步队列阻塞提交、open-nsc回调）
* 接收方如果是多线程异步处理，有序性也不能保证（接收方自己处理）