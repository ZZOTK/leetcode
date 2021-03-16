## leetcode347
> 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

统计数组出现的频率我们可以使用哈希表。

如果只是返回数组前k大的元素，我们可以直接sort。但是对于哈希表中的结果，我们无法sort。

此时，就可以借用优先队列。使用哈希表统计出现频率，再将每个元素依次加入优先队列，将出现次数作为标准进行排序。
* 我们可以使顶部为最大元素，那么就要全部入队列，再poll出k个
* 依然使顶部为最小，维持队列最大容量为k。有比顶部元素大的再入队列。

显然，方法二更加节省内存和时间。

```java
public class leetcode347 {
    public int[] topKFrequent(int[] nums, int k) {
        //哈希表存数据。key为值，value为出现的次数
        Map<Integer,Integer> ma=new HashMap<>();
        for(int num:nums){
            int count = ma.getOrDefault( num,0)+1;
            ma.put(num, count);
        }

        //优先队列进行排序。comparator比较器实现比较。
        //lamda表达式写法
        //PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> ma.get(b)-ma.get(a) );
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ma.get(o1) - ma.get(o2);
            }
        });
        //依次加入队列
        for(int key : ma.keySet()){
            //最多加入k个元素
            if (pq.size() < k){
                pq.add(key);
            }
            //如果新key的value大于之前最小的value，则一出顶，再入队
            else if(ma.get(key) > ma.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }

        int[] res = new int[k];
        int i=0;
        while (!pq.isEmpty()){
            res[i ++ ]=pq.remove();
        }
        return res;
    }
}
```
