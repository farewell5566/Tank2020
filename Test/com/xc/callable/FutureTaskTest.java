package com.xc.callable;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    public static void main(String[]Argus){
        FutureTask<Long> ft = new FutureTask(new MyTask());
        new Thread(ft).start();
        try {
            long l = ft.get();
            System.out.println(l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


