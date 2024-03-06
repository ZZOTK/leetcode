# leetcode 3

给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串的长度。


思路：从前向后遍历。以当前位置结尾的最长的子串长度， 应该是当前位置 - 最后一个不重复字符的位置。用map记录某个字符上个出现的位置。


例如 axcade,
* 起始位置在第一个a，向后遍历，长度逐渐增加
* 当遇到第二个a时，map获取到第一个a在0，所以起始位置变为x
* 继续向后遍历

```java
public class leetcde3 {
    public int lengthOfLongestSubstring(String s) {
        //哈希表，存储元素和位置
        Map<Character,Integer> ma=new HashMap<>();
        //左指针
        int left=0;
        int max=0;
        //i为右指针
        for (int i=0;i<s.length();i++){
            if (ma.containsKey(s.charAt(i))){
                //移动左指针，即滑窗。两个里面取最大值
                left=Math.max(ma.get(s.charAt(i))+1,left);
            }
            //存值，在if判断之后
            ma.put(s.charAt(i),i);
            max=Math.max(max,i-left+1);
        }
        return max;
    }
}
```