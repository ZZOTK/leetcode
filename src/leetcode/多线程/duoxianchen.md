# 手写多线程问题

* 三个线程分别打印 A，B，C，要求这三个线程一起运行，打印 n 次，输出形如“ABCABCABC....”的字符串
* 两个线程交替打印 0~100 的奇偶数

1. 使用Lock。

```java
public class leetcde3 {
    private int state;   // 当前状态值：保证三个线程之间交替打印
    private Lock lock = new ReentrantLock();

    private void printLetter(String name, int targetNum, int n) {
        for (int i = 0; i < n;) {
            lock.lock();
            if (state % 2 == targetNum ) {
                state++;
                if(i % 2 == targetNum){
                    System.out.print(name + i);
                }
                i++;
            }

            lock.unlock();
        }
    }

    public static void main(String[] args) {
        leetcde3 loopThread = new leetcde3();

        new Thread(() -> {
            loopThread.printLetter("B", 1,100);
        }, "B").start();

        new Thread(() -> {
            loopThread.printLetter("A", 0,100);
        }, "A").start();

    }
}
```

main 方法启动后，2个线程会抢锁，但是 state 的初始值为 0，所以第一次执行 if 语句的内容只能是 线程 A.

然后还在 for 循环之内，此时state = 1，只有 线程 B 才满足 1% 3 == 1，所以第二个执行的是 B，

执行完 AB 之后，才去执行第二次 for 循环，所以要把 i++ 写在 for 循环里边，不能写成 for (int i = 0; i < times;i++) 这样。


2. 信号量实现

> 在信号量上我们定义两种操作： 信号量主要用于两个目的，一个是用于多个共享资源的互斥使用，另一个用于并发线程数的控制。

* acquire（获取） 当一个线程调用 acquire 操作时，它要么通过成功获取信号量（信号量减1），要么一直等下去，直到有线程释放信号量，或超时。
* release（释放）实际上会将信号量的值加1，然后唤醒等待的线程。


```java
public class PrintABCUsingSemaphore {
    private int times = 100;
    private static Semaphore semaphoreA = new Semaphore(1); // 只有A 初始信号量为1,第一次获取到的只能是A
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    public static void main(String[] args) {
        PrintABCUsingSemaphore printer = new PrintABCUsingSemaphore();
        new Thread(() -> {
            printer.print("A", semaphoreA, semaphoreB);
        }, "A").start();

        new Thread(() -> {
            printer.print("B", semaphoreB, semaphoreC);
        }, "B").start();

        new Thread(() -> {
            printer.print("C", semaphoreC, semaphoreA);
        }, "C").start();
    }

    private void print(String name, Semaphore current, Semaphore next) {
        for (int i = 0; i < times; i++) {
            try {
                current.acquire();  // A获取信号执行,A信号量减1,当A为0时将无法继续获得该信号量
                System.out.print(name);
                next.release();    // B释放信号，B信号量加1（初始为0），此时可以获取B信号量
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
```

如果题目中是多个线程循环打印的话，一般使用信号量解决是效率较高的方案，上一个线程持有下一个线程的信号量，通过一个信号量数组将全部关联起来，这种方式不会存在浪费资源的情况。