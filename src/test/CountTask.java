package test;
import jsr166y.ForkJoinPool;
import jsr166y.ForkJoinTask;
import jsr166y.RecursiveTask;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by lian on 2017/1/4.
 */
public class CountTask extends RecursiveTask<Long>{
    private static final int THRESHOLD=1000;
    private long start;
    private long end;

    public CountTask(long start,long end){
        this.start=start;
        this.end=end;
    }

    public Long compute(){
        long sum=0;
        boolean canCompute=(end-start)<THRESHOLD;
        if(canCompute){
            for(long i=start;i<=end;i++){
                sum+=i;
            }
        }else {
            long step=(start+end)/100;
            ArrayList<CountTask> subTasks=new ArrayList<CountTask>();
            long pos=start;
            for(int i=0;i<100;i++){
                long lastOne=pos+step;
                if(lastOne>end){
                    lastOne=end;
                }
                CountTask subTask=new CountTask(pos,lastOne);
                pos+=step+1;
                subTasks.add(subTask);
                subTask.fork();
            }
            for (CountTask t:subTasks){
                sum+=t.join();
            }
        }
        return sum;
    }


    public static void main(String[]a){
        ForkJoinPool  forkJoinPool=new ForkJoinPool();
        CountTask task=new CountTask(0,200000L);
        ForkJoinTask<Long> result=forkJoinPool.submit(task);
        try {
            long res=result.get();
            System.out.println("sum"+res);
        }catch (InterruptedException e){
            e.printStackTrace();
        }catch (ExecutionException e){
            e.printStackTrace();
        }

    }

}
