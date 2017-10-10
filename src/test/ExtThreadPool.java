package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by lian on 2017/1/4.
 */
public class ExtThreadPool {
    public static class MyTask implements Runnable{
        public String name;

        public MyTask(String name){
            this.name=name;
        }

        public void run(){
            System.out.println("正在执行"+Thread.currentThread().getId()+"Task,Name="+name);
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] a)throws InterruptedException{
        ExecutorService es=new ThreadPoolExecutor(5,5,0L, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<Runnable>()){
            protected void beforeExecute(Thread t,Runnable r){
                System.out.println("准备执行："+((MyTask)r).name);
            }

            protected void afterExecute(Runnable r,Throwable t){
                System.out.println("执行完成:"+((MyTask)r).name);
            }

            protected void terminated(){
                System.out.println("线程池退出");
            }

        };
        for (int i=0;i<5;i++){
            MyTask task=new MyTask("TASK-GEYM-"+i);
            es.execute(task);
            Thread.sleep(10);
        }
        es.shutdown();
    }


}