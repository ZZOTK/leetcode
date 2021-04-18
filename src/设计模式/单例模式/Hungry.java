package 设计模式.单例模式;

// 饿汉式单例
public class Hungry {
    private Hungry(){

    }

    private final static Hungry hungry = new Hungry();

    public static Hungry getInstance(){
        return hungry;
    }
}
