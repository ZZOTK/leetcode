package 设计模式.single;

public class DCL {
    private static volatile DCL instance;

    private DCL() {
    }

    public static DCL getInstance(){
        if(null != instance) return instance;
        synchronized (DCL.class){
            if (null == instance){
                instance = new DCL();
            }
        }
        return instance;
    }
}
