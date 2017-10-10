package test;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by lian on 2016/12/31.
 */
public class CyclicBarrierDemo {
    public static class Soldier implements Runnable{
        private String soldier;
        private final CyclicBarrier cyclic;

        public Soldier(CyclicBarrier cyclic,String soldier) {
            this.soldier = soldier;
            this.cyclic=cyclic;
        }

        @Override
        public void run() {
                try {
                    cyclic.await();
                    dowork();
                    cyclic.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }



        void dowork() {
            try {
                Thread.sleep(Math.abs(new Random().nextInt()%10000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(soldier+":任务完成");
        }

    }

    public static class BarrierRun implements Runnable{
        boolean flag;
        int N;

        public BarrierRun(boolean flag,int N){
            this.flag=flag;
            this.N=N;
        }

        @Override
        public void run() {
            if(flag){
                System.out.println("司令：【士兵"+N+"个】，任务完成！");
            }else {
                System.out.println("司令：【士兵"+N+"个】，集合完毕！");
                flag=true;
            }
        }
    }
    public static void main(String[] a){
        final int N=10;
        Thread[] allSoldier=new Thread[N];
        boolean flag=false;
        CyclicBarrier cyclic=new CyclicBarrier(N,new BarrierRun(flag,N));
        System.out.println("集合队伍！");
        for (int i=0;i<N;++i){
            System.out.println("士兵"+i+"报道！");
            allSoldier[i]=new Thread(new Soldier(cyclic,"士兵"+i));
            allSoldier[i].start();
        }
    }
}

