# String 结构的差异

## sds 数据结构
```C
struct sdshdr {
    int len;        // 字符串的长度
    int free;       // 未使用的字节长度
    char buf[];     // 字符串的实际内容
};
```

SDS是Redis自己实现的一种字符串表示方式，相比于传统的C语言字符串，SDS具有许多优势和特点。

* 动态调整大小：SDS可以根据字符串的长度动态调整内存大小。这意味着当我们向SDS中添加更多的字符时，SDS会自动分配更多的内存空间来容纳新的字符，而不需要手动管理内存分配和释放。这样可以避免频繁的内存重新分配操作，提高了性能。

* O(1)时间复杂度的长度获取：SDS在内部维护了字符串的长度信息。因此，无论字符串的长度是多少，我们都可以在常数时间内获取字符串的长度，而不需要遍历整个字符串。这使得获取字符串长度的操作非常高效。

* 二进制安全：SDS可以存储任意二进制数据，而不仅仅局限于文本字符串。这意味着我们可以在SDS中存储包含空字符（‘\0’）在内的任意二进制数据，而不会导致字符串的截断或错误解析。

* 缓冲区溢出保护：SDS在内部维护了字符串的长度信息，这使得Redis能够有效地防止缓冲区溢出的问题。当我们向SDS中添加新的字符时，Redis会检查是否有足够的空间来容纳新的字符，如果没有足够的空间，Redis会自动分配更多的内存空间，以避免溢出。

* 兼容C字符串：SDS可以通过转换函数与C字符串进行互相转换。这意味着我们可以在Redis中使用SDS来存储字符串，然后将其转换为C字符串，以便与现有的C代码进行交互。反之，我们也可以将C字符串转换为SDS，以便在Redis中使用更多的字符串操作功能。

表格区别汇总如下。

| C字符串     | SDS |
| :---        |:------------| 
| 获取字符串长度的复杂度为O(1)      | 获取字符串长度的复杂度为O(1)     |
| API是不安全的，可能会造成缓冲区溢出  | API是安全的，不会造成缓冲区溢出        |
|  修改字符串长度N次必然需要执行N次内存重分配 | 修改字符串长度N次最多需要执行N次内存重分配  |
| 只能保存文本数据  | 可以保存文本或者二进制数据  |
| 可以使用所有<string.h>库中的函数  |	可以使用一部分<string.h>库中的函数   |
	
	
## 编码格式

主要是以三种编码形式来组成的，分别是int，raw，embstr。这里int主要是用来存放整形值的字符串，embstr用来存放字符串的短字符串（大小不超过44个字节），raw存放字符的长字符串（大小不超过44个字节）。
	

raw与embstr的区别

（1）redis并未提供任何修改embstr的方式，即embstr是只读的形式。对embstr的修改实际上是先转换为raw再进行修改。

（2）采用内存分配方式不同，虽然raw和embstr编码方式都是使用redisObject结构和sdshdr结构。但是raw编码方式采用两次分配内存的方式，分别创建redisObject和sdshdr，而embstr编码方式则是采用一次分配，分配一个连续的空间给redisObject和sdshdr。（embstr一次性分配内存的方式：1，使得分配空间的次数减少。2、释放内存也只需要一次。3、在连续的内存块中，利用了缓存的优点。）


# 跳表结构的差异
```C
/*
 * 跳跃表
 */
typedef struct zskiplist {
    // 头节点，尾节点
    struct zskiplistNode *header, *tail;
    // 节点数量
    unsigned long length;
    // 目前表内节点的最大层数
    int level;
} zskiplist;
/* ZSETs use a specialized version of Skiplists */
/*
 * 跳跃表节点
 */
typedef struct zskiplistNode {
    // member 对象
    robj *obj;
    // 分值
    double score;
    // 后退指针
    struct zskiplistNode *backward;
    // 层
    struct zskiplistLevel {
        // 前进指针
        struct zskiplistNode *forward;
        // 这个层跨越的节点数量
        unsigned int span;
    } level[];
} zskiplistNode;
```

相比于传统的跳表数据结构，redis的跳表增加了 后退指针 和 span（跨过节点数量）。后退指针用于倒序寻找，span则是快速按顺序找到第几个元素。


