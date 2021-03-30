# String创建对象问题
String类型对象创建个数，我总结为一下几种情况(均不考虑字符串在常量池中已存在的情况)：

1. String a=“123”;

创建了1个对象

jvm在编译阶段会判断常量池中是否有 “123” 这个常量对象如果有，a直接指向这个常量的引用，如果没有会在常量池里创建这个常量对象。

2. String a=new String(“123”);

创建了2个对象

同情况1，jvm编译阶段判断常量池中 "123"存在与否，进而来判断是否创建常量对象，然后运行阶段通过new关键字在java heap创建String对象。

3. String a=“123”+“456”;

创建了1个对象

jvm编译阶段过编译器优化后会把字符串常量直接合并成"123456"，所有创建对象时最多会在常量池中创建1个对象。

4. String a=“123”+new String(“456”);

创建了4个对象

常量池对象"123" ,“456”，new String(“456”)创建堆对象，还有一个堆对象"123456"。

# String, StringBuffer and StringBuilder
1. 可变性

String 不可变
StringBuffer 和 StringBuilder 可变
2. 线程安全

String 不可变，因此是线程安全的
StringBuilder 不是线程安全的
StringBuffer 是线程安全的，内部使用 synchronized 进行同步
StackOverflow : String, StringBuffer, and StringBuilder


