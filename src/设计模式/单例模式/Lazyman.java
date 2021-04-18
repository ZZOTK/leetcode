package 设计模式.单例模式;

//懒汉式单例
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
