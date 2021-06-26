## 非关系型数据库和关系型数据库区别，优势比较
**关系型数据库就是表与表之间有关系**
非关系型数据库称为 NoSQL，也就是 Not Only SQL，不仅仅是 SQL。非关系型数据库不需要写一些复杂的 SQL 语句，其内部存储方式是以 key-value 的形式存在可以把它想象成电话本的形式，每个人名（key）对应电话（value）。 常见的非关系型数据库主要有 Hbase、Redis、MongoDB 等。     
非关系型数据库的优势：
* 非关系型数据库不需要经过 SQL 的重重解析，所以性能很高；      
* 非关系型数据库的可扩展性比较强，数据之间没有耦合性，遇见需要新加字段的需求，就直接增加一个 key-value 键值对即可。       

关系型数据库以表格的形式存在，以行和列的形式存取数据，关系型数据库这一系列的行和列被称为表，无数张表组成了数据库，常见的关系型数据库有 Oracle、DB2、Microsoft SQL Server、MySQL等。
关系型数据库的优势：
* 关系型数据库能够支持复杂的 SQL 查询，能够体现出数据之间、表之间的关联关系；
* 关系型数据库也支持事务，便于提交或者回滚。

## char和varchar的区别
char是固定长度，varchar是可变长度。在Innodb下，推荐使用varchar。

## SQL Select语句完整的执行顺序：

1. from子句组装来自不同数据源的数据； （先join再on）
    * FROM table1 left join table2 on 将table1和table2中的数据产生笛卡尔积，生成Temp1
    * JOIN table2 所以先是确定表，再确定关联条件
    * ON table1.column = table2.column 确定表的绑定条件 由Temp1产生中间表Temp2
2. where子句基于指定的条件对记录行进行筛选；
3. group by子句将数据划分为多个分组；
4. 使用聚集函数进行计算；
    * 有COUNT() -> 总行数；MAX() -> 最大值，MIN() -> 最小值,SUM() -> 求和;AVG() -> 平均值。
5. 使用having子句筛选分组；
6. 计算所有的表达式；
7. select 的字段；
8. 使用order by对结果集进行排序。
9. limit分页

## explain语句分析慢查询
我们常常用到explain这个命令来查看一个这些SQL语句的执行计划，查看该SQL语句有没有使用上了索引，有没有做全表扫描

```sql
-- 实际SQL，查找用户名为Jefabc的员工
select * from emp where name = 'Jefabc';
-- 查看SQL是否使用索引，前面加上explain即可
explain select * from emp where name = 'Jefabc';
```

![img.png](explain.png)

* select_type : 查询类型，有简单查询、联合查询、子查询等
* key : 使用的索引
    * 没有索引就是null
* rows : 扫描的行数

### 最重要的字段 ： type

对表访问方式，表示MySQL在表中找到所需行的方式，又称“访问类型”。

常用的类型有： ALL、index、range、 ref、eq_ref、const、system、NULL（从左到右，性能从差到好）
1. ALL：Full Table Scan， MySQL将遍历全表以找到匹配的行
2. index: Full Index Scan，index与ALL区别为index类型只遍历索引树
3. range:只检索给定范围的行，使用一个索引来选择
4. ref: 表示上述表的连接匹配条件，即哪些列或常量被用于查找索引列上的值
    * 联合索引复合索引都是ref
5. eq_ref: 类似ref，区别就在使用的索引是唯一索引，对于每个索引键值，表中只有一条记录匹配，简单来说，就是多表连接中使用primary key或者 unique key作为关联条件
    * 主键索引唯一索引是eq_ref
6. const、system: 当MySQL对查询某部分进行优化，并转换为一个常量时，使用这些类型访问。如将主键置于where列表中，MySQL就能将该查询转换为一个常量，system是const类型的特例，当查询的表只有一行的情况下，使用system
7. NULL: MySQL在优化过程中分解语句，执行时甚至不用访问表或索引，例如从一个索引列里选取最小值可以通过单独索引查找完成。

## sql三种日志：binlog、redo log和undo log
binlog用于记录数据库执行的写入性操作(不包括查询)信息，以二进制的形式保存在磁盘中。binlog是mysql的逻辑日志，并且由Server层进行记录，使用任何存储引擎的mysql数据库都会记录binlog日志。

* 逻辑日志：可以简单理解为记录的就是sql语句。
* 物理日志：因为mysql数据最终是保存在数据页中的，物理日志记录的就是数据页变更。

binlog是通过追加的方式进行写入的，可以通过max_binlog_size参数设置每个binlog文件的大小，当文件大小达到给定值之后，会生成新的文件来保存日志。

binlog使用场景：
* 主从复制：在Master端开启binlog，然后将binlog发送到各个Slave端，Slave端重放binlog从而达到主从数据一致。
* 数据恢复：通过使用mysqlbinlog工具来恢复数据

Redo log：被称之为重做日志，是在数据库发生意外时，进行数据恢复，redo log会备份是事务执行过程中的修改数据，redo log备份的是事务过程中最新的数据位置

当我们提交一个事务时，InnoDB会先去把要修改的数据写入日志，然后再去修改缓冲池里面的真正数据页。

redo log包括两部分：一个是内存中的日志缓冲(redo log buffer)，另一个是磁盘上的日志文件(redo log file)。mysql每执行一条DML语句，先将记录写入redo log buffer，后续某个时间点再一次性将多个操作记录写到redo log file。这种先写日志，再写磁盘的技术就是MySQL里经常说到的WAL(Write-Ahead Logging) 技术。

redo log实际上记录数据页的变更，而这种变更记录是没必要全部保存，因此redo log实现上采用了大小固定，循环写入的方式，当写到结尾时，会回到开头循环写日志。

## 数据库三大范式
1. 每列都是不可再分的最小数据单元
2. 满足第一范式，非主键列不存在对主键的部分依赖，即要求每个表只描述一件事情
3. 满足第一第二范式，表中的列不存在对非主键列的传递依赖

## Q：谈谈 SQL 优化的经验
* 查询语句无论是使用哪种判断条件 等于、小于、大于， **WHERE 左侧的条件查询字段不要使用函数或者表达式**
* 当你的 SELECT 查询语句只需要使用一条记录时，要使用 LIMIT 1
* **不要直接使用**SELECT*，而应该使用具体需要查询的表字段
* 为每一张表设置一个 ID 属性
* 使用 BETWEEN AND 替代 IN
* 为搜索字段创建索引



