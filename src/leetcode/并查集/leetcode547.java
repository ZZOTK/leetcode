package leetcode.并查集;
//有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
//
//省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
//给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
//返回矩阵中 省份 的数量。

public class leetcode547 {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFind un = new UnionFind(n);
        for(int i = 0;i < n ; i ++){
            for(int j = 0; j < n ; j ++){
                if(M[i][j] == 1){
                    un.union(i,j);
                }
            }
        }
        return un.getCount();
    }

    public class UnionFind {
        private int[] id;
        private int count;

        public UnionFind(int N) {
            count = N;
            id = new int[N];
            for(int i = 0; i < N; i++) id[i] = i;
        }

        public int getCount() {
            return count;
        }

        public boolean connected(int p, int q) {
            return find(p) == find(q);
        }

        public int find(int p) {
            return id[p];
        }

        public void union(int p, int q){
            int pRoot = find(p);
            int qRoot = find(q);

            if(pRoot == qRoot) return;

            for(int i = 0; i < id.length; i++)
                if(id[i] == pRoot)  id[i] = qRoot;
            count--;
        }
    }
}
