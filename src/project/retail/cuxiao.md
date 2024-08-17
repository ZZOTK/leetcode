## 促销活动的业务抽象

对于促销活动，
1. 在算价层面，可以按照计算方式和生效门槛来抽象
   * 优惠计算方式包括减（单次多次），折，单品特价
   * 生效门槛包括满元，满件，组合
   
2. 商品作用范围
   * 商品作用范围可以到店铺，类目，商品等
   
3. 用户作用范围
   * 标签，用户身份，限购等
   
## 优惠查询问题

优惠查询是放在es中完成。数据库不适用于商品找优惠（商品的优惠可能在不同的范围中）。

## 优惠计算问题

优惠的计算首先遵循一个基本原则，优惠在同级别互斥。比如存在两个店铺级优惠，那么只能生效一个。

如果需要计算出多种优惠叠加后的最优结果，需要用到回溯。流程如下

1. 对所有的优惠进行全排列+剪枝（这里的剪枝是同级别互斥的剪枝（当然必选优惠也需要），可以参考leetcode 90）
2. 对于每个优惠的计算，按照算价的抽象，实现算价方法，按照排列好的顺序去计算优惠
    * 对于多个优惠，可以将前几个优惠的计算结果进行缓存。例如优惠计算123456，和123546.如果将123的计算结果进行了缓存，那么计算后一个时，就不需要再从123开始，加快计算效率
3. 结果排序返回给前段(这里是否要做缓存)