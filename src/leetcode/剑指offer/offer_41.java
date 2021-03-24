package leetcode.剑指offer;

import java.util.PriorityQueue;
import java.util.Queue;

//如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有数值排序之后位于中间的数值。如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。

//利用一个大根堆与一个小根堆交替，实现实时的寻找中位数
public class offer_41 {
    Queue<Integer> A,B;
    /** initialize your data structure here. */
    public void MedianFinder() {
        A = new PriorityQueue<>();
        B = new PriorityQueue<>((x,y) -> (y-x));
    }

    public void addNum(int num) {
        if(A.size() != B.size()){
            A.add(num);
            B.add(A.poll());
        }else{
            B.add(num);
            A.add(B.poll());
        }
    }

    public double findMedian() {
        if(A.size()!=B.size()){
            return A.peek();
        }else{
            return (A.peek() + B.peek())/2.0;
        }
    }
}
