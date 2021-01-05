package leetcode.滑窗;

//在一条环路上有N个加油站，其中第i个加油站有汽油gas[i]升。
//你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1个加油站需要消耗汽油cost[i]升。你从其中的一个加油站出发，开始时油箱为空。
//如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。


public class leetcode134 {
    //滑窗.遍历
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int[] gas2 = new int[n * 2];
        for(int i =0; i< n; i ++){
            gas2[i] = gas[i];
            gas2[i + n] = gas[i];
        }

        int[] cost2 = new int[n * 2];
        for(int i = 0; i < n; i ++){
            cost2[i] = cost[i];
            cost2[i + n] = cost[i];
        }

        for(int i = 0; i < n ; i ++){
            int res = 0;
            for(int j = i; j < i+n; j++){
                res += gas2[j];
                res -= cost2[j];
                if(res < 0){
                    break;
                }
                if(j == i+n-1){
                    return i;
                }
            }
        }
        return -1;
    }

    //巧妙。找到最低点的后一个点作为起点，总和为正就可以
    public int canCompleteCircuit1(int[] gas, int[] cost) {
        int len = gas.length;
        int spare = 0;
        int minSpare = Integer.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < len; i++) {
            spare += gas[i] - cost[i];
            if (spare < minSpare) {
                minSpare = spare;
                minIndex = i;
            }
        }

        return spare < 0 ? -1 : (minIndex + 1) % len;
    }
}
