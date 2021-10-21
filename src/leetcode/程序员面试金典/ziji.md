 ## leetcode78

> 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
> 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。

一共n个不同元素，每个元素是否出现用0-1表示，则0-2^n-1可以表示所有的情况。

某位上出现1。这说明nums某位在解集中
```java
class Solution {
    List<List<Integer>> ans= new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        int len = 1 << n;
        for(int i = 0; i < len ; i ++){
            List<Integer> path = new LinkedList<>();
            for(int j = 0 ; j < n ;j ++){
                if(((i >> j) & 1) == 1){
                    path.add(nums[j]);
                }
            }
            ans.add(path);
        }
        return ans;
    }
}
```

## leetcode90
> 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
> 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。

利用sort去重，再每次取0-n位的子集
```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Deque<Integer> path = new ArrayDeque<>();
        List<List<Integer>> ans=new ArrayList<>();
        Arrays.sort(nums);
        if (nums.length==0){
            return ans;
        }
        for (int i=1;i<=nums.length;i++){
            backtrack(nums,path,ans,0,i);
        }
        ans.add(new ArrayList<>());
        return ans;

    }
    public void backtrack(int[] nums,Deque<Integer> path,List<List<Integer>> ans,int begin,int i ) {
        if (path.size() == i) {
            ans.add(new ArrayList<>(path));
            return;
        }
        for (int j = begin; j < nums.length; j++) {

            if(j > begin && nums[j] == nums[j -1]){
                continue;
            }
            
            path.addLast(nums[j]);
            //注意理解j+1. 和leetcode39有相似的地方。
            backtrack(nums, path, ans, j + 1, i);
            path.removeLast();
        }
    }

}
```


