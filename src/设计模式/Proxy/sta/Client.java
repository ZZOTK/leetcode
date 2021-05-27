package 设计模式.Proxy.sta;

public class Client {
    public static void main(String[] args) {
        Host host = new Host();
//        host.rent();
        //代理，租房不面对房东直接面对中介
        //代理实现了很多的附属操作，房东只提供了租房
        //功能的扩展
        Proxy proxy = new Proxy(host);
        proxy.rent();
    }
}
