package 设计模式.single;

import java.lang.reflect.Constructor;

public enum EnumSingle {
    INSTANCE;
    public EnumSingle getInstance(){
        return INSTANCE;
    }
}

class  Test{
    public static void main(String[] args) throws Exception {
        EnumSingle instance = EnumSingle.INSTANCE;
        Constructor<EnumSingle> declaredConstructor = EnumSingle.class.getDeclaredConstructor(String.class, int.class);
        declaredConstructor.setAccessible(true);
        EnumSingle instance2 = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(instance2);
    }
}
