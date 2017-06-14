package com.threads.executor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.threads.MyCallable;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Callable<String>> tasks = new ArrayList<>();
        
        for(int i=1;i<4;i++){
        	MyCallable myCallable = new MyCallable("http://192.168.1.8:8080/HttpUtil/MyService?time="+(i*20000)+"&a="+(i+100)+"&b="+(i+200));
        	tasks.add(myCallable);
        }
        try {
            List<Future<String>> aaa = executor.invokeAll(tasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        if(executor.isShutdown()){
        	System.out.println("COmpleted Task Successfully");
        }

	}

}
