package test;

import java.util.concurrent.*;

/**
 * Created by lian on 2017/1/4.
 */
public class DivTask implements Runnable{
    int a,b;
    public DivTask(int a,int b){
        this.a=a;
        this.b=b;
    }

    @Override
    public void run() {
        double re=a/b;
        System.out.println(re);
    }

    public static void main(String[]a)throws InterruptedException,ExecutionException{
        ThreadPoolExecutor pools=new ThreadPoolExecutor(0,Integer.MAX_VALUE,0L, TimeUnit.SECONDS,new SynchronousQueue<Runnable>());

        for(int i=0;i<5;i++){
            Future re=pools.submit(new DivTask(100,i));
            re.get();
//            pools.execute(new DivTask(100,i));
        }



    }

}
