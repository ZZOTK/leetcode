## 工厂模式
工厂模式核心思想：实现创建者和调用者的分离

三种模式 ： 
1. 简单工厂模式
    * 用来生产同一等级结构中的任意产品（对于新增的产品要覆盖原有代码）
    ![img.png](fac1.png)
      
```java
public interface Keyboard {
    void print();
    void input(Context context);
}

class HPKeyboard implements Keyboard {

    @Override
    public void print() {
        //...输出逻辑;
    }

    @Override
    public void input(Context context) {
        //...输入逻辑;
    }

}

class DellKeyboard implements Keyboard {

    @Override
    public void print() {
        //...输出逻辑;
    }

    @Override
    public void input(Context context) {
        //...输入逻辑;
    }

}

class LenovoKeyboard implements Keyboard {

    @Override
    public void print() {
        //...输出逻辑;
    }

    @Override
    public void input(Context context) {
        //...输入逻辑;
    }

}

/**
 * 工厂
 */
public class KeyboardFactory {
    public Keyboard getInstance(int brand) {
        if(BrandEnum.HP.getCode() == brand){
            return new HPKeyboard();
        } else if(BrandEnum.LENOVO.getCode() == brand){
            return new LenovoKeyboard();
        } else if(BrandEnum.DELL.getCode() == brand){
            return new DellKeyboard();
        }
        return null;
    }

    public static void main(String[] args) {
        KeyboardFactory keyboardFactory = new KeyboardFactory();
        Keyboard lenovoKeyboard = KeyboardFactory.getInstance(BrandEnum.LENOVO.getCode());
        //...
    }

}
```
2. 工厂方法模式
    * 用来生产同一等级结构中的固定产品（支持增加任意商品）
    ![img.png](fac2.png)
      
```java
public interface IKeyboardFactory {
    Keyboard getInstance();
}

public class HPKeyboardFactory implements IKeyboardFactory {
    @Override
    public Keyboard getInstance(){
        return new HPKeyboard();
    }
}

public class LenovoFactory implements IKeyboardFactory {
    @Override
    public Keyboard getInstance(){
        return new LenovoKeyboard();
    }
}

public class DellKeyboardFactory implements IKeyboardFactory {
    @Override
    public Keyboard getInstance(){
        return new DellKeyboard();
    }
}
```
3. 抽象工厂模式
    * 围绕一个超级工厂创建其他工厂，该工厂又是其他工厂的厂
    ![img.png](fac3.png)

### 总结

简单工厂：唯一工厂类，一个产品抽象类，工厂类的创建方法依据入参判断并创建具体产品对象。

工厂方法：多个工厂类，一个产品抽象类，利用多态创建不同的产品对象，避免了大量的if-else判断。

抽象工厂：多个工厂类，多个产品抽象类，产品子类分组，同一个工厂实现类创建同组中的不同产品，减少了工厂子类的数量。


