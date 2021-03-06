## 1000台机器，每台机器1000个 文件，每个文件存储了10亿个 整数，如何找到其中最小的1000个值？

## 分治 + hash + 大顶堆
1. 对于每台机器中每个文件存储的10亿个整数，使用hash将10亿个数据分成1000份，每份100万个数据
2. 通过大顶堆的方法找每份数据中最小的1000个值
3. 对于每台机器上1000个文件，一共有1000*1000=100w个值，再使用大顶堆整个机器上1000份数据集最小的1000个值
4. 因为一共1000台机器，每台机器1000个值，最后将这100w数据汇总都传输到一台机器上通过大顶堆找出最小的1000个值

时间复杂度：O(NlogK)，其中 N= 1000台机器 * （1000份文件 + 1次汇总）* 1000，K = 1000

## Bit Map
如果每个整数使用1 bit来表示，那么10亿个整数 = 2^32 bit/8 = 2^29 Byte，大约等于512MB

1. 每台机器上申请一个int数组长度为 int tmp[N/32+1]即可存储完这些数据，其中N代表要进行查找的总数（这里也就是2^32），tmp中的每个元素在内存在占32位可以对应表示十进制数0~31,所以可得到BitMap表。
2. 再顺序扫描这10亿的数，在对应的bit位上标记该数是否出现过。
3. 然后找出每台机器上1000个最小值
4. 最后将这100w数据汇总都传输到一台机器上通过大顶堆找出最小的1000个值

时间复杂度：O(N+100W * logK)，其中N是总数据量，K = 1000