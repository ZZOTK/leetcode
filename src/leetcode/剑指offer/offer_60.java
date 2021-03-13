package leetcode.剑指offer;

//把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
//你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。

import java.util.Arrays;

public class offer_60 {
    public double[] dicesProbability(int n) {
        if (n <= 0) {
            return new double[0];
        }
        // dp[i][j]表示：当有i个骰子时，掷出和为j的几率
        double[][] dp = new double[n + 1][6 * n + 1];

        for (int i = 1; i <= 6; i++) {
            dp[1][i] = (double) 1 / 6;
        }

        for (int i = 2; i <= n; i++) {
            int curMaxValue = 6 * i;    // 当前最大值 = 6 * 骰子个数
            for (int j = i; j <= curMaxValue; j++) {
                /*
                    我们可以这样推导：有i个骰子，掷出和为j的可能性 == (有1个骰子，掷出和为1~6的可能性) *= (有i-1个骰子，掷出和为j-(1~6)的可能性)
                    根据推导，我们能够知道：
                        dp[i][j] += dp[1][k] * dp[i - 1][j - k];
                        k为 合理情况下 的 1～6
                */
                for (int k = 1; k <= 6; k++) {
                    if (j - k <= 0) {   // k不能比j大
                        continue;
                    }
                    dp[i][j] += dp[1][k] * dp[i - 1][j - k];
                }
            }
        }

        // 将 计算结果 录入 结果数组
        double[] result = new double[5 * n + 1];
        int value = n;
        for (int i = 0; i < result.length; i++) {
            result[i] = dp[n][value++];
        }

        return result;
    }
}
