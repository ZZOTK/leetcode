# leetcode

* [二分查找](#1)
* [背包问题](#2)
* [二叉树](#3)      
* [二叉查找树](#4)             
* [单调栈](#5)       
* [双端队列Deque](#6)       
* [回溯算法（全排列与剪枝）](#7)   


<h2 id="1">二分查找</h2>
>1.普通二分查找
    
    //leetcode34
    public int binarySearch(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        //此时区间长度很重要。[left,nums.length-1],[left,nums.length)
        //[2,2]区间长度为1，[2,2)区间长度为0
        while(left<=right){
            //此时的=号是否需要很重要。
            //带等于，left=right+1才跳出
            //不带等于，left=right就跳出
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else if(nums[mid]>target){
                right=mid-1;
            }
        }
        return -1;
    }
    
>2.寻找二分查找的左边界   

比如数组int[] nums={1,2,2,3,4},我们需要找到第一个2的位置,称为用二分查找找到左侧边界。   
    
    public int binarySearcLeft(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        //搜索区间[left,right]
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<targrt){
                //搜索区域变为[mid+1,right]
                left=mid+1;
            }else if(nums[mid]>target){
                //搜索区域变为[left,mid-1]
                right=mid-1;
            }else if(nums[mid]==targrt){
                //收缩右侧边界
                right=mid-1;
            }
        }
        //检查出界情况
        if(left>=nums.length||nums[left]!=target){
            return -1;
        }
        return left;
    }
    
>3.寻找二分查找的右侧边界

    public int binarySearcLeft(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]<target){
                left=mid+1;
            }else if(nums[right]>target){
                right=mid-1;
            }else if(nums[mid]==target){
                //收缩左边界
                left=mid+1;
            }   
        }
        //检查出界情况
        if(right<0||nums[right!=target){
            return -1;
        }
        return right;
    }
   

打家劫舍问题
>1.leetcode198  
 
如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。    
dp算法，int[] dp记录偷到第n家钱最多的钱。  
dp[n]=Math.max(dp[n-2]+nums[n],dp[n-1])


>2.leetcode213

强盗依然不能抢劫相邻的房子，输入依然是一个数组，但是告诉你这些房子不是一排，而是围成了一个圈。
也就是说，现在第一间房子和最后一间房子也相当于是相邻的，不能同时抢。  
分为三个情况。同上算法，我们求[0,n-1) , (0,n-1] , (0,n-1)三个范围内的最大值。显然，（0，n-1）的情况已经被
包含。只需要求[0,n-1) , (0,n-1]这两个情况。

>3.leetcode322

树形dp。   

<h2 id="2">背包问题</h2>

>1.　01背包问题

有 N 件物品和一个容量是 V 的背包。`每件物品只能使用一次。`第 i 件物品的体积是 vi，价值是 wi。
求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。        
        
定义一个二阶矩阵dp[N+1][V+1],
这里之所以要N+1和V+1，是因为第0行表示只能选择第0个物品的时候，即没有物品的时候第0列表示背包的体积为0的时候，即不能装任何东西的时候 
dp[i][j]表示在 只能选择前i个物品，背包容量为j的情况下，背包中物品的最大价值     
对于dp[i][j]有两种情况：        
　　1.不选择当前的第i件物品/第i件物品比背包容量要大       
　　　　　　　　　　　　dp[i][j] = dp[i-1][j]       
　　2.选择当前的第i件物品（潜在要求第i件物品体积小于等于背包总容量），则能装入的物品最大价值为：        
　　　　　当前物品的价值 加上 背包剩余容量在只能选前i-1件物品的情况下的最大价值       
　　　　　　　　　　dp[i][j] = dp[i-1][j-v[i]] + w[i]       
　　　dp[i][j]在两种情况中选择比较大的情况作为当前的最优解；     
　　　即：      
　　　if(j >= v[i]):      
　　　　　　dp[i][j] = max(dp[i-1][j], dp[i-1][j-v[i]] + w[i])      
　　　else:       
　　　　　　dp[i][j] = dp[i-1][j]     

    import java.util.Scanner;
    
    public class Main{
        public static void main(String[] args) throws Exception {
            // 读入数据的代码
            Scanner reader = new Scanner(System.in);
            // 物品的数量为N
            int N = reader.nextInt();
            // 背包的容量为V
            int V = reader.nextInt();
            // 一个长度为N的数组，第i个元素表示第i个物品的体积；
            int[] v = new int[N + 1] ;
            // 一个长度为N的数组，第i个元素表示第i个物品的价值；
            int[] w = new int[N + 1] ;
    
            for (int i=1 ; i <= N ; i++){
            //读入是第一个数索引为1
                // 接下来有 N 行，每行有两个整数:v[i],w[i]，用空格隔开，分别表示第i件物品的体积和价值
                v[i] = reader.nextInt();
                w[i] = reader.nextInt();
            }
            reader.close() ;
    
            // 正式工作的代码

            int[][] dp = new int[N+1][V+1];
            dp[0][0] = 0;
            for(int i = 1; i <= N; i++){
                for(int j = 0; j <= V; j++){
                    if(j >= v[i]){
                        dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-v[i]] + w[i]);
                    }else{
                        dp[i][j] = dp[i-1][j];
                    }
                }
            }
            //压缩为一维dp数组
                int[] dp = new int[V+1];
                dp[0] = 0;
                for(int i = 1; i <= N; i++){
                    for(int j = V; j >= v[i]; j--){
                        dp[j] = Math.max(dp[j], dp[j-v[i]] + w[i]);
                    }
                }
                System.out.println(dp[V]);
                //一维的输出
          
            //二维的输出
            System.out.println(dp[N][V]);
        }
    }
   
补充：     
1.01背包装到值为A有多少种做法：leetcode494      
2.01背包能否装到值为A ： leetcode416  


>2.完全背包问题

有 N 种物品和一个容量是 V 的背包，每种物品都有无限件可用。        
第 i 种物品的体积是 vi，价值是 wi。      
求解将哪些物品装入背包，可使这些物品的总体积不超过背包容量，且总价值最大。输出最大价值。

    //模仿01背包，多维的dp。dp[i][j]记录前i个物品放入j空间的最大值。
        int[][] dp=new int[N+1][V+1];
        for(int i=1;i<=N;i++){
            for(int j=0;j<=V;j++){
                if(j>=v[i]){
                    //此时的状态方程，比较的大小
                    dp[i][j]=Math.max(dp[i][j-v[i]]+w[i],dp[i-1][j]);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
               
            }
        }
        System.out.println(dp[N][V]);
        
    //初步优化为一维数组
        int[] dp=new int[V+1];
        for(int i=1;i<=N;i++){
            for(int j = V; j >= v[i]; j --){
                //逐一找最大值，增加循环，用变量k遍历计算最大值。
                for(int k = 0; j-k* v[i] >= 0; k ++){
                    dp[j] = Math.max(dp[j] , dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        System.out.println(dp[V]);  
    
    //再优化。
        int[] dp=new int[V+1];
        for(int i=1;i<=N;i++){
            for(int j =  v[i]; j <= V;  j ++){
                dp[j] = Math.max(dp[j], dp[j - v[i]] + w[i]);
            }
        }
        System.out.println(dp[V]);

>3 多重背包问题

有 N 种物品和一个容量是 V 的背包。        
第 i 种物品最多有 si 件，每件体积是 vi，价值是 wi。        
求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。        
思路：     
　　参考完全背包问题，就是将完全背包中的数量限制改为si.       
优化：     
　　利用二进制，将背包数量分解，变为01背包问题。
         
        //直接做法。
        int[] dp = new int[V+1];
        for(int i = 1; i <= N; i ++){
            for(int j = V; j >= v[i]; j --){
            //此处加入一个限制条件k<=s[i]即可
            //k可以从1开始，因为0就是dp[j]
                for(int k = 1; j - k * v[i] >= 0 && k <= s[i]; k ++ ){
                    dp[j] = Math.max(dp[j], dp[j - k * v[i]] + k * w[i]);
                }
            }
        }
        System.out.println(dp[V]);       
        
        //优化的多重背包问题。        
        //将数量s分解。例如有一个体积为v，值为w，数量为7的。则分解为[v,w],[2v,2w],[4v,4w]三个物体            
        //将所有物体数量都分解。则化解为了01背包问题。       
        int maxN = 200002;
        int[] v = new int[maxN];
        int[] w = new int[maxN];
        Scanner jin = new Scanner (System.in);
        void run(){
            int n = jin.nextInt();
            int m = jin.nextInt();
            int p = 1;
            for (int i = 1; i <= n ; i++){
                int V = jin.nextInt();
                int W = jin.nextInt();
                int S = jin.nextInt();
                int k = 1;
                while (S > k){
                    v[p] = V*k;
                    w[p] = W*k;
                    S -= k;
                    k *= 2;
                    p++;
                }
                if (S > 0){
                    v[p] = V*S;
                    w[p] = W*S;
                    p ++;
                }
            }
            //到此为止，原来所有的物体分解为共p个独立的物体，每个限制使用1次，化解为01背包问题。       
            //共有p个物体，放入总体积m的背包。体积为v，价值为w
            int res = dp(p, m);
            System.out.println(res);
        }
        //同01背包问题。
        int dp(int n, int m){
            int[] f = new int[maxN];
            for (int i= 1; i <= n ; i ++){
                for (int j = m ; j>= v[i] ; j--){
                    f[j] = Math.max(f[j], f[j - v[i]] + w[i]);
                }
            }
            return f[m];
        }
        public static void main(String[] args) {new solution().run();}
        
        //利用优先队列优化多重背包问题
        （待学习）
        
>4.混合背包问题       
        
有 N 种物品和一个容量是 V 的背包。
物品一共有三类：        
第一类物品只能用1次（01背包）；       
第二类物品可以用无限次（完全背包）；      
第三类物品最多只能用 si 次（多重背包）；      
每种体积是 vi，价值是 wi。        
si=−1 表示第 i 种物品只能用1次；       
si=0 表示第 i 种物品可以用无限次；       
si>0 表示第 i 种物品可以使用 si 次；        
求解将哪些物品装入背包，可使物品体积总和不超过背包容量，且价值总和最大。

思路：     
分类讨论。01背包可以看作只使用一次的多重背包问题。
 
        public static void main(String[] args){
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt(); // 物品个数
            int V = sc.nextInt(); // 背包总容量
            int[] dp = new int[V + 1];
            for(int i = 0; i < N; i++){
                int v = sc.nextInt(); // 体积
                int w = sc.nextInt(); // 价值
                int s = sc.nextInt(); // 数量
                if(s == 0){
                    // 完全背包问题
                    for(int j = v; j <= V; j++){
                        dp[j] = Math.max(dp[j], dp[j - v] + w);
                    }
                }else{
                    // 多重背包问题，01背包是多重背包的特例，可以一并处理
                    s = Math.abs(s);
                    for(int j = 1; s >= j; s -= j, j *= 2){
                        for(int k = V; k >= j * v; k--){
                            dp[k] = Math.max(dp[k], dp[k - j * v] + j * w);
                        }
                    }
                    if(s > 0){
                        for(int j = V; j >= s * v; j--){
                            dp[j] = Math.max(dp[j], dp[j - s * v] + s * w);
                        }
                    }
                }
            }
            System.out.println(dp[V]);
        }
        
>5.二维背包问题

有 N 件物品和一个容量是 V 的背包，背包能承受的最大重量是 M。
每件物品只能用一次。体积是 vi，重量是 mi，价值是 wi。
求解将哪些物品装入背包，可使物品总体积不超过背包容量，总重量不超过背包可承受的最大重量，且价值总和最大。
输出最大价值。     
思路: 与01背包一样，二维dp扩充为三维dp

        public int two_dimension_knapsack_problem_1(int N, int V, int M, int[] v, int[] m, int[] w){
            int[][][] dp = new int[N+1][V+1][M+1];
            for(int i = 1; i <= N; i++){
                for(int j = 1; j <= V; j++){
                    for(int k = 1; k <= M; k++){
                        if(j < v[i] || k < m[i]){
                            // 客观条件限制，不能选择当前物品N
                            dp[i][j][k] = dp[i-1][j][k];
                        }else {
                        //基本相同，多个限制
                            dp[i][j][k] = Math.max(dp[i-1][j][k], dp[i-1][j-v[i]][k-m[i]] + w[i]);
                        }
                    }
                }
            }
            return dp[N][V][M];

<h2 id="3">二叉树</h2>
**二叉树的主要思路就是递归。我们需要重点考虑每个节点该做什么，考虑递归.left和.right在操作之前还是之后
。然后写出函数。**
>1.树到数组     

这类问题，最直接的就是树的前序，中序，后序，层序遍历(leetcode102)。        
将树拉直(leetcode114))        
树序列化到数组再反序列化为树（leetcode297）（前序后序的序列化和复原）


>2.树本身的变形和访问

树的反转（leetcode226），树节点指向（leetcode116）


>3.数组到树

按照一定规律将数组转为树(leetcode654)       
根据前序、中序、层序、后序中的两个数组还原(**leetcode105**,**leetcode106**)     
都使用了辅助函数bulid。重点：       
1.利用左右指针大小比较来进行return。      
2.注意left和right中范围的选取。（使用distance记录）;

>4.树加入其他算法。

1.动态规划      
2.DFS，BFS（一般配合哈希表实现记忆化，完成剪枝。

<h2 id="4">二叉查找树</h2>
定义: 
    
   1、对于 BST 的每一个节点node，左子树节点的值都比node的值要小，右子树节点的值都比node的值大。      
   2、对于 BST 的每一个节点node，它的左侧子树和右侧子树都是 BST。       
   3、一个重要的性质：BST 的中序遍历结果是有序的（升序）。       
   
 例题：        
 1.leetcode230:查找第k小的元素。中序遍历，第k个元素。     
 2.leetcode538:BST转换为累加树。后序遍历，节点的累加值赋给节点。       
 
进一步操作： 
    
**1.判断BST的合法性**   
对于每一个节点root，代码值检查了它的左右孩子节点是否符合左小右大的原则；但是根据 BST 的定义，
root的整个左子树都要小于root.val，整个右子树都要大于root.val。

    boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    
    /* 限定以 root 为根的子树节点必须满足 max.val > root.val > min.val */
    
    boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
        
        if (root == null) return true;
        
        if (min != null && root.val <= min.val) return false;
        if (max != null && root.val >= max.val) return false;
       
        return isValidBST(root.left, min, root) 
            && isValidBST(root.right, root, max);
    }     
        
**2.BST的增删改查**

BST的查找：左小右大，依次向下。      

    void BST(TreeNode root, int target) {
        if (root.val == target)
            // 找到目标，做点什么
        if (root.val < target) 
            BST(root.right, target);
        if (root.val > target)
            BST(root.left, target);
    }

BST中插入一个数：根据左小右大找到空位置，插入        

    TreeNode insertIntoBST(TreeNode root, int val) {
        // 找到空位置插入新节点
        if (root == null) return new TreeNode(val);
        // if (root.val == val)
        //     BST 中一般不会插入已存在元素
        if (root.val < val) 
            root.right = insertIntoBST(root.right, val);
        if (root.val > val) 
            root.left = insertIntoBST(root.left, val);
        return root;
    }

BST中删除一个数：      
找到，删除。再修改BST结构，使BST满足他的性质。     
如果删除的节点不含有两个子节点（1或0个），则直接将子节点放到他的位置。     
如果有两个子节点，则将左子树中的最大值，或者右子树中的最小值放到他的位置。 

    TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            // 这两个 if 把情况 1 和 2 都正确处理了
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;
            // 处理情况 3
            //找到右子树的最左边即最小值
            TreeNode minNode = getMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        }
        return root;
    }
    
    TreeNode getMin(TreeNode node) {
        // BST 最左边的就是最小的
        while (node.left != null) node = node.left;
        return node;
    } 

<h2 id="5">单调栈</h2>
单调栈一般解决问题：
1.数组去除k个元素使（条件）   
    一般这种问题，使剩下的最大或最小。就是使删除k个元素之后，从前往后从小到大。  
2.后一个大或小的元素         
    用单调栈找到每个元素的下一个大的或是小的元素，再用哈希表记录。


```java
public class leetcode402 {
    public String removeKdigits(String num, int k) {
        //特殊情况全部删除
        if (num.length() == k) {
            return "0";
        }
        char[] s = num.toCharArray();
        Stack<Character> stack = new Stack<>();
        //遍历数组
        for (Character i : s) {
            //移除元素的情况，k--
            while (!stack.isEmpty() && i < stack.peek() && k > 0) {
                stack.pop();
                k--;
            }
            //栈为空，且当前位为0时，我们不需要将其入栈
            if (stack.isEmpty() && i == '0') {
                continue;
            }
            stack.push(i);
        }
        while (k > 0) {
            stack.pop();
            k--;
        }
        //这个是最后栈为空时，删除一位，比如我们的10，删除一位为0，按上面逻辑我们会返回""，所以我们让其返回"0"
        if (stack.isEmpty()) {
            return "0";
        }
        //反转并返回字符串
        StringBuilder str = new StringBuilder();
        while (!stack.isEmpty()) {
            str.append(stack.pop());
        }
        return str.reverse().toString();
    }
}
```

<h2 id="6">双端队列Deque</h2>
双端队列可以addFirst,也可以addlast。
add(),offer()默认是addLast(),即加在尾端;        
push()默认是addFirst();        
pop()和poll()默认都是pollFirst();        
pollLast()。     

leetcode103 & offer_32。二叉树的锯齿形遍历。
```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans= new LinkedList<>();
        Deque<TreeNode> path = new LinkedList<>();
        int count = 0;
        if(root == null){
            return ans;
        }
        path.add(root);
        while(!path.isEmpty()){
            LinkedList<Integer> level = new LinkedList<>();
            int n = path.size();
            for(int i = 0; i< n; i ++){
                TreeNode temp = path.poll();
                if(count%2 == 0){
                    level.addLast(temp.val);
                }else{
                    //利用addFirst实现从右往左
                    level.addFirst(temp.val);
                }
                if(temp.left != null){
                    path.addLast(temp.left);
                }
                if(temp.right != null){
                    path.addLast(temp.right);
                }
            }
            count ++;
            ans.add(level);
        }
        return ans;
        }
    }
```
<h2 id="7">回溯算法（全排列与剪枝）</h2>

> 1. 元素不重复      
> 例如给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集。(leetcode78)
> 没有重复元素就意味着在树中，只需要 **一层中不出现之前层已有的数**。这样树从根节点到叶子节点，
> 无论是哪条路径，都不会有重复的元素。
> 常用的解题办法：

```
    //leetcode78
    public void backtrack(int[] nums,Deque<Integer> path,List<List<Integer>> ans,int begin,int i ) {
        if (path.size() == i) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = begin; j < nums.length; j++) {
            //放入nums[j]
            path.addLast(nums[j]);
            //注意理解j+1. 
            //下次遍历从j + 1 处开始，就可以做到不出现之前层有的树
            backtrack(nums, path, ans, j + 1, i);
            path.removeLast();
        }
    }
    
    //leetcode39
    private void dfs(int[] candidates, int begin, int len, int target, Stack<Integer> path, List<List<Integer>> res) {
        // target 为负数和 0 的时候不再产生新的孩子结点
        if (target < 0) {
            return;
        }
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        //回溯向后
        //重点理解这里从 begin 开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.push(candidates[i]);
            
            //注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是 i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);
            
            path.pop();
        }
    }
```


> 2. 元素有重复      
> 比如，一个含有重复数字的序列，返回所有不重复的全排列。（leetcode47）
> 这种问题就意味着 ： **树的一层中不包含重复元素**
> 如何做到一层中不包含重复元素，最直接的办法，在回溯算法中，一层加一个set，用set来达到去重的效果。(leetcode491)
> 办法二，**先排序**，如果nums[i] == nums[i - 1],剪枝跳过。(leetcode40)

```java
//leetcode47
//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
public class leetcode47 {
    List<List<Integer>> ans;
    Deque<Integer> path;

    public List<List<Integer>> permuteUnique(int[] nums) {
        ans = new LinkedList<>();
        path = new LinkedList<>();
        int n = nums.length;
        boolean[] vis = new boolean[n];
        //有重复数字的要先sort
        Arrays.sort(nums);
        backtrack(nums, vis, 0);
        return ans;
    }

    public void backtrack(int[] nums, boolean[] vis, int len) {
        if (len == nums.length) {
            ans.add(new LinkedList(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (vis[i]) {
                continue;
            }
            //这个!vis[i -1]理解。前一个相同并且没选过，剪枝
            //就是对于相同的元素，只有第一个能作为根节点。
            //相同元素的相对顺序其实是不变的。
            //比如{1，1，2}，那么第一层循环将只有1，2
            if (i > 0 && nums[i] == nums[i - 1] && !vis[i - 1]) {
                continue;
            }
            path.addFirst(nums[i]);
            vis[i] = true;
            backtrack(nums, vis, len + 1);
            vis[i] = false;
            path.pollFirst();
        }
    }
}
```