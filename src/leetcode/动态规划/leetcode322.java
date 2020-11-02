package leetcode.动态规划;
//背包问题
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

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
