## leetcode347
> 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。

统计数组出现的频率我们可以使用哈希表。

如果只是返回数组前k大的元素，我们可以直接sort。但是对于哈希表中的结果，我们无法sort。

此时，就可以借用优先队列。使用哈希表统计出现频率，再将每个元素依次加入优先队列，将出现次数作为标准进行排序。
* 我们可以使顶部为最大元素，那么就要全部入队列，再poll出k个
* 依然使顶部为最小，维持队列最大容量为k。有比顶部元素大的再入队列。

显然，方法二更加节省内存和时间。

```java
public class leetcode347 {
    public int[] topKFrequent(int[] nums, int k) {
        //哈希表存数据。key为值，value为出现的次数
        Map<Integer,Integer> ma=new HashMap<>();
        for(int num:nums){
            int count = ma.getOrDefault( num,0)+1;
            ma.put(num, count);
        }

        //优先队列进行排序。comparator比较器实现比较。
        //lamda表达式写法
        //PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> ma.get(b)-ma.get(a) );
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return ma.get(o1) - ma.get(o2);
            }
        });
        //依次加入队列
        for(int key : ma.keySet()){
            //最多加入k个元素
            if (pq.size() < k){
                pq.add(key);
            }
            //如果新key的value大于之前最小的value，则一出顶，再入队
            else if(ma.get(key) > ma.get(pq.peek())){
                pq.remove();
                pq.add(key);
            }
        }

        int[] res = new int[k];
        int i=0;
        while (!pq.isEmpty()){
            res[i ++ ]=pq.remove();
        }
        return res;
    }
}
```

leetcode 215

找到第K大的元素

方法1，优先队列。构建一个k大小的小根堆
```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums) {
            if(pq.size() < k){
                pq.add(num);
            }else{
                if(num > pq.peek()){
                    pq.remove();
                    pq.add(num);
                }
            }
        }
        return pq.peek();
    }
}
```

方法2，利用快排的性质来做。快排选择基准之后，这个基准的左边都比他小，右边都比他大。如果基准的下标正好满足是第k大，则提前结束快排的流程。
直接返回这个数字就好了

```java
public class leetcode215 {
    public int findKthLargest(int[] nums, int k) {
        int n = nums.length;
        int l = 0;
        int target = n - k;
        int r = n - 1;
        while (true) {
            // 获取基准数的下标
            int piv = partition(nums, l, r);
            if (piv == target) {
                return nums[piv];
            } else if (piv > target) {
                r = piv - 1;
            } else {
                l = piv + 1;
            }
        }
    }


    //获取下标的两种写法
    public int partition(int[] nums, int l, int r) {
        int pivot = l + (r - l) / 2;
        // 将选定的数字换到最左边
        swap(nums, l, pivot);
        int tag = nums[pivot];
        int le = l + 1;
        int re = r;
        while (true) {
            while (le <= re && nums[le] < tag) {
                le++;
            }
            // 此时re最差是在正好=tag值的最右一个位置
            while (le <= re && nums[re] > tag) {
                re--;
            }
            if (le >= re) {
                break;
            }
            swap(nums, le, re);
            le++;
            re--;
        }
        // 将选定的数字换到正确的位置
        swap(nums, l, re);
        return re;
    }

    public int partition(int[] nums, int l, int r) {
        int pivot = l + (r - l) / 2;
        int target = nums[pivot];
        // 先把选定的数字换到最左边
        swap(nums, l, pivot);
        int index = l + 1;
        for (int i = index; i <= r; i++) {
            if (nums[i] < target) {
                swap(nums, i, index);
                index++;
            }
        }
        //交换完后将这个数字换到合理的位置
        swap(nums, l, index - 1);
        return index - 1;
    }

    public void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
```
