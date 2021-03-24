# PriorityQueue
优先队列PriorityQueue是Queue接口的实现，可以对其中元素进行排序。通过自定义的比较器，可以实现取最值的功能。
核心是通过堆来排序，相比于数组链表，可以做到一变插入或是取出元素，依然保持有序。
**但是对于优先级相同的元素时，它并不能保证先进先出。**


java中的优先队列是通过二叉堆实现的。

## 什么是二叉堆？
* 完全二叉树
* 堆的根节点的优先级最大（即最大或最小）
* 父节点的优先级必定大于子节点，兄弟节点的优先级不确定谁大谁小
* java中默认的是小根堆。即peek元素为最小值

## 存储二叉堆
![img.png](dui.png)

将上面的二叉树存储在数组中就是：

![img.png](duilist.png)

于是根据这些规则就会产生一些不得了的性质：

* 根节点的编号为1
* 结点k的父节点为k/2
* 结点k的左儿子为k ∗ 2 
* 结点k的右儿子为k ∗ 2 + 1

## 二叉堆的维护
1. 插入 ： 上浮算法
   * 往堆插入元素，插入到最后一个位置开始，通过上浮操作不断调整位置，直到满足父节点的优先级必定大于子节点这个条件。
    
![img.png](shangfu.png)

```java
private void up(int k) {
	int fa = k >> 1;
	while(fa >= 1) {
		if(heap[k] <= heap[fa]) {
			break;
		}
		int temp = heap[k];
		heap[k] = heap[fa];
		heap[fa] = temp;
		
		k = fa;
		fa >>= 1;
	}
}
```
2. 删除： 下沉算法
    * 删除顶部元素，先与最后一个元素交换（在最后一个位置删除），再对现在顶部元素下沉算法，保持堆的有序。
    * 删除指定位置的元素，其基本思想是从指定位置开始，把最后一个元素放到被删除元素的位置，通过下沉或者上浮操作，使得堆满足父元素优先级大于子元素的条件。

![img.png](xiachen.png)

```java
private void down(int k) {
	int son = k << 1;
	while(son <= size) {
		if(son + 1 <= size && heap[son + 1] < heap[son]) {
			son++;
		}
		if(heap[k] <= heap[son]) {
			break;
		}
		
		int temp = heap[k];
		heap[k] = heap[son];
		heap[son] = temp;
		
		k = son;
		son <<= 1;
	}
}
```
##复杂度分析
|   | 时间复杂度|
| --- | ------ | 
|插入|	O(log n)|
|删除|	O(log n)|
|构造|	O(n)|