## 指定查询字段
SELECT (字段) FROM (表)

--查询字段并取别名      
SELECT `student` AS 学号，`studentName` AS 学生姓名 FROM student       

--函数Concat(a,b)     
SELECT CONCAT(`姓名:`,StudentName) AS 新名字 FROM student

--去重 distinct       
SELECT DISTINCT `studentno` FROM result     

--学员考试成绩加一分输出       
SELECT `studentno`,`studentresult` + 1 AS `提分后` FROM result         

## where 条件字句   

--查询成绩在95到100之间的        
SELECT `studentresult` FROM result
WHERE studentresult>=95 AND(&&) studentresult<=100      

--查询除了1000号学术之外的成绩
SELECT studentno,`studentresult` FROM result
WHERE NOT studentno = 1000

## 模糊查询
模糊查询本质是比较运算符。
BETWEEN :  a between b and c  a在b和c之间则为真。       
LIKE： a like b  SQL匹配。如果a匹配b则为真。        
IN： a in(a1,a2,.....)  如果a在(a1,a2,...)中，则为真。        

--查询姓张的学生       
--LIKE结合 %(表示0到任意个字符) _(表示一个字符)
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentname` LIKE '张%'

--IN(一个或多个具体的值)的使用
--查询1001，1002，1003三位学生
SELECT `studentno`,`studentname` FROM `student`
WHERE `studentn0` IN (1001,1002,1003)

## 联表查询
inner join : 如果表中至少有一个匹配，就返回行
left join : 会从左表中返回所有的值，即使右表中没有匹配
right join : 会从游标中返回所有的值，即使左表中没有匹配

## 自连接

## 分页和排序
分页（limit） : limit + 起始 + pagesize     
排序（order by） :  ASC,升序   DESC降序

## 子查询
where(select * 条件)

## 事务
要么都成功，要么都失败（转账）     
将一组sql放在一批中执行       
事务原则： ACID原则  **原子性，一致性，隔离性，持久性** （脏读，幻读....）       
原子性：一起成功或失败，不能只发生一个     
一致性：操作前与操作后状态一致     
持久性：事务结束后的数据不会随着外界的原因导致数据丢失     
隔离性：多个用户同时操作，排除其他事务对本次事务的影响     
脏读：一个事务读取了另一个事务未提交的数据       
幻读：一个事务内读取了别的事务插入的数据，导致前后不一致        

## 数据库的三大范式
1、数据库表的每一列都是不可分割的原子数据项。（原子性）        
2、前提：满足第一范式 确保数据库表中的每一列都和主键相关，
而不能只与主键的一部分相关（一张表只做一件事情）。       
3、前提：满足第一和第二范式 表中的每一列都与主键直接相关，而不能间接相关。

