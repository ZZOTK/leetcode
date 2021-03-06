> 位运算的是二进制每一位的逻辑运算。     
> 常用的二进制运算：左移，右移（等价于除以二）        
> 例题：leetcode461

* 计算二进制中1的个数的方法： x & (x - 1), 直到x为0.操作多少次则有多少个1

```java
//两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
//给出两个整数 x 和 y，计算它们之间的汉明距离。

//取x XOR y ，即只有xy不同时才为1。再统计有多少个1
public class leetcode461 {
    public int hammingDistance(int x, int y) {
        //x^y 取xor
        int xor = x ^ y;
        int count = 0;
        while(xor != 0){
            count++;
            //计算二进制中有多少个1的位算法，不断x&(x-1)
            xor = xor&(xor - 1);
        }
        return count;
    }
}
```

## 补充阅读：JDK中是怎么统计1的个数的呢？

最多执行5步，等于说复杂度从N到了LogN！
```java
    public static int bitCount(int i) {
        // HD, Figure 5-2
        //等价于 i = (i & 0x55555555) + ((i>>>1) & 0x55555555)
        i = i - ((i >>> 1) & 0x55555555);
        i = (i & 0x33333333) + ((i >>> 2) & 0x33333333);
        i = (i + (i >>> 4)) & 0x0f0f0f0f;
        i = i + (i >>> 8);
        i = i + (i >>> 16);
        return i & 0x3f;
    }
```


