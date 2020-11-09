package leetcode.数组_变形_贪心;
//假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
//自定义comparator比较器。
//compare( a , b ){return a - b ;}此时是按照a>b,则sort后从小到大排列
//return b - a ; 此时sort后则从大到小排列。
//条件 ？ A ： B   判断条件是否成立。成立执行A ， 否则执行B

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class leetcode406 {
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                // if the heights are equal, compare k-values
                //先按照高h从大到小排列。同h再按照k从小到大排列。
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0];
            }
        });

        List<int[]> output = new LinkedList<>();
        for(int[] p : people){
            //在第p[1]个位置插入p
            output.add(p[1], p);
        }

        int n = people.length;
        return output.toArray(new int[n][2]);
    }
}
