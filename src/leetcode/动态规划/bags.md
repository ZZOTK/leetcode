背包问题是经典的动态规划问题。有知名的文章《背包九讲》。在这儿就说说常见的背包问题。

## 01背包问题

> 有 N 件物品和一个容量是 V 的背包。**每件物品只能使用一次**。第 i 件物品的体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。

01背包问题，就是说的每个物品只能使用一次。

思路：
定义一个二阶矩阵dp[N+1][V+1],
`dp[i][j]表示在 只能选择前i个物品，背包容量为j的情况下，背包中物品的最大价值 `。   

已知dp[i-1][j],要求dp[i][j],则需要状态转移方程。状态转移方程的推导：    
对于dp[i][j]有两种情况：
1. 不选择当前的第i件物品 || 第i件物品比背包容量要大
    * 显然，此时 dp[i][j] = dp[i-1][j] 
2. 有可能选择当前的第i件物品。
    * 潜在要求第i件物品体积小于等于背包总容量
    * 则能装入的物品最大价值为：当前物品的价值 加上 背包剩余容量在只能选前i-1件物品的情况下的最大价值。
    * 此时，状态转移方程表示为  dp[i][j] = max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])  
    
java 代码实现如下
```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // 读入数据的代码
        Scanner reader = new Scanner(System.in);
        // 物品的数量为N
        int N = reader.nextInt();
        // 背包的容量为V
        int V = reader.nextInt();
        // 一个长度为N的数组，第i个元素表示第i个物品的体积；
        int[] v = new int[N + 1];
        // 一个长度为N的数组，第i个元素表示第i个物品的价值；
        int[] w = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            //读入是第一个数索引为1
            // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
            v[i] = reader.nextInt();
            w[i] = reader.nextInt();
        }
        reader.close();

        // 二维dp

        int[][] dp = new int[N + 1][V + 1];
        dp[0][0] = 0;
        //此处i从1开始是因为上面v[i]和w[i]是从1开始存的
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= V; j++) {
                if (j >= v[i]) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - v[i]] + w[i]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
    }
}
```
那么，动态规划的空间可以压缩为1维数组吗？
优化： 
* 此时，dp[v]就表示在v大小的背包空间下，可以放入的最大的物品价值 。
* 当我们已知第i-1个物品放入后，v的最大值，那么加入第i个物品时，最大值要么是放入第i个物品，要么是原来的值。
* 此时的状态转移方程为 dp[v] = Math.max( dp[v - v[i] ) + w[i] , dp[v])

参考代码：
```java
        int[] dp = new int[V+1];
        dp[0] = 0;
        for(int i = 1; i <= N; i++){
            for(int j = V; j >= v[i]; j--){
                dp[j] = Math.max(dp[j], dp[j-v[i]] + w[i]);
            }
        }
```

## 完全背包问题
> 有 N 种物品和一个容量是 V 的背包，**每种物品都有无限件可用。** 第 i 种物品的体积是 vi，价值是 wi。 求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。\\

完全背包与01背包区别就是每件物品可以使用无数次。

思路： 
可以模仿01背包，dp[i][j]记录前i个物品放入j空间的最大值。
1. 不选择当前的第i件物品 || 第i件物品比背包容量要大
    * 显然，此时 dp[i][j] = dp[i-1][j]
2. 有可能选择当前的第i件物品。
    * 潜在要求第i件物品体积小于等于背包总容量。
    * 由于可以重复使用，能装入的物品最大价值为：当前物品的价值 加上 背包剩余容量在只能选**前i件**物品的情况下的最大价值。
    * 状态转移方程变为 dp[i][j]=Math.max(dp[i][j-v[i]]+w[i],dp[i-1][j])
    
参考代码：

```java
//数据读取和上一题01背包相同
    //模仿01背包，多维的dp。dp[i][j]记录前i个物品放入j空间的最大值。
        int[][] dp=new int[N+1][V+1];
        for(int i=1;i<=N;i++){
            for(int j=0;j<=V;j++){
                if(j>=v[i]){
                    //此时的状态方程，比较的大小
                    //注意i-1和i的状态区别
                    dp[i][j]=Math.max(dp[i][j-v[i]]+w[i],dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
               
            }
        }
        System.out.println(dp[N][V]);
```
压缩为一维空间同样也是可以的。
```java
    //初步优化为一维数组
    //dp[v]表示在v空间下装入的最大价值
        int[] dp=new int[V+1];
        for(int i=1;i<=N;i++){
            for(int j = V; j >= v[i]; j --){
            	//第i个物品可以使用0-k个，和不使用第i个物品的最大值比较，找到较大的一个
                //逐一找最大值，增加循环，用变量k遍历计算最大值。
                for(int k = 0; j-k* v[i] >= 0; k ++){
                    dp[j] = Math.max(dp[j] , dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        System.out.println(dp[V]);  
```

当然，还可以继续优化，不需要使用for循环去判断使用0-k个
```java
    //再优化。
        int[] dp=new int[V+1];
        for(int i=1;i<=N;i++){
        //和01背包相比在于01背包从后向前遍历，由于使用到之前的状态，从后向前时前面的状态为0，确保了一个物品只使用了一次。
        //完全背包使用从前向后遍历，前面的状态先遍历。此时后面的状态再计算时，使第i个物品重复使用。
            for(int j =  v[i]; j <= V;  j ++){
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);
```
