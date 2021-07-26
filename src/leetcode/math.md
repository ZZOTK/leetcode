# JAVA解决数学问题

## 判断是不是质数
判断数j是不是质数：从0开始尝试到sqrt（j）

```java
private static boolean check(int x) {
        if (x <= 1) return false;
        for (int i = 2; i <= x / i; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
```

## 分解质因数

给定正整数a，将数分解质因数，并按照质因数从小到大的顺序输出每个质因数的底数和指数。


```java
    private static void decompose(int x) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 2; i <= x / i; i++) {
            int mul = 0;
            if (x % i == 0) {
                while (x % i == 0) {
                    mul++;
                    x /= i;
                }
                map.put(x,mul);
            }
        }
        //x本身就是质数
        if (x > 1) {
            map.put(x,1);
        }
    }
```

## 筛质数
给定一个正整数n，请你求出1~n中质数的个数。

埃氏筛
```java
    public int sushushai(int n){
        int count = 0;
        boolean[] vis = new boolean[n+1];
        for(int i = 2;i <= n; i++){
            if(!vis[i]) {
                count++;
                for (int j = i + i; j <= n; j += i) {
                    vis[j] = true;
                }
            }
        }
        return count;
    }
```

## 快速幂
快速求n的m次方

```java
private static int pow(int n, int m) 
    int res = 1;                     
    int base = n;                    
    while(m != 0) {                  
        if ((m&1) == 1) {            
            res = res * base;        
        }                            
        base = base * base;          
        m = m >> 1;                  
    }                                
    return res;                      
}
```

## 求一个数的所有约数
给定正整数a，对于整数a,请按照从小到大的顺序输出它的所有约数。

试除法

```java
    private static void approximate(int x) {
        List<Integer> list = new LinkedList<>();
        for (int i = 1; i <= x / i; i++) {
            if (x % i == 0) {
                list.add(i);
                if (i != x / i) {
                    list.add(x / i);
                }
            }
        }
    }
```

## 约数个数
给定n个正整数，请你输出这些数的乘积的约数个数，答案对10^9+7取模。

依次质因数分解，再讲所有（质因数个数+1）乘起来。

## 高精度乘法
```java
//String乘法
public class solution {
    public static String multi(String num1, String num2) {
        int[] nums1 = new int[num1.length()];
        int[] nums2 = new int[num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            nums1[i] = num1.charAt(i) - '0';
        }
        for (int i = 0; i < num2.length(); i++) {
            nums2[i] = num2.charAt(i) - '0';
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int jw = 0;
            for (int j = num2.length() - 1; j >= 0; j--) {
                int temp = res[i + j + 1] + nums1[i] * nums2[j] + jw;
                res[i + j + 1] = temp % 10;
                jw = temp / 10;
            }
            res[i] += jw;
        }
        StringBuffer sb = new StringBuffer();
        //去掉首位0
        int index = 0;
        while (index < res.length && res[index] == 0) {
            index++;
        }
        for (int i = index; i < res.length; i++) {
            sb.append(res[i]);
        }
        if (sb.length() == 0) {
            return "0";
        }
        return sb.toString();
    }
}
```

##  String加法

