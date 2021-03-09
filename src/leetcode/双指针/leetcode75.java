package leetcode.双指针;
//给定一个包含红色、白色和蓝色，一共n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
//
//此题中，我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
//链接：https://leetcode-cn.com/problems/sort-colors


//两个思路，方法1先计数再赋值。方法二只需要扫描一遍数组。
public class leetcode75 {
    public void sortColors(int[] nums) {
        int c0 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int n : nums) {
            if (n == 0) {
                c0++;
            } else if (n == 1) {
                c1++;
            } else if (n == 2) {
                c2++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i < c0) {
                nums[i] = 0;
            } else if (i < c0 + c1) {
                nums[i] = 1;
            } else {
                nums[i] = 2;
            }
        }
    }

    //方法二，一次遍历。找到0放最前面，找到2放最后面。注意移动cur指针.
    public void sortColors2(int[] nums) {
        int p1=0;
        int cur=0;
        int len=nums.length;
        int p2=len-1;
        while (cur<=p2){
            if (nums[cur]==0){
                int temp=nums[p1];
                nums[p1]=nums[cur];
                nums[cur]=temp;
                cur++;
                p1++;
            }else if (nums[cur]==2){
                int temp=nums[p2];
                nums[p2]=nums[cur];
                nums[cur]=temp;
                p2--;//此处cur不需要往前移动
            }else {
                cur++;
            }
        }
    }


}
