package multi_thread.AQS;

import java.util.concurrent.Semaphore;

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
