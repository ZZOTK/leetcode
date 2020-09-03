import edu.princeton.cs.algs4.*;

import javax.imageio.stream.ImageInputStream;


public class flip {
    private int[] id;
    private int count;
    public flip(int N){
        count =N;
        id = new int[N];
        for (int i=0; i<N; i++)
            id[i]=i;
    }
    public int count(){return count;}
    public boolean connected(int p, int q)
    {return find(p) == find(q);}
    public int find(int p){
        return id[p];
    }
    public void unim(int p, int q){
        int pID = find(p);
        int qID = find(q);
        if(pID==qID) return;
        for(int i=0; i<id.length; i++)
            if(id[i]==pID) id[i]=qID;
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        UF uf =new UF(N);
        while(!StdIn.isEmpty()){
            int p =StdIn.readInt();
            int q = StdIn.readInt();
            if(uf.connected(p,q)) continue;
            uf.union(p,q);
            StdOut.println(p+" "+q);

        }
        StdOut.println(uf.count()+"components");
    }

}

