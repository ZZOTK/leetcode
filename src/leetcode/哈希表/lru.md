## leetcode146
实现LRU缓存机制

1. 新建一个DlinkNode类。这是一个包含key，value的双向链表。
2. 需要一些中间变量，
    * Map<Integer, DLinkedNode> cache记录是否有这个节点。
        * key值是node的key，value值是node本身。这样可以直接通过hash表找到链表中的某个节点！
    * size是当前的长度，capacity是最大长度
    * 一个头结点和尾节点来方便操作
3. 分析实现逻辑

* 获取key
    * 先去hash表中get(key)，如果不存在，直接返回。
    * 存在的话，返回key的值，并将节点移动到头部
    
* 增加key
    * 先去hash表中get(key)，如果不存在，则新建一个节点，将节点放到首部，并size+1。
    * 如果size超过了最大值capacity，则需要删除最后一个节点
    * 如果key已经存在，就更新节点的值，并将该节点移动到首部
    
从实现逻辑可以看出，
* 增加到首部
* 移动到首部
* 删除最后一个节点
* 删除某个节点

这几个操作可以自己实现。按双向链表实现。

```java
class LRUCache {
    class DLinkedNode {
        int key;
        int value;
        DLinkedNode prev;
        DLinkedNode next;
        public DLinkedNode() {}
        public DLinkedNode(int key, int value) {this.key = key; this.value = value;}
    }

    private Map<Integer, DLinkedNode> cache = new HashMap<>();
    private int size;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {
            // 如果 key 不存在，创建一个新的节点
            DLinkedNode newNode = new DLinkedNode(key, value);
            // 添加进哈希表
            cache.put(key, newNode);
            // 添加至双向链表的头部
            addToHead(newNode);
            ++size;
            if (size > capacity) {
                // 如果超出容量，删除双向链表的尾部节点
                DLinkedNode tail = removeTail();
                // 删除哈希表中对应的项
                cache.remove(tail.key);
                --size;
            }
        }
        else {
            // 如果 key 存在，先通过哈希表定位，再修改 value，并移到头部
            node.value = value;
            moveToHead(node);
        }
    }

    //将一个节点增加到头部
    private void addToHead(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    //删除某个节点
    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    //将一个节点移动带头部
    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    //删除尾部的节点
    private DLinkedNode removeTail() {
        DLinkedNode res = tail.prev;
        removeNode(res);
        return res;
    }

}
```