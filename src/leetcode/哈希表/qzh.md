最基本的前缀和：
1. 前缀和presum(n)表示前n的前缀和
2. 那么n到m个中的和就为presum(m) - presum(n)
3. hashmap中放入（前缀和，出现次数）

* 此处的前缀和是指题中的条件

利用hash表记录每个presum出现的次数。当他互补的条件出现时，结果加上出现的次数

* 例子：leetcode560，930,1248,974

## leetcode560

给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数

```java
    public int subarraySum(int[] nums, int k) {
        //前缀和
        int presum = 0;
        int count = 0;
        Map<Integer,Integer> ma = new HashMap<>();
        //第0个的和
        ma.put(0, 1);
        for(int num : nums){
            presum += num;
            //互补的数
            int tar = presum - k;
            if(ma.containsKey(tar)){
                //结果加上次数
                count += ma.get(tar);
            }
            ma.put(presum, ma.getOrDefault(presum, 0) + 1);
        }
        return  count;

    }
```

前缀和利用下标求解：

题中要求最短最长子数组（连续时），如果条件可以用前缀和计算，则将map中放入（前缀和，下标）

* 例子： leetcode1590,1371,1542

## leetcode1590
给你一个正整数数组nums，请你移除 最短子数组（可以为 空），使得剩余元素的 和能被 p整除。 不允许将整个数组都移除。

请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 -1。

子数组定义为原数组中连续的一组元素。

```java
    public int minSubarray(int[] nums, int p) {
        int goal = 0;
        for(int num : nums){
            goal += num;
            goal %= p;
        }
        //goal为整个数组和的余数
        if(goal == 0){
            return 0;
        }
        int presum = 0;
        int res= nums.length ;
        Map<Integer,Integer> map = new HashMap<>();
        map.put(0,0);
        for(int i = 0; i < nums.length; i ++){
            presum += nums[i];
            presum %= p;
            //确保为正
            int tar = (presum - goal + p) % p;
            if(map.containsKey(tar)){
                //利用下标求最大长度
                res = Math.min(i+1 - map.get(tar),res);
            }
            map.put(presum,i + 1);
        }
        if(res == nums.length){
            return -1;
        }
        return res;
    }
```

## leetcode1542
给你一个字符串 s 。请返回 s 中最长的 超赞子字符串 的长度。

「超赞子字符串」需满足满足下述两个条件：

1. 该字符串是 s 的一个非空子字符串
2. 进行任意次数的字符交换后，该字符串可以变成一个回文字符串

利用二进制位运算表示奇数偶数。

有10个数字，分别记录他的数量的奇偶状态，0为偶数，1为奇数，则需要1024个数字表示状态。

^运算：0^1 = 1； 1^1 = 0 可以用来表示奇数偶数之间的变换。

条件转换： 
1. s能表示回文，则s中最多只有一个字符出现了奇数次，其余的所有字符都出现了偶数次。
2. 前缀和奇偶完全相同，相减出来一定是偶数   
3. 允许一位为奇数次，则允许前缀和最多只有一位不同！


```java
    public int longestAwesome(String s) {
        int n = s.length();
        //数组记录1024个状态。
        int[] count = new int[1 << 10];
        Arrays.fill(count,-1);
        count[0] = 0;
        int presum = 0;
        int ans = 0;
        for(int i = 0 ; i < n ; i ++){
            int temp = s.charAt(i) - '0';
            //位运算记录下标
            presum ^= 1 << temp;
            //完全相同
            if(count[presum] >= 0){
                ans = Math.max(ans,i+1-count[presum]);
            }else{
                //只记录第一次出现的下标表示结果最大
                count[presum] = i + 1;
            }
            //一位不同
            for (int k = 0; k < 10; ++k) {
                if (count[presum ^ (1 << k)] >= 0) {
                    ans = Math.max(ans, i + 1 - count[presum ^ (1 << k)]);
                }
            }
        }
        return ans;

    }
```












