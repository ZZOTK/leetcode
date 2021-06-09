## leetcode300
给你一个整数数组 nums ，找到其中最长严格递增子序列的长度。

子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，[3,6,2,7] 是数组 [0,3,1,6,2,2,7] 的子序列。

思路一：动态规划
```java
    //dp[i]表示到第i个结尾的最大长度
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int res = 1;
        for(int i = 1 ; i < n; i ++){
            dp[i] = 1;
            for(int j = 0; j < i ;j ++){
                if(nums[i] > nums[j] ){
                    dp[i] = Math.max(dp[i],dp[j] + 1);
                }
            }
            res = Math.max(res,dp[i]);
        }
        return res;
    }
```

优化到nlog(n): 贪心加二分的思路

定义一个tail数组，tail[i]表示长度为 i + 1 的上升子序列的末尾最小是i

第i+ 1个数过来时，如果比tail[i]大，直接加在后面，否则找到第 1 个大于等于 nums[i+1] 的元素，尝试让那个元素更小

```java
    public int lengthOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) {
            return len;
        }
        
        int[] tail = new int[len];
        tail[0] = nums[0];
        int end = 0;

        for (int i = 1; i < len; i++) {
            if (nums[i] > tail[end]) {
                // 直接添加在那个元素的后面，所以 end 先加 1
                end++;
                tail[end] = nums[i];
            } else {
                // 使用二分查找法，在有序数组 tail中找到第 1 个大于等于 nums[i] 的元素
                // 右边界
                int left = 0;
                int right = end;
                while (left < right) {
                    int mid = left + ((right - left) / 2);
                    if (tail[mid] < nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                tail[left] = nums[i];
            }
        }
        end++;
        return end;
    }
```

如果要输出这个序列，则遍历tail数组。







