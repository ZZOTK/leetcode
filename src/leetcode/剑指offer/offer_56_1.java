package leetcode.剑指offer;
//一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
//无法使用哈希表，空间复杂度不满足。
//若只有一个不同可以使用异或

//思路：分组异或，确保两个不同的数在不同的组

public class offer_56_1 {
    public int[] singleNumbers(int[] nums) {
        //用k记录两个不同的数异或的结果
        int k = 0;
        for (int num : nums) {
            k ^= num;
        }

        //mask找到k中第一个不为0的位置，说明这两个数在这一位不同
        //以这位是否为0进行分组
        int mask = 1;
        while ((mask & k) == 0) {
            mask <<= 1;
        }

        //分组异或
        int a = 0;
        int b = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                a ^= num;
            } else {
                b ^= num;
            }
        }

        return new int[]{a, b};

    }
}
