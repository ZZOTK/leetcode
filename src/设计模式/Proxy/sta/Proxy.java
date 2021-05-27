package 设计模式.Proxy.sta;

public class Proxy implements Rent{
    private Host host;

    public Proxy(){
    }

    public Proxy(Host host) {
        this.host = host;
    }

    @Override
    public void rent() {
        seeHouse();
        host.rent();
        het();
        fare();
    }

    public void seeHouse(){
        System.out.println("中介带你看房");
    }

    public void het(){
        System.out.println("中介签合同");
    }

    public void fare(){
        System.out.println("收中介费");
    }
}
