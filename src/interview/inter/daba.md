一个设计运动员打靶，最高10环，每次得分都在0-10之间，开10枪，得90分的可能性有多少种。

思路一：递归。
f(i,j)表示i枪得了j分
```java
    public int f(int time,int score) {
        int all = 0;
        if (score / time > 10)
            return 0;
        if (time == 1)
            return 1;
        for (int i = 0; i <= 10; i++) {
            int tmp = f(1, i) * f(time - 1, score - i);
            if (tmp >= 1)
                all += tmp;
        }
        return all;
    }
```

思路二：数学

直接可得：C19 9

