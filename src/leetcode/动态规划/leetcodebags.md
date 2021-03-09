## leetcode416
> 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。

思路：
转换为01背包。使用0-i个物品，装满sum/2的背包。物品重量为nums[i]，每个物品使用一次。

```java
public class leetcode416 {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        int target = 0;
        for ( int num : nums) {
            sum += num ;
        }
        //奇数直接返回
        if ( sum % 2 == 1){
            return false ;
        }else {
            target = sum / 2 ;
        }
        int len = nums.length ;
        boolean[][] dp = new boolean[len + 1][target + 1];
        for ( int i = 0; i < len + 1 ; i ++ ){
            dp[i][0] = true ;
        }
        for ( int i = 0 ; i < len ; i ++){
            for (int j = 1; j < target + 1 ; j ++ ){
                if (nums[i] > j){
                    dp[i + 1][j] = dp[i][j];
                }else {
                    dp[i+1][j] = dp[i][j] || dp[i][j - nums[i]];
                }
            }
        }
        return dp[len][target];
    }


    //压缩为一维的dp
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        int target = 0;
        for ( int num : nums) {
            sum += num ;
        }
        if ( sum % 2 == 1){
            return false ;
        }else {
            target = sum / 2 ;
        }
        int len = nums.length ;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true ;
        for ( int i = 0 ; i < len ; i ++){
            for (int j = target; j >= nums[i]; j -- ){
                dp[j] = dp[j] || dp[j - nums[i]];
                if(dp[target]){
                    return true;
                }
            }
        }
        return dp[target];
    }
}
```

## leetcode322
> 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
你可以认为每种硬币的数量是无限的。

### 背包恰好装满问题
背包问题有时候还有一个限制就是必须恰好装满背包，此时基本思路没有区别，只是在初始化的时候有所不同。

如果没有恰好装满背包的限制，我们将dp全部初始化成0就可以了。因为任何容量的背包都有一个合法解“什么都不装”，这个解的价值为0，所以初始时状态的值也就全部为0了。如果有恰好装满的限制，那只应该将dp[0,…,N][0]初始为0，其它dp值均初始化为-inf，因为此时只有容量为0的背包可以在什么也不装情况下被“恰好装满”，其它容量的背包初始均没有合法的解，应该被初始化为-inf。

思路 : 
* 如果我们将面值看作是物品，面值金额看成是物品的重量，每件物品的价值均为1，这样此题就是是一个恰好装满的完全背包问题了。
* 这里求最多装入多少物品而是求最少，又由于是恰好装满，所以除了dp[0]，其他都应初始化为一个最大值。

```java
public class leetcode322 {
    public int coinChange(int[] coins, int amount) {
        // 给 0 占位
        int[] dp = new int[amount + 1];
        // 注意：因为要比较的是最小值，这个不可能的值就得赋值成为一个最大值
        Arrays.fill(dp, amount + 1);
        // 理解 dp[0] = 0 的合理性，单独一枚硬币如果能够凑出面值，符合最优子结构
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] != amount + 1) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        if (dp[amount] == amount + 1) {
            dp[amount] = -1;
        }
        return dp[amount];
    }


    //BFS算法
    public int coinChange1(int[] coins, int amount) {
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        if(amount==0){return 0;}
        int count=0;
        Arrays.sort(coins);
        queue.add(amount);
        int n=coins.length;
        while(!queue.isEmpty()){
            int size=queue.size();
            count++;
            for(int i=0;i<size;i++){
                int cur=queue.poll();
                for(int j=n-1;j>=0;j--){
                    int next=cur-coins[j];
                    if(next<0){
                        continue;
                    }
                    if(next==0){
                        return count;
                    }
                    // if(!visited.contains(next)){
                    queue.offer(next);
                    // visited.add(next);
                    // }
                }
            }
        }
        return -1;
    }
}
```

## leetcode494
> 给定一个非负整数数组，a1, a2, ..., an, 和一个目标数，S。现在你有两个符号+和-。对于数组中的任意一个整数，你都可以从+或-中选择一个符号添加在前面。
返回可以使最终数组和为目标数 S 的所有添加符号的方法数。

