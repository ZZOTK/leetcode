## CountDownLatch
用来控制一个或者多个线程等待多个线程。

维护了一个计数器 cnt，每次调用 countDown() 方法会让计数器的值减 1，减到 0 的时候，那些因为调用 await() 方法而在等待的线程就会被唤醒。

```java
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        //设定初始值
        CountDownLatch countDownLatch = new  CountDownLatch(6);
        for (int i = 1; i <= 6 ; i++) {
            new Thread(() ->{
                System.out.println(Thread.currentThread().getName() + "go out");
                //减一
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }

        //唤醒
        countDownLatch.await();

        System.out.println("Close door");
    }
}
```
## CyclicBarrier
用来控制多个线程互相等待，只有当多个线程都到达时，这些线程才会继续执行。

和 CountdownLatch 相似，都是通过维护计数器来实现的。线程执行 await() 方法之后计数器会减 1，并进行等待，直到计数器为 0，所有调用 await() 方法而在等待的线程才能继续执行。

CyclicBarrier 和 CountdownLatch 的一个区别是，CyclicBarrier 的计数器通过调用 reset() 方法可以循环使用，所以它才叫做循环屏障。

CyclicBarrier 有两个构造函数，其中 parties 指示计数器的初始值，barrierAction 在所有线程都到达屏障的时候会执行一次。

```java
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("召唤神龙");
        });

        for(int i = 1; i <= 7; i ++){
            final int temp = i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName() + "收集到"+ temp + "号龙珠");

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println(temp);
            }).start();
        }
    }
}
```
在await后面的代码要等await执行结束之后再执行。

## Semaphore
Semaphore 类似于操作系统中的信号量，可以控制对互斥资源的访问线程数。

示例用信号量实现3个线程交替打印123
```java
public class semaphoreDemo {
    private static Semaphore A =  new Semaphore(1);
    private static Semaphore B =  new Semaphore(0);
    private static Semaphore C =  new Semaphore(0);

    public static void main(String[] args) {
        new Thread(() ->{
            for(int i = 0;  i < 10 ; i ++){
                try {
                    A.acquire();
                    System.out.println("1");
                    B.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threada").start();

        new Thread(() ->{
            for(int i = 0;  i < 10 ; i ++){
                try {
                    B.acquire();
                    System.out.println("2");
                    C.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadb").start();

        new Thread(() ->{
            for(int i = 0;  i < 10 ; i ++){
                try {
                    C.acquire();
                    System.out.println("3");
                    A.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadc").start();
    }
}
```




