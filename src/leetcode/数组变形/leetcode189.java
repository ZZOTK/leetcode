package leetcode.数组变形;
//不同方法
public class leetcode189 {
    public class Solution1 {
        public void rotate(int[] nums, int k) {
            int temp, previous;//暴力法，每次右移一个，整个数组有意k次
            for (int i = 0; i < k; i++) {
                previous = nums[nums.length - 1];
                for (int j = 0; j < nums.length; j++) {
                    temp = nums[j];
                    nums[j] = previous;
                    previous = temp;
                }
            }
        }
    }
    public class Solution2 {
        public void rotate(int[] nums, int k) {
            int[] a = new int[nums.length];//创立一个新数组
            for (int i = 0; i < nums.length; i++) {
                a[(i + k) % nums.length] = nums[i];//a的顺序是所求的
            }
            for (int i = 0; i < nums.length; i++) {
                nums[i] = a[i];//a赋回nums
            }
        }
    }

    public class Solution3 {
        public void rotate(int[] nums, int k) {
            k %= nums.length;//反转的方法
            reverse(nums, 0, nums.length - 1);
            reverse(nums, 0, k - 1);
            reverse(nums, k, nums.length - 1);
        }
        public void reverse(int[] nums, int start, int end) {
            while (start < end) {
                int temp = nums[start];
                nums[start] = nums[end];
                nums[end] = temp;
                start++;
                end--;
            }
        }
    }
}
