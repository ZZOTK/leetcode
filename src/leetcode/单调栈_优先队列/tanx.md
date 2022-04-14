leetcode871

```java
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        // 处理没有中间站情况
        if(stations.length==0) return startFuel>=target?0:-1;
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((i,j)->(j-i));
        int res=0;
        for(int i=0;i!=stations.length;i++) {
            startFuel-=stations[i][0]; // 扣除从出发点到当前的油费
            startFuel+=(i>0?stations[i-1][0]:0); // 重复的路程加回去
            if(startFuel<0){ // 当前不够,从已经过的站点取油
                while(!heap.isEmpty()&&startFuel<0) {
                    startFuel+=heap.poll(); res++;
                }
                if(heap.isEmpty()&&startFuel<0) return -1;//不够冲
            }
            heap.add(stations[i][1]); // 压入当前站的冲油量
        }
        startFuel-=(target - stations[stations.length-1][0]); // 扣除最后一段路的油费
        while(!heap.isEmpty()&&startFuel<0) {
            startFuel+=heap.poll();res++;
        }
        return startFuel>=0?res:-1;
    }
```