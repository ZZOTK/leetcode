## leetcode180
```
编写一个 SQL 查询，查找所有至少连续出现三次的数字。
返回的结果表中的数据可以按 任意顺序 排列。

Logs 表：
+----+-----+
| Id | Num |
+----+-----+
| 1  | 1   |
| 2  | 1   |
| 3  | 1   |
| 4  | 2   |
| 5  | 1   |
| 6  | 2   |
| 7  | 2   |
+----+-----+

Result 表：
+-----------------+
| ConsecutiveNums |
+-----------------+
| 1               |
+-----------------+
1 是唯一连续出现至少三次的数字。

```
```sql
-- 取别名加暴力解法。连续出现三次则必然ID连续
SELECT DISTINCT
    l1.Num AS ConsecutiveNums
FROM
    Logs l1,
    Logs l2,
    Logs l3
WHERE
    l1.Id = l2.Id - 1
    AND l2.Id = l3.Id - 1
    AND l1.Num = l2.Num
    AND l2.Num = l3.Num
```

## leetcode177

```
编写一个 SQL 查询，获取 Employee 表中第n高的薪水（Salary）。

+----+--------+
| Id | Salary |
+----+--------+
| 1  | 100    |
| 2  | 200    |
| 3  | 300    |
+----+--------+
例如上述Employee表，n = 2时，应返回第二高的薪水200。如果不存在第n高的薪水，那么查询应返回null。

+------------------------+
| getNthHighestSalary(2) |
+------------------------+
| 200                    |
+------------------------+

```

* 需要去重，但是group by有去重，就不需要distinct
* DESC 降序排列
* 定义变量，先declare声明，再set赋值
```sql
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    declare m int;
    set m = N - 1;
  RETURN (
      SELECT 
            salary
      FROM 
            employee
      GROUP BY 
            salary
      ORDER BY 
            salary DESC
      LIMIT m, 1
  );
END
```




