# javaSE整理

## java和c++比较
* 都面向对象
* java在多线程（c++没有内置多线程支持）与网络编程方面更有优势
* Java 不提供指针来直接访问内存，程序内存更加安全 
* Java 的类是单继承的，C++ 支持多重继承；虽然 Java 的类不可以多继承，但是接口可以多继承。
* Java 有自动内存管理垃圾回收机制(GC)，不需要程序员手动释放无用内存

##  Java 泛型了解么？什么是类型擦除？介绍一下常用的通配符？
Java 泛型（generics）是 JDK 5 中引入的一个新特性, 泛型提供了编译时类型安全检测机制，该机制允许程序员在编译时检测到非法的类型。泛型的本质是参数化类型，也就是说所操作的数据类型被指定为一个参数。

Java 的泛型是伪泛型，泛型信息只存在于代码编译阶段，在进入 JVM 之前，与泛型相关的信息会被擦除掉，专业术语叫做类型擦除。

```java
List<String> l1 = new ArrayList<String>();
List<Integer> l2 = new ArrayList<Integer>();
		
System.out.println(l1.getClass() == l2.getClass());
```

打印的结果为 true 是因为 List<String>和 List<Integer>在 jvm 中的 Class 都是 List.class。 泛型信息被擦除了。

泛型一般有三种使用方式:泛型类、泛型接口、泛型方法。

常用的通配符为： T，E，K，V，？

* ？ 表示不确定的 java 类型
* T (type) 表示具体的一个 java 类型
* K V (key value) 分别代表 java 键值中的 Key Value
* E (element) 代表 Element

## ==与equals()的区别
== : 它的作用是判断两个对象的地址是不是相等。即判断两个对象是不是同一个对象。**(基本数据类型==比较的是值，引用数据类型==比较的是内存地址)**

equals() : 它的作用也是判断两个对象是否相等，它不能用于比较基本数据类型的变量。equals()方法存在于Object类中，而Object类是所有类的直接或间接父类。
* 类覆盖了 equals()方法。一般，我们都覆盖 equals()方法来两个对象的内容相等；若它们的内容相等，则返回 true(即，认为这两个对象相等)。
* 类没有覆盖 equals()方法。则通过equals()比较该类的两个对象时，等价于通过“==”比较这两个对象。使用的默认是 Object类equals()方法。

### 为什么重写 equals 时必须重写 hashCode 方法？

因为不重写的话，那么当你创建这个类的HashMap（key）、HashSet等不允许元素重复的散列表并塞入数据时，可能会出现重复的元素。

这是因为散列表中判断元素重复时，会先判断hashcode是否相等，相等后才会比较equals。如果hashcode不相同的就直接判断为不同元素。

equals() 方法和 hashcode() 方法间的关系是这样的：

1. 如果两个对象相同（即：用 equals比较返回true），那么它们的 hashCode 值一定要相同;
2. 如果两个对象的 hashCode 相同，它们并不一定相同（即：用 equals比较返回 false）;

##  基本数据类型
### Java 中的几种基本数据类型是什么？对应的包装类型是什么？各自占用多少字节呢？
| 基本类型 | 位数 | 字节 | 默认值  |
| :------- | :--- | :--- | ------- |
| int      | 32   | 4    | 0       |
| short    | 16   | 2    | 0       |
| long     | 64   | 8    | 0L      |
| byte     | 8    | 1    | 0       |
| char     | 16   | 2    | 'u0000' |
| float    | 32   | 4    | 0f      |
| double   | 64   | 8    | 0d      |
| boolean  | 1    |      | false   |

### 8 种基本类型的包装类和常量池
Java 基本类型的包装类的大部分都实现了常量池技术，即 Byte,Short,Integer,Long,Character,Boolean；前面 4 种包装类默认创建了数值[-128，127] 的相应类型的缓存数据，Character 创建了数值在[0,127]范围的缓存数据，Boolean 直接返回 True Or False。如果超出对应范围仍然会去创建新的对象。
为啥把缓存设置为[-128，127]区间？性能和资源之间的权衡。

## 深拷贝 vs 浅拷贝
* 浅拷贝：对基本数据类型进行值传递，对引用数据类型进行引用传递般的拷贝，此为浅拷贝。
* 深拷贝：对基本数据类型进行值传递，对引用数据类型，创建一个新的对象，并复制其内容，此为深拷贝。
![img.png](copy.png)

## String StringBuffer 和 StringBuilder 的区别是什么? String 为什么是不可变的?
简单的来说：String 类中使用 final 关键字修饰字符数组来保存字符串，private final char value[]，所以String 对象是不可变的。

而 StringBuilder 与 StringBuffer 都继承自 AbstractStringBuilder 类，在 AbstractStringBuilder 中也是使用字符数组保存字符串char[]value 但是没有用 final 关键字修饰，所以这两种对象都是可变的。

线程安全性:
* String 中的对象是不可变的，也就可以理解为常量，线程安全。
* StringBuffer 对方法加了同步锁或者对调用的方法加了同步锁，所以是线程安全的。
* StringBuilder 并没有对方法进行加同步锁，所以是非线程安全的。