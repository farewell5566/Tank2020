package com.xc.callable;



import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableAndFuture {
    public static void main(String[]argus){
        ExecutorService exeSer = Executors.newCachedThreadPool();
        Future<Long> future = exeSer.submit( new MyTask());

        try {
            long result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
