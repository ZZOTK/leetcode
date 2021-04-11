为什么要用分组dp？

dp表示的状态需要有单向性。即上一个状态推出下一个状态。当状态间可以互相转换时，我们就需要分组讨论。

## leetcode1262
> 给你一个整数数组 nums，请你找出并返回能被三整除的元素最大和。

对于数字余数为0,1,2分开讨论。

思考：当不是三，而是k时，怎么写。

```java
    public int maxSumDivThree(int[] nums) {
        int n = nums.length;

        int[][] dp = new int[n+1][3];
        //这里用最小值表示，让第一个余数为1和2的数结果是正确的
        dp[0][0] = 0;
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][2] = Integer.MIN_VALUE;

        for (int i = 1; i <= n; i++) {
            if (nums[i-1] % 3 == 0) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][0] + nums[i-1]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][1] + nums[i-1]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][2] + nums[i-1]);
            } else if (nums[i-1] % 3 == 1) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][2] + nums[i-1]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] + nums[i-1]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][1] + nums[i-1]);
            } else if (nums[i-1] % 3 == 2) {
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + nums[i-1]);
                dp[i][1] = Math.max(dp[i-1][1], dp[i-1][2] + nums[i-1]);
                dp[i][2] = Math.max(dp[i-1][2], dp[i-1][0] + nums[i-1]);
            }
        }
        return dp[n][0];
    }
```

## leetcode5728
周赛的一个题，题目较长。

也是状态相互转换，分成1,2,3跑道讨论。

```java
    public int minSideJumps(int[] obstacles) {
        int n = obstacles.length;
        int[][] dp=new int[n][3];
        dp[0][0]=1;
        dp[0][2]=1;

        for(int i=1;i<n;++i){
            if(obstacles[i]==1){
                dp[i][0]=Integer.MAX_VALUE-1;
                dp[i][1]=Math.min(dp[i-1][1],dp[i-1][2]+1);
                dp[i][2]=Math.min(dp[i-1][2],dp[i-1][1]+1);
            }else if(obstacles[i]==2){
                dp[i][1]=Integer.MAX_VALUE-1;
                dp[i][0]=Math.min(dp[i-1][0],dp[i-1][2]+1);
                dp[i][2]=Math.min(dp[i-1][2],dp[i-1][0]+1);
            }else if(obstacles[i]==3){
                dp[i][2]=Integer.MAX_VALUE-1;
                dp[i][0]=Math.min(dp[i-1][0],dp[i-1][1]+1);
                dp[i][1]=Math.min(dp[i-1][1],dp[i-1][0]+1);
            }else{
                dp[i][0]=Math.min(dp[i-1][0],Math.min(dp[i-1][1],dp[i-1][2])+1);
                dp[i][1]=Math.min(dp[i-1][1],Math.min(dp[i-1][0],dp[i-1][2])+1);
                dp[i][2]=Math.min(dp[i-1][2],Math.min(dp[i-1][0],dp[i-1][1])+1);
            }
        }

        return Math.min(dp[n-1][0],Math.min(dp[n-1][1],dp[n-1][2]));
    }
```
