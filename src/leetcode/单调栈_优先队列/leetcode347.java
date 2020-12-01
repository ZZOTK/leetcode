package leetcode.单调栈_优先队列;

import java.util.*;

//给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
//哈希表加堆排序。
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
//        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> ma.get(b)-ma.get(a) );
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
