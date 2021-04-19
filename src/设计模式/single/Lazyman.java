package 设计模式.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

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

    public static void main(String[] args) throws Exception {
        Field zz = Lazyman.class.getDeclaredField("flag");
        zz.setAccessible(true);

        Constructor<Lazyman> declaredConstructor= Lazyman.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);
        Lazyman instance = declaredConstructor.newInstance();

        zz.set(instance,false);

        Lazyman instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }
}
