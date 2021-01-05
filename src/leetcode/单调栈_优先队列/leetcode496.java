package leetcode.单调栈_优先队列;

//给定两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。找到nums1中每个元素在nums2中的下一个比其大的值。
//nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。

import java.util.HashMap;
import java.util.Stack;

public class leetcode496 {
    //方法1.nums1中逐一元素去nums2中倒序用最小栈找下一个最大的
    public int[] nextGreaterElement1(int[] nums1, int[] nums2) {
        int n1 = nums1.length;
        int n2 = nums2.length;
        int[] ans = new int[n1];
        for(int i = 0; i < n1; i ++){
            ans[i] = -1;
            Stack<Integer> sta = new Stack<>();
            for(int j = n2 - 1; j >= 0 ; j--){
                if(nums2[j] == nums1[i]){
                    while(!sta.isEmpty()){
                        int temp = sta.pop();
                        if(temp > nums2[j]){
                            ans[i] = temp;
                            break;
                        }
                    }
                }
                sta.push(nums2[j]);
            }
        }
        return ans;
    }

    //方法2.利用最小栈将nums2中每个元素下一个比他大的存入hashmap中。再对nums1中元素逐个查找hashmap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack < Integer > stack = new Stack < > ();
        HashMap< Integer, Integer > map = new HashMap < > ();
        int[] res = new int[nums1.length];

        for (int i = 0; i < nums2.length; i++) {
            while (!stack.empty() && nums2[i] > stack.peek())
                map.put(stack.pop(), nums2[i]);
            stack.push(nums2[i]);
        }
        //最后还在栈中的元素说明没有下一个比他大的元素。
        while (!stack.empty()){
            map.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }

}
