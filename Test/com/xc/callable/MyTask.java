package com.xc.callable;

import java.util.concurrent.Callable;

public class MyTask implements Callable<Long> {
    @Override
    public Long call() throws Exception {
        long a = 1L;
        for (long i =1L;i<100l;i++){
            a +=i;
            System.out.println("i的值为"+i);
            Thread.sleep(50);
        }

        return a;
    }

}
