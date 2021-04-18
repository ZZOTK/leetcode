package 设计模式.单例模式;

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
