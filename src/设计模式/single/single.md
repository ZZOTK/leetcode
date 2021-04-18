# 单例模式
单例模式的核心思想： 构造器私有！

## 饿汉式单例
```java
//饿汉式单例
public class Hungry {
    private Hungry(){

    }

    private final static Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }
}
```

特点：在创建初期就实例化了对象，这样调用都是这个已经创建好的对象

缺点：开始就创建很浪费内存资源。

解决：用的时候再创建

## 懒汉式单例
```java
//懒汉式单例
public class Lazyman {
    private Lazyman(){
    }

    private static Lazyman lazyman;

    public static Lazyman getInstance(){
        if(lazyman == null){
            lazyman = new Lazyman();
        }
        return lazyman;
    }
}
```
确实做到了用时再创建，但是在多线程情况下不安全。解决方案： 加锁

## DCL（双重检测模式的懒汉单例）
```java
//懒汉式单例
public class Lazyman {
    private Lazyman(){
    }

    private volatile static Lazyman lazyman;

    public static Lazyman getInstance(){
        //两次加锁
        if(lazyman == null){
            synchronized (Lazyman.class){
                if(lazyman == null){
                    lazyman = new Lazyman();
               }
            }
        }
        return lazyman;
    }
}
```
用两次检测并加锁解决并发下的懒汉单例问题。第一次检测保证加锁前lazyman是否为空，
第二次检查加锁后，lazyman是否为空（有可能加锁时已经被创建了）。

为什么要加volatile？
new的时候需要三步操作：
1. 分配内存空间
2. 执行构造方法，初始化对象
3. 把这个对象指向这个空间

有可能会执行132，先分配空间，空对象占用空间，再初始化。再空对象占用空间时，如果有一个线程进来，他判断lazyman不为null，就直接return了。

此时：反射可以打破单例模式。
## 静态内部类实现
```java
//静态内部类实现单例
public class Holder {
    private Holder(){

    }

    public static Holder getInstance(){
        return Innerclass.holder;
    }

    public static class Innerclass{
        private final static Holder holder = new Holder();
    }
}
```
换了个实现方式，反射可以打破，不安全。

## 加入一个标志位
```java
public class Lazyman {
    private static boolean flag = false; 
    
    private Lazyman(){
        synchronized (Lazyman.class){
            if(!flag){
                flag =true;
            }else {
                throw new RuntimeException("不要试图用反射破坏");
            }
        }
    }

    private static Lazyman lazyman;

    public static Lazyman getInstance(){
        if(lazyman == null){
            synchronized (Lazyman.class){
                if(lazyman == null){
                    lazyman = new Lazyman();
                }
            }
        }
        return lazyman;
    }
}
```
使用一个flag记录，当创建之后flag变为true，也就不能再创建。

问题：反射可以改这个flag。只使用反射创建不能破坏单例了，但是反射技术可以改这个flag来破坏。

## 







