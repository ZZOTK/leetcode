package leetcode.单调栈_优先队列;

//给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，
// 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。

//数组复制一遍在后面，用取余表示。

import java.util.Arrays;
import java.util.Stack;

public class leetcode503 {
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Arrays.fill(res, -1);
        Stack<Integer> stack = new Stack<>();   // 存储的是索引index

        // 由于是循环数组,对于末位元素,要往后循环考虑到其前一位数也就是倒数第二个数
        for(int i = 0; i < 2 * nums.length; i++) {
            // 对于当前元素,弹出栈中小于当前元素的元素,这些被弹出的元素的"下一个更大元素"就是当前元素
            // 一个元素只有入栈后再被弹出,才能得到该元素的"下一个更大元素",否则无"下一个更大元素",res[i]默认为-1
            // 例如,对于最大的元素,其一直留在栈中无法被弹出,因此其"下一个更大元素"默认为-1
            while(!stack.empty() && nums[stack.peek()] < nums[i % nums.length])
                res[stack.pop()] = nums[i % nums.length];
            stack.push(i % nums.length);
        }

        return res;
    }

}
