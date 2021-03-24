# 剑指offer41
> 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

由于需要不断插入，实时求中位数，考虑使用优先队列。

使用两个优先队列，一个大根堆B存储数字小的一半，peek就是小的中最大的一个。小根堆A存储数字大的一般，peek就是大的中最小的一个。
1. 如果是第奇数个数add : 
    * 先将他放入B中，再将B的peek放入A中
    * 此时A中比B多一个数
2. 如果是第偶数个数add :
    * 先将他放入A中，再将A的peek放入B中
    
总之，随时保持A中所有数都比B的大！AB的peek值用来求中位数！
```java
public class MedianFinder {
    Queue<Integer> A, B;

    /** initialize your data structure here. */
    public MedianFinder() {
        A = new PriorityQueue<>();
        B = new PriorityQueue<>((x, y) -> (y - x));
    }

    public void addNum(int num) {
        //难点。
        if (A.size() != B.size()) {
            A.add(num);
            B.add(A.poll());
        } else {
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        if (A.size() != B.size()) {
            return A.peek();
        } else {
            return (A.peek() + B.peek()) / 2.0;
        }
    }
}
```